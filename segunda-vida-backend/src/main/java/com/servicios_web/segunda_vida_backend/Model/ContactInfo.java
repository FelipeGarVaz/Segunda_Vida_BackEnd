package com.servicios_web.segunda_vida_backend.Model;

public class ContactInfo{
    private String message;
    private String phone;

    public ContactInfo(String message, String phone) {
        this.message = message;
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
