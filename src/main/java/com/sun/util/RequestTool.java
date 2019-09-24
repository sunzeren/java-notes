package com.sun.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求相关工具
 */
public class RequestTool {

    /**
     * 获取当前请求会话ID
     *
     * @return
     */
    public static String getCurrentSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    /**
     * 获取当前请求
     *
     * @return 请求信息
     */
    public static HttpServletRequest getCurrentServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取当前媒体请求
     *
     * @return 请求信息
     */
    public static MultipartHttpServletRequest getCurrentMultipartServletRequest() {
        HttpServletRequest servletRequest = getCurrentServletRequest();
        MultipartResolver multipartResolver = new StandardServletMultipartResolver();
        if (servletRequest != null && multipartResolver.isMultipart(servletRequest)) {
            return multipartResolver.resolveMultipart(servletRequest);
        }
        return null;
    }

    /**
     * 获取当前响应
     *
     * @return 响应信息
     */
    public static HttpServletResponse getCurrentServletResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getResponse();
    }

    /**
     * 获取指定请求完整地址
     *
     * @param request 指定请求
     * @return
     */
    public static String getRequestURL(HttpServletRequest request) {
        String requestScheme = request.getScheme();
        int serverPort = request.getServerPort();
        String protocolHeader = request.getHeader("X-Forwarded-Proto");
        if (StringUtils.isNotEmpty(protocolHeader)) {
            requestScheme = protocolHeader.trim();
            if ("http".equalsIgnoreCase(requestScheme)) {
                serverPort = 80;
            } else if ("https".equalsIgnoreCase(requestScheme)) {
                serverPort = 443;
            }
        }
        StringBuffer url = (new StringBuffer(requestScheme)).append("://").append(request.getServerName());
        if (serverPort > 0 && ("http".equalsIgnoreCase(requestScheme) && serverPort != 80 || "https".equalsIgnoreCase(requestScheme) && serverPort != 443)) {
            url.append(':').append(serverPort);
        }
        if (StringUtils.isNotEmpty(request.getRequestURI())) {
            url.append(request.getRequestURI());
        }
        return url.toString();
    }

    /**
     * 获取指定请求客户端IP地址
     *
     * @param request 指定请求
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip != null && ip.length() != 0) {
            ip = ip.split(",")[0];
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取指定请求协议
     *
     * @param request 指定请求
     * @return
     */
    public static String getScheme(HttpServletRequest request) {
        String pro = request.getHeader("X-Forwarded-Proto");
        if (pro == null || pro.length() == 0 || "unknown".equalsIgnoreCase(pro)) {
            pro = request.getScheme();
        }
        return pro;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        return uri.endsWith(".json");
    }

}
