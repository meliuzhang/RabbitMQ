package com.lb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LB
 * @Remarks
 * @date 2019/12/07 20:17
 */
@SpringBootApplication
public class AppProducer {
    public static void main(String[] args) {
        SpringApplication.run(AppProducer.class, args);
        System.out.println("启动成功" );
    }

}
