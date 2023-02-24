package com.org.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.entity.MMessage;
import com.org.entity.vo.MessagePageVO;
import com.org.service.MMessageService;
import com.org.util.JwtUtil;
import com.org.util.ServerResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资讯表
 * 前端控制器
 * </p>
 *
 * @author Jie
 * @since 2022-10-23
 */
@RestController
@RequestMapping("/find/mMessage")
public class MMessageController {
    @Autowired
    private MMessageService mMessageService;


    /**
     * 分页查询资讯列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchMMessageByPage")
    public ServerResponseVO searchMMessageByPage(HttpServletRequest request, HttpServletResponse response,
                                                 @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                                 @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        List<MessagePageVO> messagePageVOList = mMessageService.searchMMessageByPage((pageNum - 1) * pageSize, pageSize);
        return ServerResponseVO.success(messagePageVOList);
    }


    /**
     * 新增
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(@RequestBody MMessage mMessage, HttpServletRequest request,
                      HttpServletResponse response) {
        Long userId = JwtUtil.getId(request);
        mMessage.setTeaId(userId);

        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
        YitIdHelper.setIdGenerator(options);
        Long mMessageId = YitIdHelper.nextId() / 1000000;
        mMessage.setId(mMessageId);
//        mMessageService.save(mMessage);
        mMessageService.addMessage(mMessage);
        return "ok";
    }

    /**
     * 修改
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String edit(@RequestBody MMessage mMessage, HttpServletRequest request,
                       HttpServletResponse response) {
        mMessageService.updateById(mMessage);
        return "ok";
    }

    /**
     * 删除
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         String ids) {
        List<String> idList = StrUtil.split(ids, ',');
        mMessageService.removeByIds(idList);
        return "ok";
    }

    /**
     * 查询详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public MMessage detail(HttpServletRequest request, HttpServletResponse response,
                           String id) {
        MMessage mMessage = mMessageService.getById(id);
        return mMessage;
    }

    /**
     * 查询所有
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryList")
    public List<MMessage> queryList(HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<MMessage> wrapper = new QueryWrapper<>();
        List<MMessage> list = mMessageService.list(wrapper);
        return list;
    }

    /**
     * 分页查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageList")
    public Page<MMessage> queryPageList(HttpServletRequest request, HttpServletResponse response,
                                        MMessage query,
                                        @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        QueryWrapper<MMessage> wrapper = new QueryWrapper<>();
        Page<MMessage> pg = new Page<MMessage>(pageNum, pageSize);
        pg = mMessageService.page(pg, wrapper);
        return pg;
    }

    /**
     * listAll
     */
    @RequestMapping(method = RequestMethod.POST, value = "/listAll")
    public String listAll(@RequestBody Map<String, Object> mMessage, HttpServletRequest request,
                          HttpServletResponse response) {
        System.out.println("token:" + request.getHeader("token"));
        System.out.println("mMessage:" + new JSONObject(mMessage).toString());
        System.out.println(new JSONObject(mMessage).get("page").toString());
        int in = Integer.parseInt(new JSONObject(mMessage).get("page").toString());
        System.out.println(in);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "success");
        map.put("code", "0");
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 1; i < 9; i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("vid", i);
            objectMap.put("vtitle", "青龙战甲搭配机动兵");
            objectMap.put("author", "狙击手麦克");
            objectMap.put("headurl", "https://profile-avatar.csdnimg.cn/eabd69cac9064aa89f4d55b70acaf132_lgz0921.jpg");
            list.add(objectMap);

        }
        Map<String, Object> page = new HashMap<>();
        page.put("totalCount", "4");
        page.put("pageSize", "10");
        page.put("totalPage", "1");
        page.put("currPage", "1");
        if (in == 1) {
            page.put("list", list);
        } else {
            page.put("list", null);
        }
        map.put("page", page);

        return new JSONObject(map).toString();
    }
}

