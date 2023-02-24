package com.org;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import io.swagger.annotations.Api;

import java.util.Collections;

/**
 * Author: B.M
 * Email: 2105584602@qq.com
 * CreateTime: 2022/5/31
 * Desc:代码自动生成
 */
@Api(value = "代码自动生成器")
public class AutomaticCode {

    public static void main(String[] args) {
        String url="jdbc:p6spy:mysql://175.178.189.140:3306/myapp?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC&allowMultiQueries=true&allowPublicKeyRetrieval=true";

        FastAutoGenerator.create(url,"rjbf4","123456")
                /*全局配置*/
                .globalConfig(builder -> {
                    builder.author("B.M") // 设置作者
                            .disableOpenDir()       //禁止打开输出目录
                            .enableSwagger() //开启 swagger 模式
                            .commentDate("yyyy-MM-dd")          //注释日期
                            .outputDir("F:\\综合实训\\mooc-sc\\course-8003\\src\\main\\java");// 指定输出目录
                })
                /*包配置*/
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("org") // 设置父包模块名
                            .entity("model")    //实体类包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,"F:\\综合实训\\mooc-sc\\course-8003\\src\\main\\resources\\mapper"));
                })
                //  .templateEngine(new FreemarkerTemplateEngine()) 使用Freemarker引擎模板，默认的是Velocity引擎模板
                /*策略配置*/
                .strategyConfig(builder -> {
                    builder.addInclude("m_course_chapter") // 设置需要生成的表名
                            .addTablePrefix("m_","t_") ; // 设置过滤表前缀
                    /*Enitity策略配置*/
                    builder.entityBuilder()
                            .enableLombok()             //开启lombok
                            .enableChainModel()         //开启链式
                            .enableTableFieldAnnotation()   //开启生成实体时生成字段注解
                            .versionColumnName("version")	//乐观锁字段名(数据库)
                            .versionPropertyName("version")	//乐观锁属性名(实体)
                            .logicDeleteColumnName("deleted")	//逻辑删除字段名(数据库)
                            .logicDeletePropertyName("deleted")	//逻辑删除属性名(实体)
                            .naming(NamingStrategy.underline_to_camel);   //默认下划线转驼峰命名
                            //.idType(IdType.AUTO);
                    /*Controller策略配置*/
                    builder.controllerBuilder()
                            .enableRestStyle();      //开启生成@RestController 控制器
                    /*Mapper策略配置*/
                    builder.mapperBuilder()
                            .enableMapperAnnotation();   //开启 @Mapper 注解
                })

                .execute();                     //执行
    }

}

