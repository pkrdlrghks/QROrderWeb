package com.example.QROrderWeb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Board {
    @Id
    @GeneratedValue
    private Integer idx;
    private String title;
    private String contents;
    private String managerId;
    private LocalDate writeDay=LocalDate.now();

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public LocalDate getWriteDay() {
        return writeDay;
    }

    public void setWriteDay(LocalDate writeDay) {
        this.writeDay = writeDay;
    }
}
