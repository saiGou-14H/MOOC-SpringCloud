package com.org.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.org.config.utils.FileUtil;
import com.org.model.Result;
import com.org.model.Sensitive;
import com.org.service.ISensitiveService;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author B.M
 * @since 2022-11-20
 */
@RestController
@RequestMapping("/sensitive")
public class SensitiveController {

    @Autowired
    ISensitiveService sensitiveService;

    /*
    * add
    */
    @ApiOperation("添加敏感词")
    @GetMapping("/adSensitive")
    public Result adSensitive(@RequestParam("word") String word) {

        if (!sensitiveService.list(new QueryWrapper<Sensitive>().eq("word", word)).isEmpty())
            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "词库已存在该敏感词");
        if (!sensitiveService.save(new Sensitive().setWord(word)))
            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");

        return Result.success(HttpStatus.SC_OK, "true");
    }
//    @GetMapping("/fun")
//    public Result fun() throws InterruptedException {
//        Set<String> set = FileUtil.getFileContent();
//        List<String> StringList = new ArrayList<>();
//        for (String s: set) {
//            StringList.add(s);
//        }
//
//        List<Sensitive> sensitiveList = new ArrayList<>();
//        for (int i=1; i<set.size(); i++) {
//            sensitiveList.add(new Sensitive().setWord(StringList.get(i)));
//            if (i%100==0) {
//                sensitiveService.saveBatch(sensitiveList);
//                Thread.sleep(4000);
//                sensitiveList.clear();
//                Thread.sleep(1000);
//            }
//        }
//        sensitiveService.saveBatch(sensitiveList);
//
//        return Result.success();
//    }

    /*
     * delete
     */
    @ApiOperation("删除敏感词")
    @GetMapping("/delSensitive/{id}")
    public Result delSensitive(@PathVariable("id") Integer id) {
        if (!sensitiveService.remove(new QueryWrapper<Sensitive>().eq("id", id)))
            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, "true");
    }

    /*
     * update
     */
    @ApiOperation("修改敏感词")
    @GetMapping("/udSensitive/{id}")
    public Result udSensitive(@RequestParam("word") String word, @PathVariable Integer id) {
        if(!sensitiveService.update(new Sensitive().setWord(word), new UpdateWrapper<Sensitive>().eq("id", id)))
            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
        return Result.success(HttpStatus.SC_OK, "true");
    }

    //@Scheduled(cron = "0/5 * * * * *")
    @ApiOperation("更新敏感词库")
    @GetMapping("/udSensitive")
    public Result udSensitive() throws IOException {

        File temPath = new File(ResourceUtils.getURL("").getPath());
        String path = temPath.getAbsolutePath().replace("\\community-8004", "")+"/SensitiveWord.txt";

        List<String> wordList = sensitiveService.shWord();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for(String word: wordList) {
                bw.write(word);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "更新失败");}
        finally {bw.close();}

        return Result.success(HttpStatus.SC_OK, "true");
    }

    /*
     * search
     */
    @ApiOperation("查看敏感词")
    @GetMapping("/shSensitive")
    public Result shWord(@RequestParam("word") String word) {
        List<Sensitive> sensitiveList = sensitiveService.shSensitive(word);
        if (sensitiveList.isEmpty())
            return Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "false");
//        List<String> wordList = sensitiveService.shWord();
        return Result.success(HttpStatus.SC_OK, "true", sensitiveList);
    }

}

