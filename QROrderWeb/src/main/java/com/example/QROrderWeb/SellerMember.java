package com.example.QROrderWeb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SellerMember {

    @Id
    @GeneratedValue
    private Integer idx;
    @Id
    private String userId;

    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Id
    @GeneratedValue
    private Integer shnum;

    private String phonNumber;


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

    public Integer getShnum() {
        return shnum;
    }

    public void setShnum(Integer shnum) {
        this.shnum = shnum;
    }

    public String getPhonNumber() {
        return phonNumber;
    }

    public void setPhonNumber(String phonNumber) {
        this.phonNumber = phonNumber;
    }
}
