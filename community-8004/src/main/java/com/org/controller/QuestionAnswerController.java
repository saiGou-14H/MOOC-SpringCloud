package com.org.controller;


import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.config.utils.FileUtil;
import com.org.model.QuestionAnswer;
import com.org.model.Result;
import com.org.service.IQuestionAnswerService;
import com.org.util.JwtUtil;
import com.org.util.SensitiveWordUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 话题/问题表	 前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/questionAnswer")
public class QuestionAnswerController {

    @Autowired
    IQuestionAnswerService questionAnswerService;

    /*
    * add
    * */
    @ApiOperation(value = "提出问题")
    @PostMapping("/adQue")
    public Result adQue(@RequestBody QuestionAnswer questionAnswer, HttpServletRequest request) {

        //加载敏感词
        Set<String> senstiveSet = FileUtil.getFileContent();
        senstiveSet.addAll(senstiveSet);
        SensitiveWordUtil.init(senstiveSet);
        //查找是否有敏感词
        boolean flag = SensitiveWordUtil.contains(questionAnswer.getContent(), SensitiveWordUtil.MinMatchTYpe);
        if(flag) {
            //获取语
            // 句中的敏感词
            Set<String> set = SensitiveWordUtil.getSensitiveWord(questionAnswer.getContent());
            System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "语句中包含敏感词的个数为：" + set.size(), set);
        }

        try {
            //生成问题d
            IdGeneratorOptions options = new IdGeneratorOptions((short)1);
            YitIdHelper.setIdGenerator(options);
            Long newId = YitIdHelper.nextId();
            questionAnswer.setId(newId/1000000);

            Long au_id = JwtUtil.getId(request);
            questionAnswer.setAuId(au_id);
        } catch (Exception e) {e.printStackTrace();}
        //设置时间
        Date date = new Date(); //获取当前时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        questionAnswer.setCreateTime(LocalDateTime.parse(formatter.format(date), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //教师默认为最佳回复
        questionAnswer.setIsSelect(1);
        if (!questionAnswerService.save(questionAnswer)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, "true");
    }

    /*
     * delete
     * */

    /*
     * update
     * */
    @ApiOperation(value = "审核问题")
    @GetMapping("/checkQue/{que_id}/{state}")
    public Result upQuestion(@PathVariable("que_id") Long queId, @PathVariable("state") Integer state) {
        Map<String, Object> map = new HashMap<>();
        map.put("queId", queId);
        map.put("state", state);
        if (!questionAnswerService.upQuestion(map)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, "true");
    }

    @ApiOperation(value = "批量审核问题")
    @PostMapping("/checkQues/{state}")
    public Result checkQues(@RequestBody List<Long> queIds, @PathVariable("state") Integer state) {
        Iterator<Long> iterator = queIds.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = new HashMap<>();
            map.put("queId", iterator.next());
            map.put("state", state);
            if (!questionAnswerService.upQuestion(map)) return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        }

        return Result.success(HttpStatus.SC_OK, "true");
    }

    /*
     * search
     * */

}

