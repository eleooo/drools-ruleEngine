package com.jy.modules.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * http://www.cnblogs.com/yueguanguanyun/p/9041690.html
 */
//@ApiModel(value="book对象",description="图书对象book")
public class Book implements Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键ID",name="id",required = true)
    private long id;
    @ApiModelProperty(value="图书名称",name="name",required = true)
    private String name;
    @ApiModelProperty(value="图书价格",name="price",required = true)
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
