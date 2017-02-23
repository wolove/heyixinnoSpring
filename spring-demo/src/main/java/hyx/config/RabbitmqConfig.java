package hyx.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import hyx.util.rabbitmq.MyMessageListener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitmqConfig {

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
	 * @param fac
	 * @description ��ʼ������
	 */
	private void initQueue(ConnectionFactory fac) {
		try {
			Connection c = null;
			Channel channel = null;
			try {
				c = fac.newConnection();
				channel = c.createChannel();
				channel.queueDeclare("queue", false, false, false, null);
			} finally {
				channel.close();
				c.close();
			}
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @description springΨһʵ�ֵĹ���
	 */
	@Bean
	public CachingConnectionFactory getCachingConnectionFactory() {
		CachingConnectionFactory factory = new CachingConnectionFactory(getConnectionFactory());
		return factory;
	}

	/**
	 * @param listener
	 * @return
	 * @description listener-container,Ϊ��ʵ����Ϣ������ amqp ���첽)
	 */
	@Bean
	@Autowired
	public SimpleMessageListenerContainer container(MyMessageListener listener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(getCachingConnectionFactory());
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		container.setConcurrentConsumers(50);
		container.setMaxConcurrentConsumers(200);
		container.setQueueNames("queue");
		container.setMessageListener(listener);
		
		return container;
	}
	
	
}
