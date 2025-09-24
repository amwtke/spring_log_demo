package com.example.mdc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class GreetingAuditService {
    private static final Logger log = LoggerFactory.getLogger(GreetingAuditService.class);

    @Async("mdcExecutor")
    public void logGreeting(String name) {
        log.info("异步处理", kv("name", name));
    }
}
