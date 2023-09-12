package com.example.QROrderWeb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenueRepository extends CrudRepository<menue, Integer> {

    List<menue> findByShnum(Integer sh_num);
    @Query("select DISTINCT count(metype) metypeCount from menue")
    List<String> countMetype();
}
