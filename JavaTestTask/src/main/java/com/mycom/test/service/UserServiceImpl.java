/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycom.test.service;

import com.mycom.test.entity.RequiredFields;
import com.mycom.test.entity.ServiceConfig;
import com.mycom.test.entity.User;
import com.mycom.test.exception.MyCustomeException;
import com.mycom.test.repository.RequiredFieldsRepository;
import com.mycom.test.repository.ServiceConfigRegistry;
import com.mycom.test.repository.UserRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequiredFieldsRepository requiredFieldsRepository;
    @Autowired
    private UserManagementClient userManagementClient;
    @Autowired
    private ServiceConfigRegistry serviceConfig;

    @Override
    public List<String> getMissingFields(String firstName, String lastName) {
        User user = userRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<RequiredFields> requiredFields = requiredFieldsRepository.findAll();
        return checkMissingFields(user, requiredFields);
    }

    @Override
    public void saveUser(User user) throws MyCustomeException {
        Optional<User> opt = userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName());
        if (opt.isPresent()) {
            user.setId(opt.get().getId());
        }
        ServiceConfig sc = serviceConfig.findByServiceName("UserMgmtService").orElseThrow(() -> new RuntimeException("Service config not found"));
        URI determinedBasePathUri = URI.create(sc.getBaseUrl());
        ResponseEntity<String> re = userManagementClient.displayUser(determinedBasePathUri, user);
        if (re.getStatusCode().is2xxSuccessful()) {
            userRepository.save(user);
        } else {
            throw new MyCustomeException("Error with client call with status code- " + re.getStatusCodeValue());
        }
    }

    private List<String> checkMissingFields(User user, List<RequiredFields> requiredFields) {
        List<String> missingFields = new ArrayList<>();
        for (RequiredFields field : requiredFields) {
            String fieldName = field.getFieldName();
            Boolean isRequired = field.getRequired();
            if (isRequired) {
                switch (fieldName) {
                    case "birthdate":
                        if (user.getBirthdate() == null) {
                            missingFields.add("birthdate");
                        }
                        break;
                    case "birthplace":
                        if (user.getBirthplace() == null) {
                            missingFields.add("birthplace");
                        }
                        break;
                    case "sex":
                        if (user.getSex() == null) {
                            missingFields.add("sex");
                        }
                        break;
                    case "currentAddress":
                        if (user.getCurrentaddress() == null) {
                            missingFields.add("currentAddress");
                        }
                        break;
                }
            }
        }

        return missingFields;
    }
}
