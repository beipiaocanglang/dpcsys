package dpcsys.core.dpcInterface.service.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import dpcsys.api.dpcInterface.service.MQProducer;

public class MQProducerImpl implements MQProducer{
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void sendDataToQueue(String exchange,String queueKey, String str) {
		 try {
	            amqpTemplate.convertAndSend(exchange,queueKey, str);
	        } catch (Exception e) {
	        	log.error(e);
	        	e.printStackTrace();
	        }
	}
}
