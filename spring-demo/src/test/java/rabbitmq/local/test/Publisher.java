package rabbitmq.local.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author hyx
 * @Require ��Ҫ���Ȱ�װrabbitmq Server
 */
public class Publisher {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		//��Զ�̽�����Ϣ����
		// factory.setPort(5672);
		// factory.setHost("192.168.253.118");
		// factory.setUsername("hyx");
		// factory.setPassword("123456");
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		String mesg = "������";
		/** ʹ��exchange routekey ����Ϣ���з��� */
		// channel.exchangeDeclare("x", "direct");
		// channel.basicPublish("x", "routekey", null, mesg.getBytes());

		// ֱ������н��з��ͣ����������consumer�˽�������
		// ����Ķ�������spring�����õ�
		channel.basicPublish("", "queue", null, mesg.getBytes("utf-8"));
		channel.close();
		conn.close();
	}
}
