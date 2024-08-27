/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycom.test.service;

import com.mycom.test.entity.User;
import com.mycom.test.exception.MyCustomeException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface UserService {

    public List<String> getMissingFields(String firstName, String lastName);

    public void saveUser(User userResponse) throws MyCustomeException;
}
