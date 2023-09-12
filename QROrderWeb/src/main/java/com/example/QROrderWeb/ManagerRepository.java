package com.example.QROrderWeb;

import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    Manager findByUserId(String userId);
}
