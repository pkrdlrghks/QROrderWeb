package com.example.QROrderWeb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenueRepository extends CrudRepository<menue, Integer> {

    List<menue> findByShnum(Integer shnum);
    @Query("select DISTINCT metype from menue where shnum=?1")
    List<String> Metype(Integer shnum);

    @Query("select max(menum) as maxmenum from menum where shnum=?1")
    Integer findMaxmenue(Integer shnum);
}
