package com.example.QROrderWeb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
public class watingList {
    @Id
    @GeneratedValue
    private Integer idxWating;
    private Integer watingNum;
    @JoinColumn(name = "shops_shnum")
    private Integer shnum;
    @JoinColumn(name = "menue_idxme")
    private Integer idxme;
    private Integer prise;

    private String mename;
    private boolean complete=false;
    private LocalDate orderDay=LocalDate.now();

    public String getMename() {
        return mename;
    }

    public void setMename(String mename) {
        this.mename = mename;
    }

    public Integer getIdxWating() {
        return idxWating;
    }

    public void setIdxWating(Integer idxWating) {
        this.idxWating = idxWating;
    }

    public Integer getWatingNum() {
        return watingNum;
    }

    public void setWatingNum(Integer watingNum) {
        this.watingNum = watingNum;
    }

    public Integer getShnum() {
        return shnum;
    }

    public void setShnum(Integer shnum) {
        this.shnum = shnum;
    }

    public Integer getIdxme() {
        return idxme;
    }

    public void setIdxme(Integer idxme) {
        this.idxme = idxme;
    }

    public Integer getPrise() {
        return prise;
    }

    public void setPrise(Integer prise) {
        this.prise = prise;
    }

    public LocalDate getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(LocalDate orderDay) {
        this.orderDay = orderDay;
    }
}
