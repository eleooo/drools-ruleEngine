package com.jy.modules.drools.domain;

import java.util.Date;

public class Sale {
    // 日期
    private Date date;
    // 单价
    private int price;
    // 数量
    private int amount;
    // 销售单号
    private String saleCode;

    public Sale(Date date, int price, int amount, String saleCode) {
        if (date != null) {
            this.date = (Date) date.clone();
        }
        this.price = price;
        this.amount = amount;
        this.saleCode = saleCode;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = (Date) date.clone();
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "date=" + date +
                ", price=" + price +
                ", amount=" + amount +
                ", saleCode='" + saleCode + '\'' +
                '}';
    }
}
