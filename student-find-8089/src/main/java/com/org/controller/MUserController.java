package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MUser;
import com.org.service.MUserService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseEnum;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <p>
 * 用户表
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-11-08
 */
@RestController
@RequestMapping("/find/mUser")
public class MUserController {
    @Autowired
    private MUserService mUserService;

    /**
     * 上传图片
     */
    public static Map<String, String> Base64ToPic(String Type, String base64Data, HttpServletRequest request) throws IOException {
        Map<String, String> map = null;                            //给前端的结果
        String src = null;
        if (Type.equals("face")) {                          //设置图片存放文件夹的路径
            src = "images/face";
        } else if (Type.equals("head")) {
            src = "images/head";
        }
        /*没有此文件夹就创建*/
        String filepath = ResourceUtils.getURL("").getPath() + src;
        System.out.println(filepath);
        File filePath = new File(filepath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //用上传时间表示文件名防止冲突
        String filename = filepath + File.separator + new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date())
                + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000)
                + ".png";
        System.out.println(filename);
        //创建文件
        File imageFile = new File(filename);
        if (!imageFile.exists()) {
            imageFile.createNewFile();
        }
        //jdk8无法解码包含换行的编码结果，需要使用Base64.getMimeDecoder();
        byte[] bs = Base64.getMimeDecoder().decode(base64Data);//base64解码
        //将二进制写入文件
        OutputStream imageStream = new FileOutputStream(filename);
        imageStream.write(bs);
        imageStream.flush();
        imageStream.close();

        map = new HashMap<>();
        map.put("picName", filename);
        return map;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/uploadHead")
    public ServerResponseVO uploadHead(@RequestBody Map<String, Object> map, HttpServletRequest request) throws IOException {
        String base64str = (String) map.get("base46Str");
        System.out.println(base64str);
        Map<String, String> fileMap = Base64ToPic("head", base64str, request);
        System.out.println(ResourceUtils.getURL("").getPath());
//        return ServerResponseVO.success(fileMap);
        return ServerResponseVO.success(ServerResponseEnum.SUCCESS);
    }


    /**
     * 查询用户信息详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserinfo")
    public ServerResponseVO detail(HttpServletRequest request, HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        MUser mUser = mUserService.getById(userId);
        return ServerResponseVO.success(mUser);
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(@RequestBody MUser mUser, HttpServletRequest request,
                         HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        mUser.setId(userId);
//        mUser.setRole(1);
        mUserService.updateUser(mUser);
//        mUserService.updateById(mUser);
        return "ok";
    }

    /**
     * 新增
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(@RequestBody MUser mUser, HttpServletRequest request,
                      HttpServletResponse response) {
        mUserService.save(mUser);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mUserService.removeByIds(idList);
        return "ok";
    }


    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public String queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MUser> wrapper = new QueryWrapper<>();
        List<MUser> list = mUserService.list(wrapper);
        return "ok";
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public String queryPageList(HttpServletRequest request, HttpServletResponse response,
                                MUser query,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MUser> wrapper = new QueryWrapper<>();
        Page<MUser> pg = new Page<MUser>(pageNum, pageSize);
        pg = mUserService.page(pg, wrapper);
        return "ok";
    }

}

