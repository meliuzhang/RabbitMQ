package com.lb.Controller;

import com.lb.rabbitmq.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LB
 * @Remarks
 * @date 2019/12/07 17:32
 */
@RestController
public class ProducerController {

    @Autowired
    private FanoutProducer fanoutProducer;

    @RequestMapping("/sendFanout")
    public String sendFanout(String queueName) {
        fanoutProducer.send(queueName);
        return "success";
    }

}
