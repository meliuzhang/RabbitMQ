package com.lb.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.management.MXBean;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.amqp.core.Queue;

/**
 * @author LB
 * @Remarks
 * @date 2019/12/07 17:33
 */

//Fanout 类型 发布订阅模式
@Component
public class FanoutConfig {

    // 队列名称
    private String FANOUT_EMAIL_QUEUE = "fanout_eamil_queue";

    // 队列名称
    private String FANOUT_SMS_QUEUE = "fanout_sms_queue";

    // 交换机名称
    private String EXCHANGE_NAME = "fanoutExchange";

    //1.定义一个队列
    @Bean
    public Queue fanOutEamilQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE);
    }

    //定义第二个队列
    @Bean
    public Queue fanOutSmsQueue() {
        // arg0：队列名称
        // arg1：是否持久化
        // arg2：是否排外
        // arg3：关闭连接时队列是否自动删除
        // arg4：队列其他参数
                //- Message TTL 设置消息生命周期
                //- Auto Expire 当队列在指定的时间没有被访问就会被删除
                //- Max Length 限定队列的消息的最大值长度，超过指定长度将会把最早的几条删除掉
                //- Max Length Bytes 限定队列最大占用的空间大小
                //- DLX 当队列消息长度大于最大长度、或者过期的等，将从队列中删除的消息推送到指定的交换机中去而不是丢弃掉
                //- DLK 将删除的消息推送到指定交换机的指定路由键的队列中去
                //- Maximum priority 声明优先级队列
                //- Lazy mode 先将消息保存到磁盘上，不放在内存中，当消费者开始消费的时
        return new Queue(FANOUT_SMS_QUEUE);
    }

    //arg4:队列其他参数设置


    // 2.定义一个交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    //将交换机与第一个队列进行绑定
    @Bean
    Binding bindingExchangeEamil(Queue fanOutEamilQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutEamilQueue).to(fanoutExchange);
    }

    //将交换机与第二个队列进行绑定
    @Bean
    Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
    }


}
