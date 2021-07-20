package com.saic.msw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zzz
 * @date 2021/7/20 21:47
 * @description：
 * @version: 1.0
 */

@SpringBootApplication
@MapperScan("com.saic.msw.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
