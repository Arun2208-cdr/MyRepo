/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycom.test.repository;

import com.mycom.test.entity.ServiceConfig;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public interface ServiceConfigRegistry extends JpaRepository<ServiceConfig, Long> {

    Optional<ServiceConfig> findByServiceName(String serviceName);
}
