package com.ckd.waterz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ckd.waterz.models.User;



@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail(String email);
}

