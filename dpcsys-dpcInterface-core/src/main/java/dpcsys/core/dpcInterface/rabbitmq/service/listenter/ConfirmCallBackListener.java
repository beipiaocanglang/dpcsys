package dpcsys.core.dpcInterface.rabbitmq.service.listenter;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class ConfirmCallBackListener implements ConfirmCallback{

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("service 处理成功回调.............................");
		System.out.println("confirm--:correlationData:"+correlationData+",ack:"+ack+",cause:"+cause);  
	}

}
