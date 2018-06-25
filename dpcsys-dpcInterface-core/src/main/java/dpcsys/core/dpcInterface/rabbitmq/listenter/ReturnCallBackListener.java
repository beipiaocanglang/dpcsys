package dpcsys.core.dpcInterface.rabbitmq.listenter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

public class ReturnCallBackListener implements ReturnCallback{

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText,
			String exchange, String routingKey) {
		System.out.println("处理失败回调.............................");
		System.out.println("return--message:"+new String(message.getBody())+",replyCode:"+replyCode+",replyText:"+replyText+",exchange:"+exchange+",routingKey:"+routingKey);
	}

}
