package com.jy.modules.drools.entity;

import java.util.Date;

/**
 * Created by apple on 2019/1/13.
 */
public class Applicant {
    private String custName;

    private String cardId;

    private Date birthDate;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getBirthDate() {
        return (Date) birthDate.clone();
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate != null) {
            this.birthDate = (Date) birthDate.clone();
        }
    }
}
