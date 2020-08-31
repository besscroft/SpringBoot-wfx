package com.bess.springboot.wfx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.bess.springboot.wfx.dao")
@ServletComponentScan(basePackages = "com.bess.springboot.wfx.filter")
public class SpringbootWfxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWfxApplication.class, args);
    }

}
