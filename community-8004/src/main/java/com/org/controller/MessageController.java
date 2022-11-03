package com.org.controller;


import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.org.model.Message;
import com.org.model.Result;
import com.org.service.IMessageService;
import com.org.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

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

    //@Autowired
    //private RestTemplate restTemplate;      //提供多种便捷访问远程http服务的方法，简单的Restful服务模板

    @Autowired
    IMessageService messageService;

    private static final String REST_URL_PREFIX = "http://USER-8001";

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
        if(!messageService.save(message)) return Result.success(HttpStatus.SC_INTERNAL_SERVER_ERROR, "请检查数据是否有误或个别数据是否为空");
        return Result.success(HttpStatus.SC_OK, "Ture");
    }

    /*
     *delete
     * */

    /*
     *update
     * */

    /*
     *search
     * */

}

