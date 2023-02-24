package com.org.controller;


import com.org.config.utils.FileUtil;
import com.org.model.QuestionComment;
import com.org.model.Result;
import com.org.service.IQuestionCommentService;
import com.org.service.ISensitiveService;
import com.org.util.JwtUtil;
import com.org.util.SensitiveWordUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/questionComment")
public class QuestionCommentController {

    @Autowired
    IQuestionCommentService questionCommentService;
    @Autowired
    ISensitiveService sensitiveService;

    /*
    * add
    * */
    //教师回答默认为最佳
    @ApiOperation(value = "教师回复问题")
    @PostMapping("/answerQue")
    public Result answerQue(@RequestBody QuestionComment questionComment, HttpServletRequest request) {

        //加载敏感词
        Set<String> senstiveSet = FileUtil.getFileContent();
        //Set<String> senstiveSet = new HashSet<>();
        //将查询到的list存到set中
        //senstiveSet.addAll(sensitiveService.shWord());
        senstiveSet.addAll(senstiveSet);
        SensitiveWordUtil.init(senstiveSet);
        //查找是否有敏感词
        boolean flag = SensitiveWordUtil.contains(questionComment.getContent(), SensitiveWordUtil.MinMatchTYpe);
        if(flag) {
            //获取语
            // 句中的敏感词
            Set<String> set = SensitiveWordUtil.getSensitiveWord(questionComment.getContent());
            System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "语句中包含敏感词的个数为：" + set.size(), set);
        }

        //生成时间
        Date date = new Date(); //获取当前时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp tdate = new Timestamp(date.getTime());
        //插入数据
        questionComment.setUserId(JwtUtil.getId(request)).setDate(formatter.format(tdate));
        if(!questionCommentService.answerQue(questionComment)) {return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");}

        return Result.failure(HttpStatus.SC_OK, "true");
    }

//    @Test
//    public void fun() {
//
//    }


    /*
     * delete
     * */

    /*
     * update
     * */
    @ApiOperation(value = "审核回复")
    @GetMapping("/checkCom/{com_id}/{state}")
    public Result checkCom(@PathVariable("com_id") Long comId, @PathVariable("state") Integer state) {
        Map<String, Object> map = new HashMap<>();
        map.put("comId", comId);
        map.put("state", state);
        if (!questionCommentService.checkCom(map)) {}
        return Result.success(HttpStatus.SC_OK, "true");
    }
    @ApiOperation(value = "教师采纳回复")
    @GetMapping("/acceptCom/{id}")
    public Result acceptCom(@PathVariable("id") Long id) {

        if(!questionCommentService.acceptCom(id)) {return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");}

        return Result.failure(HttpStatus.SC_OK, "true");
    }

    /*
     * search
     * */


}

