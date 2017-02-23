package rabbitmq.local.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author hyx
 * @Require 需要事先安装rabbitmq Server
 */
public class Publisher {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		//向远程进行消息发送
		// factory.setPort(5672);
		// factory.setHost("192.168.253.118");
		// factory.setUsername("hyx");
		// factory.setPassword("123456");
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		String mesg = "搞事情";
		/** 使用exchange routekey 对消息进行发送 */
		// channel.exchangeDeclare("x", "direct");
		// channel.basicPublish("x", "routekey", null, mesg.getBytes());

		// 直接向队列进行发送，队列最好在consumer端进行声明
		// 这里的队列是在spring里配置的
		channel.basicPublish("", "queue", null, mesg.getBytes("utf-8"));
		channel.close();
		conn.close();
	}
}
