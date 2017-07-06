package hyx.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author hyx
 */
@Configuration
public class RabbitmqConfig {

    private static final String exchangeName = "exchange";

    /**
     * @return
     * @description spring 唯一实现的工厂类
     */
    @Bean
    public CachingConnectionFactory getCachingConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(getConnectionFactory());
        // CachingConnectionFactory factory = new
        // CachingConnectionFactory(getYzcConnectionFactory());
        return factory;
    }

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
     * @description init queue
     */
    private void initQueue(ConnectionFactory fac) {
        try {
            Connection c = null;
            Channel channel = null;
            try {
                c = fac.newConnection();
                channel = c.createChannel();
                // channel.queueDeclare("queue", false, false, false, null);
                // config binding exchange and queue
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
     * init exchange with type topic
     *
     * @param channel
     * @throws IOException
     */
    private void declareExchange(Channel channel) throws IOException {
        //第三个参数boolean表示是否持久化
        channel.exchangeDeclare(exchangeName, "topic");
    }

    /**
     * declare queue and binding to exchange
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

    @Bean(name = "anonymousListener")
    public MessageListener getMessageListener() {
        return new MessageListener() {
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


    }

    /**
     * @return
     * @description listener-container,spring实现的消息驱动的 amqp
     */
    @Bean
    @Autowired
    public SimpleMessageListenerContainer container(CachingConnectionFactory factory,
                                                    @Qualifier("anonymousListener") MessageListener anonymousListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setConcurrentConsumers(50);
        container.setMaxConcurrentConsumers(200);
        // this queue will be destroyed when connection is closed
        String[] queueNames = QueueHolder.getInstance().getQueuesAsArray();
        container.addQueueNames(queueNames);
        container.setMessageListener(anonymousListener);
        return container;
    }

    @Bean(name = "rabbitTemplate")
    @Autowired
    public RabbitTemplate getRabbitTemplate(CachingConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        return template;
    }


    /**
     * test connect to remote rabbitmq server
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
