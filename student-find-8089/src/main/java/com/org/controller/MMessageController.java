package com.org.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.entity.MMessage;
import com.org.entity.vo.MessagePageVO;
import com.org.service.MMessageService;
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

}

