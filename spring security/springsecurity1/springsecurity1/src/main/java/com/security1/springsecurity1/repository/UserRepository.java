package com.security1.springsecurity1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.security1.springsecurity1.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}
