package com.zcxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Horace Leoi
 * @date : 2023/12/20
 * @remark :
 */
@MapperScan({"com.zcxy.mapper", "com.zcxy.service"})
@SpringBootApplication
public class CompetitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompetitionApplication.class, args);
    }
}
