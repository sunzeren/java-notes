package com.sun.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author by Sun, Date on 2018/9/6.
 * PS: Not easy to write code, please indicate.
 * Swagger2配置类
 * 在与spring boot集成时，放在与Application.java同级的目录下。
 * 通过@Configuration注解，让Spring来加载该类配置。
 * 再通过@EnableSwagger2注解来启用Swagger2。
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    // @Bean
    // public Docket docket() {
    //     return new Docket(DocumentationType.SWAGGER_2)
    //             .apiInfo(apiInfo())
    //             .select()
    //             .apis(RequestHandlerSelectors.basePackage("com.sun"))
    //             .paths(PathSelectors.any())
    //             .build();
    // }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    // private ApiInfo apiInfo() {
    //     //noinspection SpellCheckingInspection
    //     return new ApiInfoBuilder()
    //             .title("Spring Boot中使用Swagger2构建RESTful APIs")
    //             .description("更多请关注http://www.baidu.com")
    //             .termsOfServiceUrl("http://www.baidu.com")
    //             .contact("Sunzeren")
    //             .version("1.0")
    //             .build();
    // }
}
