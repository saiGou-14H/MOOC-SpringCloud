package com.org.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;

@Slf4j
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    //@Value("${logo-img.request-path}")
    private static String logoReqPath = "/file/**"; // 请求地址
    //@Value("${logo-img.local-path}")
    //private static String logoLocPath = "D:/mooc-mc/file"; // 本地存放资源目录的绝对路径

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //自己定义logoLocPath
        //获取根目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:static").getPath());
        } catch (FileNotFoundException e) {}
        if(!path.exists()) path = new File("");
        String src = "/user-8001/src/main/resources/static/file";
        File realPath = new File(path.getAbsolutePath(),src);
        if(!realPath.exists()) {
            realPath.mkdirs();
        }

        File logoDir = new File(String.valueOf(realPath));
        boolean flag = false;

        if (!logoDir.exists())
            flag = logoDir.mkdirs();
        if (flag)
            log.info("已成功创建资源 logo 目录：{}", realPath);

        log.info("getAbsolutePath = {}", logoDir.getAbsolutePath());
        log.info("getPath = {}", logoDir.getPath());

        registry.addResourceHandler(logoReqPath)
                .addResourceLocations("file:" + logoDir.getAbsolutePath() + File.separator);
    }

//    @Test
//    public void fun() throws FileNotFoundException {
//
//        //获取根目录
//        File path = new File(ResourceUtils.getURL("classpath:static").getPath());
//        if(!path.exists()) path = new File("");
//        String src = "/user-8001/src/main/resources/static/file";
//        File realPath = new File(path.getAbsolutePath(),src);
//        if(!realPath.exists()) {
//            realPath.mkdirs();
//        }
//        System.out.println("文件保存路径："+realPath);
//
//    }

}

