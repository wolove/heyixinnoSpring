package hyx.util.rabbitmq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author hyx
 * @description 消息驱动的amqp pojo,对接收到的消息进行处理
 */
@Component
public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		String result =null;
		try {
			result = new String(msg.getBody(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("i get message :" + result);
	}

}
