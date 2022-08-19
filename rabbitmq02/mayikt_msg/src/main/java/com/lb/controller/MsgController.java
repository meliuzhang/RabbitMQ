package com.lb.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LB
 * @Remarks
 * @date 2019/12/07 20:19
 */

@SpringBootApplication
@RestController
public class MsgController {

    // 模拟第三方发送邮件
    @RequestMapping("/sendEmail")
    public Map<String, Object> sendEmail(String email) {
        System.out.println("开始发送邮件:" + email);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "200");
        result.put("msg", "发送邮件成功..");
        System.out.println("发送邮件成功");
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(MsgController.class, args);
    }

}
