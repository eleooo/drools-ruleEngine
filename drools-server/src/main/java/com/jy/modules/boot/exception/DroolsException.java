package com.jy.modules.boot.exception;

import java.io.Serializable;

/**
 * Created by apple on 2019/4/30.
 */
public class DroolsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 5449355956170645749L;
    /**
     * 错误编码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public DroolsException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public DroolsException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DroolsException(String message, Throwable cause, Integer errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DroolsException(Throwable cause, Integer errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public DroolsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
