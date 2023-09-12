package com.example.QROrderWeb;

import org.springframework.data.repository.CrudRepository;

public interface ShopeRepository extends CrudRepository<shops, Integer> {

    shops findByShtell(String shtell);
}
