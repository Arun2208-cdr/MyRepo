/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycom.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @author ADMIN
 */
@SpringBootApplication
@EnableFeignClients
public class MainSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainSpringApplication.class,args);
    }
}
