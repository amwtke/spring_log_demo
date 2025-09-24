package com.example.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mdc.service.GreetingAuditService;

@SpringBootApplication
@RestController
public class DemoMdcApplication {
    private static final Logger log = LoggerFactory.getLogger(DemoMdcApplication.class);
    private final GreetingAuditService greetingAuditService;

    public DemoMdcApplication(GreetingAuditService greetingAuditService) {
        this.greetingAuditService = greetingAuditService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoMdcApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "world") String name) {
        log.info("handling /hello request");
        greetingAuditService.logGreeting(name);
        return "hello " + name;
    }
}
