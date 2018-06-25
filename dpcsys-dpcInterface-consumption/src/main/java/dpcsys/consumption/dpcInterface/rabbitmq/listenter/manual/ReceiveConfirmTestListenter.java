package dpcsys.consumption.dpcInterface.rabbitmq.listenter.manual;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

/**
 * 消费方监听，需要手动确认机制
 *
 * @author : lijianjun
 * @version V1.0
 * @Description: TODO
 * @Company:faner
 * @date: 2018年4月21日 下午1:34:22
 */
public class ReceiveConfirmTestListenter implements ChannelAwareMessageListener {

    private static int count = 0;

    @Override
    public void onMessage(Message msg, Channel channel) throws Exception {
        try {
            count++;
            System.out.println("业务处理中................................");
            System.out.println("data:" + new String(msg.getBody()) + ",count:" + count);
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//			e.printStackTrace();
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
