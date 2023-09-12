package com.example.QROrderWeb;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity

public class menue {
    @JoinColumn(name = "shops_shnum")
    private Integer shnum;
    private Integer menum;
    private String mename;
    private String metype;
    private Integer prise;
    @Id
    @GeneratedValue
    private Integer idxme;

    public Integer getShnum() {
        return shnum;
    }

    public void setShnum(Integer shnum) {
        this.shnum = shnum;
    }

    public Integer getMenum() {
        return menum;
    }

    public void setMenum(Integer menum) {
        this.menum = menum;
    }

    public String getMename() {
        return mename;
    }

    public void setMename(String mename) {
        this.mename = mename;
    }

    public String getMetype() {
        return metype;
    }

    public void setMetype(String metype) {
        this.metype = metype;
    }

    public Integer getPrise() {
        return prise;
    }

    public void setPrise(Integer prise) {
        this.prise = prise;
    }

    public Integer getIdxme() {
        return idxme;
    }

    public void setIdxme(Integer idxme) {
        this.idxme = idxme;
    }
}
