package com.saic.msw.enums;

public enum ResponseEnum {

    SUCCESS(0 , "success"),
    ERROR(-1,"fail");

    private Integer code;
    private String msg;

    ResponseEnum(Integer code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
