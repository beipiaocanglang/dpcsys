package dpcsys.core.dpcInterface.rabbitmq.service.listenter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RealTimeListenter implements MessageListener  {

	private static int count2 = 0;

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("service --data: ,count2:" + count2+"-----------"+ new String(message.getBody()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
