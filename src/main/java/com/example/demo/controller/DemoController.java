package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("getIp")
    public String test() {
        try {
            return "new:" + InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "error";
        }
    }
}
