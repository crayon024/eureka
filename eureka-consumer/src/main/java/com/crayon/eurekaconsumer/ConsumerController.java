package com.crayon.eurekaconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Crayon
 * @date 2020/6/22
 * Description:
 */
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
//    @Autowired
//    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String consumer() {
        ServiceInstance helloService = loadBalancerClient.choose("hello-service");
        //调用 enreka-client 提供的 hello service
        String url = "http://" + helloService.getHost() + ":" + helloService.getPort() + "/hello";
        log.info("消费服务的 url：" + url);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
