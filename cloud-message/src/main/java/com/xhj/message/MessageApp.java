package com.xhj.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xhj
 * @date 2023年10月26日 15:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MessageApp {
    public static void main(String[] args) {
        SpringApplication.run(MessageApp.class,args);
    }
}
