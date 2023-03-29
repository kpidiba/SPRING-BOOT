package com.spring.springvalidation.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class LoginData {

    @NotBlank(message = "Username can not be empty")
    @Size(min = 3,max=12,message="Username between 3-12 caracters")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email !!!")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String password) {
        this.email = password;
    }

    @Override
    public String toString() {
        return "LoginData [username=" + username + ", email=" + email + "]";
    }
    
}
