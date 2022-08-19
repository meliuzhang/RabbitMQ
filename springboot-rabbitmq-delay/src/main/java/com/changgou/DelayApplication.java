package com.changgou;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * @author LB
 * @Remarks
 * @date 2019/08/31 10:25
 */
@SpringBootApplication
public class DelayApplication {
    public static void main(String[] args) {
        SpringApplication.run(DelayApplication.class, args);
        System.out.println("启动成功");
    }

    //创建普通队列
    @Bean
    public Queue messageQueue() {
        return new Queue("message.queue", true);
    }

    //创建死信队列
    /**
     * 创建队列时传入两个参数就会成为死信队列，第一个是将死信转发到Exchange，第二个是Routing key
     * @return
     */
    @Bean
    public Queue delaymessageQueue() {
        //声明消息变成死信后,交给的交换机和路由键
        return QueueBuilder.durable("delay.message.queue")
                .withArgument("x-dead-letter-exchange", "dlx.exchange")//第一个是将死信转发到Exchange
                .withArgument("x-dead-letter-routing-key", "message.queue")//第二个是Routing key
                .build();
    }

    // 创建交换机
    @Bean
    public Exchange delayExchange() {
        return new DirectExchange("dlx.exchange");
    }

    // 将队列绑定到交换机
    @Bean
    public Binding bindingBuilder(Queue messageQueue, Exchange delayExchange) {
        return BindingBuilder.bind(messageQueue).to(delayExchange).with("message.queue").noargs();
    }

}
