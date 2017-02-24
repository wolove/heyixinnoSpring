package hyx.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import hyx.util.rabbitmq.MyMessageListener;
import hyx.util.rabbitmq.QueueHolder;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author hyx
 *
 */
@Configuration
public class RabbitmqConfig {

	private static final String exchangeName = "exchange";

	private ConnectionFactory getConnectionFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		initQueue(factory);
		return factory;
	}

	/**
	 * @return
	 * @description spring唯一实现的工厂
	 */
	@Bean
	public CachingConnectionFactory getCachingConnectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory(getConnectionFactory());
		// CachingConnectionFactory factory = new
		// CachingConnectionFactory(getYzcConnectionFactory());
		return factory;
	}

	/**
	 * @param fac
	 * @description 初始化队列
	 */
	private void initQueue(ConnectionFactory fac) {
		try {
			Connection c = null;
			Channel channel = null;
			try {
				c = fac.newConnection();
				channel = c.createChannel();
				// channel.queueDeclare("queue", false, false, false, null);
				// 尝试binding exchange和queue
				declareExchange(channel);
				declareQueue(channel);
			} finally {
				channel.close();
				c.close();
			}
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 声明exchange（topic）
	 * 
	 * @param channel
	 * @throws IOException
	 */
	private void declareExchange(Channel channel) throws IOException {
		channel.exchangeDeclare(exchangeName, "topic");
	}

	/**
	 * 创建队列并进行binding
	 * 
	 * @param channel
	 * @throws IOException
	 */
	private void declareQueue(Channel channel) throws IOException {
		String queueName = UUID.randomUUID().toString();
		channel.queueDeclare(queueName, false, false, false, null);
		QueueHolder.getInstance().addQueue(queueName);
		channel.queueBind(queueName, exchangeName, "routekey");
	}

	/**
	 * @param listener
	 * @return
	 * @description listener-container,为了实现消息驱动的 amqp （异步)
	 */
	@Bean
	@Autowired
	public SimpleMessageListenerContainer container(MyMessageListener listener, CachingConnectionFactory factory,
			@Qualifier("anonymousListener") MessageListener AnonymousListener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(factory);
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		container.setConcurrentConsumers(50);
		container.setMaxConcurrentConsumers(200);
		// 将queue放入container进行监听
		String[] queueNames = QueueHolder.getInstance().getQueuesAsArray();
		container.addQueueNames(queueNames);
		container.setMessageListener(AnonymousListener);
		return container;
	}

	@Bean(name = "rabbitTemplate")
	@Autowired
	public RabbitTemplate getRabbitTemplate(CachingConnectionFactory factory) {
		RabbitTemplate template = new RabbitTemplate(factory);
		return template;
	}

	@Bean("anonymousListener")
	public MessageListener getMessageListener() {
		MessageListener ml = new MessageListener() {

			@Override
			public void onMessage(Message msg) {
				String s = "";
				try {
					s = new String(msg.getBody(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				System.out.println(s);
			}
		};

		return ml;
	}

	/**
	 * 测试用配置
	 * 
	 * @return
	 */
	private ConnectionFactory getYzcConnectionFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.253.118");
		factory.setUsername("hyx");
		factory.setPassword("123456");
		factory.setVirtualHost("/");
		initQueue(factory);
		return factory;
	}
}
