package edu.icet.config.logFileConfiguration;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestWrapper extends HttpServletRequestWrapper {

    private final byte[] cachedBody;
    private final Map<String, String> headersMap;
    private ByteArrayInputStream byteArrayInputStream;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);


        InputStream inputStream = request.getInputStream();
        this.cachedBody = inputStream.readAllBytes();


        byteArrayInputStream = new ByteArrayInputStream(cachedBody);


        this.headersMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }
    }


    @Override
    public String getHeader(String name) {
        return headersMap.get(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return java.util.Collections.enumeration(headersMap.keySet());
    }


    public String getRequestBody() {
        return new String(cachedBody);
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cachedBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {}

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
