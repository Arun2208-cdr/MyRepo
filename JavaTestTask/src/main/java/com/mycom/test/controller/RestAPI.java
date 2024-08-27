/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycom.test.controller;

import com.mycom.test.entity.User;
import com.mycom.test.exception.MyCustomeException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mycom.test.service.UserService;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@RestController
public class RestAPI {

    @Autowired
    private UserService userService;

    @GetMapping("/getMissingFields")
    public ResponseEntity<String> getMissingFields(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
                return new ResponseEntity<>("First name and Last name is mandatory", HttpStatus.BAD_REQUEST);
            } else {
                List<String> missingFields = userService.getMissingFields(firstName, lastName);
                JSONObject obj = new JSONObject();
                if (missingFields.isEmpty()) {
                    obj.put("missingfields", "None");
                } else {
                    obj.put("missingfields", missingFields);
                }
                return new ResponseEntity<>(obj.toString(), HttpStatus.OK);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }

    }

    @PostMapping("/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody User user) {
        try {
            if (StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName())
                    || StringUtils.isEmpty(user.getBirthdate()) || StringUtils.isEmpty(user.getBirthplace())
                    || StringUtils.isEmpty(user.getCurrentaddress()) || StringUtils.isEmpty(user.getSex())) {
                return ResponseEntity.ok("Please enter all mandatory elements");
            }
            userService.saveUser(user);
            return ResponseEntity.ok("User saved successfully and data passed to client");
        } catch (MyCustomeException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/display")
    public ResponseEntity<String> display(@RequestBody User user) {
        try {
            System.out.println("--" + user.getFirstName());
            System.out.println("--" + user.getLastName());
            System.out.println("--" + user.getId());
            return ResponseEntity.ok("User saved successfully and data passed to client");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
