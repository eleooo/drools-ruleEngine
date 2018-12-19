package com.jy.modules.drools.domain;

import java.io.Serializable;

/**
 * @className: Message
 * @package: com.jy.module.drools.domain
 * @describe: 定义一个Drools中需要使用到的Model
 * @auther: dongdongchen
 * @date: 2018/12/12
 * @time: 11:49
 **/

public class Message  implements Serializable {
    public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;
    private String msg;
    private Integer status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
