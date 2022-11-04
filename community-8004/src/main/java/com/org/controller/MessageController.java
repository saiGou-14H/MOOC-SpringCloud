package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.model.Message;
import com.org.model.MessageComment;
import com.org.model.Result;
import com.org.service.IMessageCommentService;
import com.org.service.IMessageService;
import com.org.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 资讯表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@Api(value = "资讯")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    IMessageService messageService;
    @Autowired
    IMessageCommentService messageCommentService;

    private static final String REST_URL_COMMUNITY = "http://COMMUNITY-8004";

    /*
    * add
    * */
    @ApiOperation(value = "创建资讯")
    @PostMapping("/ctMessage")
    public Result ctMessage(@RequestBody Message message, HttpServletRequest request) {
        try {
            IdGeneratorOptions options = new IdGeneratorOptions((short)1);
            YitIdHelper.setIdGenerator(options);
            Long newId = YitIdHelper.nextId();
            message.setId(newId/1000000);

            message.setAuthor(JwtUtil.getUserName(request));
            message.setTeaId(JwtUtil.getId(request));
        } catch (Exception e) {e.printStackTrace();}
        if(!messageService.save(message)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据是否有误或个别数据是否为空");
        return Result.success(HttpStatus.SC_OK, "Ture");
    }

    /*
     *delete
     * */
    @ApiOperation(value = "删除资讯")
    @GetMapping("/delComment/{msg_id}")
    public Result delComment(@PathVariable Long msg_id) {
        //首先删除资讯评论
        messageCommentService.remove(new QueryWrapper<MessageComment>().eq("message_id", msg_id));
        //再删除资讯
        if(!messageService.removeById(msg_id)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "删除资讯失败");
        return Result.success(HttpStatus.SC_OK, "Ture");
    }

    /*
     *update
     * */
    @ApiOperation(value = "修改资讯")
    @PostMapping("/udMessage")
    public Result udMessage(@RequestBody Message message) {
        if(!messageService.udMessage(message)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据是否有误或个别数据是否为空");
        return Result.success(HttpStatus.SC_OK, "Ture");
    }

    /*
     *search
     * */
    @ApiOperation(value = "查询资讯")
    @GetMapping("/shMessage")
    public Result shMessage(HttpServletRequest request) {
        List<Message> messageList = messageService.list(new QueryWrapper<Message>().eq("tea_id", JwtUtil.getId(request)));
        if(messageList.isEmpty()) return Result.success(HttpStatus.SC_OK, "没有创建过资讯");
        return Result.success(HttpStatus.SC_OK, "Ture", messageList);
    }

    @ApiOperation(value = "查询资讯评论")
    @GetMapping("/shMsgCom/{msg_id}")
    public Result shMsgCom(@PathVariable Long msg_id) {
        List<MessageComment> messageCommentList = messageService.shMsgCom(msg_id);
        if(messageCommentList.isEmpty()) return Result.success(HttpStatus.SC_OK, "该资讯没有评论");
        return Result.success(HttpStatus.SC_OK, "Ture", messageCommentList);
    }

}

