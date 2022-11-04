package com.org.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.org.model.MessageComment;
import com.org.model.Result;
import com.org.service.IMessageCommentService;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资讯评论表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/messageComment")
public class MessageCommentController {

    @Autowired
    IMessageCommentService messageCommentService;

    /*
     *add
     * */

    /*
     *delete
     * */
    @ApiOperation(value = "删除资讯评论")
    @PostMapping("/delMsgCom")
    public Result delMsgCom(@RequestBody String data) {
        //解析json字符串
        JSONObject jsonObject =  JSON.parseObject(data);
        List<Integer> commentIds = (List<Integer>) jsonObject.get("commentIds");
        //批量删除
        if(!messageCommentService.removeBatchByIds(commentIds)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, "Ture");
    }

    @ApiOperation(value = "删除资讯全部评论")
    @GetMapping("/delAllMsgCom/{msg_id}")
    public boolean delAllMsgCom(@PathVariable Long msg_id) {


        return true;
    }

    /*
     *update
     * */

    /*
    * search
    * */


}

