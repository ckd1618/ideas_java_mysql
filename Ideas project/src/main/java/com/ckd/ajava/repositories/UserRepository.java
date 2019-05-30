package com.ckd.ajava.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ckd.ajava.models.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
