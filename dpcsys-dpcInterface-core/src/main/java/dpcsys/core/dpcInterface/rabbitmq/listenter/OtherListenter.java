package dpcsys.core.dpcInterface.rabbitmq.listenter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import dpcsys.api.dpcInterface.service.MQProducer;

public class OtherListenter implements ChannelAwareMessageListener {

	@Autowired
	private MQProducer mqProducer;
	
	@Override
	public void onMessage(Message msg, Channel channel) throws Exception {
		try {
			try {
				String body = new String(msg.getBody());
				System.out.println("消费者2--data:-----------"+ body );
				//手动进行应答
				channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		   if (msg.getMessageProperties().getRedelivered()){
                System.out.println("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(msg.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
            }else{
                System.out.println("消息即将再次返回队列处理...");
            	// requeue为是否重新回到队列  队列头部
//                channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true); 
                //重新发送消息到队尾
                channel.basicPublish(msg.getMessageProperties().getReceivedExchange(),
                		msg.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
                        JSON.toJSONBytes(new Object()));
            }
		}
	}
}
