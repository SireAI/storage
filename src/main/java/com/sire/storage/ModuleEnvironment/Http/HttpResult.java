package com.sire.storage.ModuleEnvironment.Http;

import java.io.Serializable;

public class HttpResult implements Serializable{

    // 响应码
    private Integer httpcode;

    // 响应体
    private String httpBody;

    public HttpResult() {
        super();
    }

    public HttpResult(Integer code, String body) {
        super();
        this.httpcode = code;
        this.httpBody = body;
    }

    public Integer getHttpcode() {
        return httpcode;
    }

    public void setHttpcode(Integer httpcode) {
        this.httpcode = httpcode;
    }

    public String getHttpBody() {
        return httpBody;
    }

    public void setHttpBody(String httpBody) {
        this.httpBody = httpBody;
    }

}