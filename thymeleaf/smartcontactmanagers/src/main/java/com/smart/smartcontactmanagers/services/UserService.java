package com.smart.smartcontactmanagers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.smart.smartcontactmanagers.dao.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userrepository;
}
