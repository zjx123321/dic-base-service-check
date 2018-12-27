package com.jdddata.dic.base.service.check.muti.source.config;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by zhengjiexiang on 2018/12/21
 */
public class Request {

    private Map<String, String> header;

    private Map<String, String> body;

    public Request() {
        header = Maps.newConcurrentMap();
        header.put("appVersion", "1.0.0");
        header.put("idfa", "");
        header.put("cmdName", "app_zz");
        header.put("userID", "zjx");
        header.put("uuid", "ffffffff-d5f8-bf85-1f91-171a0033c587");
        header.put("token", "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJqc2NwIiwiaXNzIjoiamRkLmNvbSJ9.eyJ1c2VyVHlwZSI6MSwidXNlcmlkIjoxNiwidXVpZCI6ImZmZmZmZmZmLWQ1ZjgtYmY4NS0xZjkxLTE3MWEwMDMzYzU4NyJ9.7a37c4913e1940192f3249cf3b5f10f5.ZDhmMDMwODMtNGY2ZS00ZmRjLTkwODQtMThjMTU3OWY4YzE1");
        header.put("cmdID", "0");
        header.put("platformVersion", "5.1.1");
        header.put("action", "110");
        header.put("imei", "865800028773239");
        header.put("userType", "1");
        header.put("platformCode", "Android");
        header.put("phoneName", "ONEPLUS");
        header.put("appId", "lottery_pc");
    }

    public Request(Map<String, String> body) {
        this();
        this.body = body;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }
}
