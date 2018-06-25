package dpcsys.consumption.dpcInterface.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dpcsys.api.dpcInterface.constants.DpcInterfaceResponseHashMap;
import dpcsys.api.dpcInterface.model.DfieldCheck;
import dpcsys.api.dpcInterface.vo.BaseParameterModel;
import dpcsys.consumption.dpcInterface.rabbitmq.service.MQProducer;
import dpcsys.consumption.frame.web.controller.BaseController;
import faner.dplatformSpringjdbc.api.frame.util.tools.date.DateCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;

/**
 * @version V2.0
 * @Company:faner
 * @author: lijianjun
 */
@Controller
@Scope("prototype")
@RequestMapping("/rabbitMq")
public class RabbitMqController extends BaseController {

    @Autowired
    private MQProducer mqProducer;

    final String queue_key = "demo";

    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/sendXX", method = RequestMethod.GET)
    public void sendXX(BaseParameterModel baseModel, DfieldCheck dfieldCheck) throws Exception {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("data", "hello,rabbmitmq!");
        StringBuffer data = new StringBuffer();
        for (int i = 0; i < 1024; i++) {
            data.append("1");
        }
        mqProducer.sendDataToQueue(queue_key, data.toString());
        log.info("写：" + DateCoreUtil.formatDate(new Date(), "HH:mm:ss sss"));
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    public static void main(String[] args) {

    }
}
