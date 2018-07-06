package com.pant.loki.entity;

import com.pant.loki.utils.StringUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 用于对应nginx info log的记录对象
 * @author pantao
 * @date 2018/7/6 10:29
 */
public class NginxInfo {

    private static final String REQUEST_REGEX = "(get|head|post|put|delete|connect|options|trace)\\s+(/.*)\\s+(.*)";
    private static final String RECORD_ID_KEY = "recordId";

    /**
     * 客户端IP地址
     */
    private String remoteAddr;

    /**
     * 它代表客户端，也就是HTTP的请求端真实的IP，
     * 只有在通过了HTTP 代理或者负载均衡服务器时才会添加该项
     */
    private String forwarded;

    /**
     * 请求状态
     */
    private String status;

    /**
     * 请求响应的总字节数，单位byte
     */
    private long bytesSent;

    /**
     * 请求从发起到结束
     */
    private String requestTime;

    /**
     * 包含: 请求方式 uri 协议类别
     */
    private String request;

    /**
     * 请求方法
     */
    private String method;

    /**
     * uri
     */
    private String uri;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 话单ID
     */
    private String recordId;

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getForwarded() {
        return forwarded;
    }

    public void setForwarded(String forwarded) {
        this.forwarded = forwarded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(long bytesSent) {
        this.bytesSent = bytesSent;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {

        Pattern pattern = Pattern.compile(REQUEST_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(request);
        if (matcher.find() && matcher.groupCount() >= 3) {
            this.setMethod(matcher.group(1));
            this.setUri(matcher.group(2));
            this.setProtocol(matcher.group(3));
        }
        this.request = request;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        Map<String, String> paramsMap = StringUtil.getParamsMap(uri);
        if (!paramsMap.isEmpty()) {
            this.setRecordId(paramsMap.get(RECORD_ID_KEY));
        }
        this.uri = uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
