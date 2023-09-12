package com.example.QROrderWeb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Manager {
    @Id
    private Integer idx;
    private String userId;
    private String pass;

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
