/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycom.test.service;

import com.mycom.test.entity.User;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ADMIN
 */
@FeignClient(name = "userManagementClient", url = "http://dummy:8089")
public interface UserManagementClient {

    @PostMapping("/display")
    ResponseEntity<String> displayUser(URI baseUrl,@RequestBody User user);
}