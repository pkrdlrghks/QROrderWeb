package com.example.QROrderWeb;

import org.springframework.data.repository.CrudRepository;

public interface SellerMemberRepository extends CrudRepository<SellerMember, Integer> {

    SellerMember findByUserId(String id);
}
