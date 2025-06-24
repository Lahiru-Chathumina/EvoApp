package edu.icet.config.logFileConfiguration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor

public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);
    private final MonthlyLogWriter monthlyLogWriter;

    public RequestResponseLoggingFilter() {
        this.monthlyLogWriter = new MonthlyLogWriter();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, responseWrapper);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequestResponse(request, responseWrapper, duration);
            responseWrapper.flushResponse();
        }
    }

    private void logRequestResponse(HttpServletRequest request, ResponseWrapper responseWrapper, long duration)
            throws IOException {


        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[").append(request.getMethod()).append("] ")
                .append(request.getRequestURI())
                .append(" - Status: ").append(responseWrapper.getStatus())
                .append(" - Duration: ").append(duration).append("ms")
                .append(" - Client: ").append(request.getRemoteAddr());


        logger.info(logMessage.toString());


        StringBuilder detailedLog = new StringBuilder();
        detailedLog.append("\n=== Request/Response ===\n")
                .append("Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("\n")
                .append("Method: ").append(request.getMethod()).append("\n")
                .append("URI: ").append(request.getRequestURI()).append("\n")
                .append("Client IP: ").append(request.getRemoteAddr()).append("\n")
                .append("Headers: ").append(getRequestHeaders(request)).append("\n")
                .append("Response Status: ").append(responseWrapper.getStatus()).append("\n")
                .append("Response Body: ").append(abbreviate(responseWrapper.getResponseBody(), 200)).append("\n")
                .append("Processing Time: ").append(duration).append("ms\n");


        monthlyLogWriter.log(detailedLog.toString());
    }

    private String getRequestHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("; ");
        });
        return headers.length() > 0 ? headers.substring(0, headers.length() - 2) : "none";
    }

    private String abbreviate(String text, int maxLength) {
        if (text == null) return "null";
        return text.length() <= maxLength ? text : text.substring(0, maxLength) + "...[truncated]";
    }
}