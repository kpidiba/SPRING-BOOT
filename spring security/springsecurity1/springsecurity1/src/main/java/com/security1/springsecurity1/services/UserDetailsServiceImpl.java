package com.security1.springsecurity1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security1.springsecurity1.models.User;
import com.security1.springsecurity1.models.details.MyUserDetails;
import com.security1.springsecurity1.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("ce compte n' existe pas");
        }
        return new MyUserDetails(user);
    }
    
}
