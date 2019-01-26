package com.jy.modules.demo.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
    private Long id;
    private String custName;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public UserDTO(Long id, String custName) {
        this.id = id;
        this.custName = custName;
    }

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                '}';
    }
}
