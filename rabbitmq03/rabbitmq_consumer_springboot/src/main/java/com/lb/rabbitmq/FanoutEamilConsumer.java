package com.lb.rabbitmq;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;

//�ʼ�����
@Component
public class FanoutEamilConsumer {

	// rabbitmq Ĭ������� ��������߳�������쳣������£����Զ�ʵ�ֲ�������
	// ���������Ի��ƣ� ���з����� ���Ͳ�������
	// ������Ѷ� ����ҵ���߼������쳣��Ϣ�����ѳɹ���
	// @RabbitListener(queues = "fanout_email_queue")
	// public void process(String msg) throws Exception {
	// System.out.println("�ʼ������߻�ȡ��������Ϣmsg:" + msg);
	// JSONObject jsonObject = JSONObject.parseObject(msg);
	// String email = jsonObject.getString("email");
	// String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
	// System.out.println("�ʼ������߿�ʼ���õ������ʼ�������,emailUrl:" + emailUrl);
	// JSONObject result = HttpClientUtils.httpGet(emailUrl);
	// // ������õ������ʼ��ӿ��޷����ʣ����ʵ���Զ�����.
	// if (result == null) {
	// throw new Exception("���õ������ʼ��������ӿ�ʧ��!");
	// }
	// System.out.println("�ʼ������߽������õ������ʼ��������ɹ�,result:" + result + "����ִ�н���");
	//
	// }
	// @RabbitListener �ײ� ʹ��Aop�������أ��������û���׳��쳣���Զ��ύ����
	// ���Aopʹ���쳣֪ͨ���� ��ȡ�쳣��Ϣ�Ļ����Զ�ʵ�ֲ������� ������Ϣ�Ỻ�浽rabbitmq�������˽��д�ţ�һֱ���Ե������쳣Ϊ׼��

	// �޸����Ի��Ʋ��� һ��Ĭ������� ���5������һ��

	// MQ���Ի�����Ҫע�������
	// MQ�������ݵ���������ν����ʹ��ȫ��ID

	// @RabbitListener(queues = "fanout_email_queue")
	// public void process(Message message, @Headers Map<String, Object>
	// headers, Channel channel) throws Exception {
	// String messageId = message.getMessageProperties().getMessageId();
	// String msg = new String(message.getBody(), "UTF-8");
	// System.out.println("�ʼ������߻�ȡ��������Ϣmsg:" + msg + ",��Ϣid:" + messageId);
	// // ���Ի��ƶ��Ǽ����
	//
	// JSONObject jsonObject = JSONObject.parseObject(msg);
	// String email = jsonObject.getString("email");
	// String emailUrl = "http://127.0.0.1:8083/sendEmail?email=" + email;
	// System.out.println("�ʼ������߿�ʼ���õ������ʼ�������,emailUrl:" + emailUrl);
	// JSONObject result = HttpClientUtils.httpGet(emailUrl);
	// // ������õ������ʼ��ӿ��޷����ʣ����ʵ���Զ�����.
	// if (result == null) {
	// throw new Exception("���õ������ʼ��������ӿ�ʧ��!");
	// }
	// System.out.println("�ʼ������߽������õ������ʼ��������ɹ�,result:" + result + "����ִ�н���");
	// // �ֶ�ack
	// Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
	// // �ֶ�ǩ��
	// channel.basicAck(deliveryTag, false);
	//
	// }
	// Ĭ�����Զ�Ӧ��ģʽ
	@RabbitListener(queues = "fanout_email_queue")
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
		String messageId = message.getMessageProperties().getMessageId();
		String msg = new String(message.getBody(), "UTF-8");
		System.out.println("�ʼ������߻�ȡ��������Ϣmsg:" + msg + ",��Ϣid:" + messageId);
		JSONObject jsonObject = JSONObject.parseObject(msg);
		Integer timestamp = jsonObject.getInteger("timestamp");
		try {
			int result = 1 / timestamp;
			System.out.println("result:" + result);
			// ֪ͨmq������ɾ������Ϣ
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			e.printStackTrace();
			// // ��������Ϣ
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		}

	}

}
