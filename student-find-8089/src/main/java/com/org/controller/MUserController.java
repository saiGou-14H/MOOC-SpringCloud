package com.org.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MUser;
import com.org.service.MUserService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
     * 新增
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(@RequestBody MUser mUser, HttpServletRequest request,
                      HttpServletResponse response) {
        mUserService.save(mUser);
        return "ok";
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
     * 查询用户信息详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserinfo")
    public ServerResponseVO detail(HttpServletRequest request, HttpServletResponse response) {

        Long userId = JwtUtil.getId(request);
        MUser mUser = mUserService.getById(userId);
        return ServerResponseVO.success(mUser);
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

