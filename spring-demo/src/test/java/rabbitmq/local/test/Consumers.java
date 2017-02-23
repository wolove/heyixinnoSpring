package rabbitmq.local.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;

/**
 * @author hyx
 * @description 接收特定routingkey的消费者，原生rabbitmq代码实现
 */
public class Consumers {
	public static void main(String[] args) throws IOException, TimeoutException {
		// 配置连接
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();

		// 声明exchange
		String exchangeName = "x";
		String exchangeType = "direct";
		channel.exchangeDeclare(exchangeName, exchangeType);
		// 声明queue(名字由server来起)
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, exchangeName, "routekey");

		// consumer一次只处理一个消息
		channel.basicQos(1);

		Consumer consumer = new DefaultConsumer(channel) {

			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				System.out.println("receive msg" + new String(body));
			}
		};
		boolean isAutoAck = true;
		channel.basicConsume(queueName, isAutoAck, consumer);
	}
}
