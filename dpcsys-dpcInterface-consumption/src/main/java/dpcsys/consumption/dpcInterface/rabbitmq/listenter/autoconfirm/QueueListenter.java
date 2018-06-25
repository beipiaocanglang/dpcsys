package dpcsys.consumption.dpcInterface.rabbitmq.listenter.autoconfirm;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import faner.dplatformSpringjdbc.api.frame.util.tools.date.DateCoreUtil;

public class QueueListenter implements MessageListener {

    private static int count = 0;
    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void onMessage(Message msg) {
        try {
            count++;
            System.out.println("消费者1-----------data:" + /*new String(msg.getBody()) +*/ ",count:" + count);
//			System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
//			for (int i = 0; i < 100000000; i++) {
//				// count++;
//			}
//			System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
            // System.out.print(msg.toString());
            log.info("读：" + DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
        for (int i = 0; i < 100000000; i++) {
            count++;
        }
        System.out.println(DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
    }

}
