package com.lumiaqian.images_service.Utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author qian
 * @version V1.0
 * @Title: IPUtil
 * @Package: com.lumiaqian.images_service.Utils
 * @Description: TOTO
 * @date 2019-04-28 16:18
 **/
public class IPUtil {

    /**
     * @Param: HttpServletRequest
     * @return: String
     * @Author: qian
     * @Description: 获取客户端ip
     * @Date: 2018/9/1 下午4:16
     **/
    public final static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }
}
