package com.neu.music.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: Neusoft
 * @Date: 2021/10/24 18:35
 */
@Configuration
public class SwaggerConfig {
    //微信端
    @Bean
    public Docket wxRestApi(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Shopping-Token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return createRestApi("微信端",PathSelectors.regex("/.*"),pars);
    }
    //管理端
    @Bean
    public Docket adminRestApi(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Shopping-Admin-Token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return createRestApi("管理端",PathSelectors.regex("/admin/.*"),pars);
    }

    /**
     * 注入一个Springfox framework主要的构建器Docket进入Spring容器
     *
     * @date 2019/1/8 22:31
     */
    public Docket createRestApi(String groupName, Predicate<String> paths,List<Parameter> pars) {
        return new Docket(DocumentationType.SWAGGER_2)
                // API基础信息
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                /* 指定swagger2的“扫描”范围，假设指定的basePackage为xxx,那么凡是以xxx开头的包，都属于
                 * 其管辖范围(注:源代码中是以startsWith实现的）
                 * 注:指定此配置后，swagger2会自动扫描并发现该范围下的被@RequestMapping注解注解了的方法并生成对应的API
                 * 注:@GetMapping、@PostMapping、@PutMapping等注解也属于@RequestMapping注解的一种变形
                 */
                .apis(RequestHandlerSelectors.basePackage("com.neu.music.controller"))
                .paths(paths)
                .build()
                .globalOperationParameters(pars);
    }

    /**
     * Api基础信息模型
     *
     * @date 2019/1/8 22:31
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("易达_前台api文档")
                // 创建人信息
                //.contact(new Contact("shixinyu", "https://blog.csdn.net/justry_deng", "neuedu"))
                // 版本号
                .version("1.0")
                // 描述
                .description("")
                .build();
    }
}
