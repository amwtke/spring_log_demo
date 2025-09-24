package com.example.mdc.web;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.UUID;

public class TraceMdcFilter implements Filter {
    public static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String incoming = (request instanceof HttpServletRequest req)
                    ? req.getHeader("X-Trace-Id") : null;
            String traceId = (incoming != null && !incoming.isBlank())
                    ? incoming
                    : UUID.randomUUID().toString().replace("-", "");
            MDC.put(TRACE_ID, traceId);
            chain.doFilter(request, response);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage());
        } finally {
            MDC.clear();
        }
    }
}
