package com.spectro.spectro.repository;

import com.spectro.spectro.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity findByNumberphone(String number);
    UserEntity findByPassword(String Password);
}