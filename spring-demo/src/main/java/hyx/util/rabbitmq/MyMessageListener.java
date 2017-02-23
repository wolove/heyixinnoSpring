package hyx.util.rabbitmq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author hyx
 * @description ��Ϣ������amqp pojo,�Խ��յ�����Ϣ���д���
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
