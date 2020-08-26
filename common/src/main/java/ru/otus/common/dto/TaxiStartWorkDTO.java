package ru.otus.common.dto;

import java.io.Serializable;

public class TaxiStartWorkDTO implements Serializable {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
