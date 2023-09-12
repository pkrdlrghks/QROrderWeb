package com.example.QROrderWeb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;


@Entity
public class shops {
    @Id
    @GeneratedValue
    private Integer shnum;
    private String shname;
    private String shaddr;
    @Id
    private String shtell;
    private String shpass;

    public Integer getShnum() {
        return shnum;
    }

    public void setShnum(Integer shnum) {
        this.shnum = shnum;
    }

    public String getShname() {
        return shname;
    }

    public void setShname(String shname) {
        this.shname = shname;
    }

    public String getShaddr() {
        return shaddr;
    }

    public void setShaddr(String shaddr) {
        this.shaddr = shaddr;
    }

    public String getShtell() {
        return shtell;
    }

    public void setShtell(String shtell) {
        this.shtell = shtell;
    }

    public String getShpass() {
        return shpass;
    }

    public void setShpass(String shpass) {
        this.shpass = shpass;
    }
}
