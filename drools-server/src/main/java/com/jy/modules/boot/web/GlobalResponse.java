package com.jy.modules.boot.web;

/**
 * 定义通用的数据返回对象
 * Created by apple on 2019/4/30.
 */
public class GlobalResponse<T> {
    /**
     * 接口是否响应成功
     */
    protected boolean success = false;
    /**
     * 响应报文主体信息
     */
    private T data;
    /**
     * 错误编码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;


    public GlobalResponse(T data, boolean success) {
        this.success = success;
        this.data = data;
    }

    public GlobalResponse() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * <br> 提供一个正常数据返回
     *
     * @param data
     * @return
     */
    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse(data, true);
    }

    /**
     * <br> 提供一个异常数据返回
     *
     * @param errorMsg
     * @param errorCode
     * @return
     */
    public static <T> GlobalResponse<T> fail(String errorMsg, Integer errorCode) {
        GlobalResponse<T> resp = new GlobalResponse<T>();
        resp.setSuccess(false);
        resp.setErrorMsg(errorMsg);
        resp.setErrorCode(errorCode);
        return resp;
    }
}
