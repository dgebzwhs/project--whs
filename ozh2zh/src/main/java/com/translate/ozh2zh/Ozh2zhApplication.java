package com.translate.ozh2zh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.translate.ozh2zh.Mapper")
@EnableSwagger2
public class Ozh2zhApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ozh2zhApplication.class, args);
    }

}
