package dpcsys.consumption.dpcInterface.rabbitmq.service.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dpcsys.consumption.dpcInterface.rabbitmq.service.MQProducer;


@Service
public class MQProducerImpl implements MQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public void sendDataToQueue(String queueKey, String str) {
        try {
            amqpTemplate.convertAndSend(queueKey, str);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
    }
}
