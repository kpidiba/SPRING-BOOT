package com.smart.smartcontactmanagers.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.smartcontactmanagers.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
}
