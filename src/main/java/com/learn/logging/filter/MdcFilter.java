package com.learn.logging.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * A servlet filter to add a transactionId to MDC for logging purposes.
 */
@Component
public class MdcFilter implements Filter {

    private static final String TRANSACTION_ID_KEY = "transactionId";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String transactionId = extractOrGenerateTransactionId(servletRequest);
            MDC.put(TRANSACTION_ID_KEY, transactionId);

            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(TRANSACTION_ID_KEY);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * Extracts transactionId from the request header or generates a new one.
     */
    private String extractOrGenerateTransactionId(ServletRequest servletRequest) {
        if (servletRequest instanceof HttpServletRequest httpRequest) {
            String headerTransactionId = httpRequest.getHeader("X-Transaction-Id");
            if (headerTransactionId != null && !headerTransactionId.isEmpty()) {
                return headerTransactionId;
            }
        }
        return UUID.randomUUID().toString();
    }
}
