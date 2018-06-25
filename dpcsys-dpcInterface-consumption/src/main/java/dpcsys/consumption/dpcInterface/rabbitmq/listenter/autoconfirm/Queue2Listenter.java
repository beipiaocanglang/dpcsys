package dpcsys.consumption.dpcInterface.rabbitmq.listenter.autoconfirm;

import java.util.Date;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import faner.dplatformSpringjdbc.api.frame.util.tools.date.DateCoreUtil;

public class Queue2Listenter implements MessageListener {

    private static int count2 = 0;

    @Override
    public void onMessage(Message msg) {
        try {
            count2++;
            System.out.println("消费者2-----------data:" + new String(msg.getBody()) + ",count2:" + count2);
//			System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
//			for (int i = 0; i < 100000000; i++) {
//				// count++;
//			}
//			System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
//			// System.out.print(msg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
        for (int i = 0; i < 100000000; i++) {
            count2++;
        }
        System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
    }
}
