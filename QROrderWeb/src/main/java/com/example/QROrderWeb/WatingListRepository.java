package com.example.QROrderWeb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface WatingListRepository extends CrudRepository<watingList, Integer> {

    LocalDate orderDay=LocalDate.now();

    @Query(value = "select max(watingNum) from watingList where orderDay=CURDATE() and shnum=?1")
    Integer getEndWating(int shnum);
    @Query(value = "select watingNum from watingList where complete=false group by watingNum")
    int[] watingPerson();

    @Query(value = "update watingList set complete=true where watingNum=?1")
    void updateComplete(String watingNum);
}
