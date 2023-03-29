package com.security1.springsecurity1.generator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin";
        String encodePassword= encoder.encode(password);
        System.out.print("----"+encodePassword+"---");
    }
}
