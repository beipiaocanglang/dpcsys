package dpcsys.api.dpcInterface.service;

public interface MQProducer {
    /**
     * 发送消息到指定队列
     *
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String exchange, String queueKey, String str);
}
