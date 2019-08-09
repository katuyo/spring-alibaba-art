package org.featx.katuyo.springali.model;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private Boolean success;

    private String code;

    private T model;

    public static <T>BaseResponse<T> success(T t) {
        BaseResponse<T> br = new BaseResponse<>();
        br.setModel(t);
        br.setSuccess(true);
        br.setCode("");
        return br;
    }

    public Boolean getSuccess() {
        return success;
    }

    public BaseResponse<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public BaseResponse<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public T getModel() {
        return model;
    }

    public BaseResponse<T> setModel(T model) {
        this.model = model;
        return this;
    }
}
