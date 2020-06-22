package com.crayon.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Crayon
 * @date 2020/6/22
 * Description:
 */
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    public String hello() {
        for (String s :  client.getServices()) {
            System.out.println(s);
        }
        return "hello";
    }
}
