package com.whh.springboot.springdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseUserDetailServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void loadUserByUsername() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}