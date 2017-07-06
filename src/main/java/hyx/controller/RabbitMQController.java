package hyx.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    RabbitTemplate template;

    @RequestMapping(value = "/rabbit/sent", method = RequestMethod.POST)
    public String sent(@RequestParam String msg) {
        //这个exchange和routekey是要和declare的相同
        template.convertAndSend("exchange", "routekey", msg);
        return msg;
    }
}
