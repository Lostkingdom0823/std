package com.biz.std.util;

import org.springframework.stereotype.Component;

@Component
public class HostLink {

    private  String hostIp="119.23.32.233";
    private  String hostPost="8888";
    private  String prefixUrl = "/usr/local/tomcat/webapps/std";

    public String getHostIp() {
        return hostIp;
    }

    public String getHostPost() {
        return hostPost;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }
}
