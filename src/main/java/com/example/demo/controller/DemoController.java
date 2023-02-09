package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("demo")
public class DemoController {
    @Value("${server.port}")
    private String port;

    @GetMapping("getIp")
    public String test() {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            return "old:" + InetAddress.getLocalHost().getHostAddress() + ":" + port;
        } catch (UnknownHostException e) {
            return "error";
        }
    }
}
