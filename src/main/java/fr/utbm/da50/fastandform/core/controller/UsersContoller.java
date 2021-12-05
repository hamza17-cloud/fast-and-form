/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.da50.fastandform.core.controller;

import fr.utbm.da50.fastandform.core.entity.Users;
import fr.utbm.da50.fastandform.core.repository.UsersRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hassan
 */
@RestController
public class UsersContoller {
    
	@Autowired
    private UsersRepository usersRepository;
    @Autowired
    Users appConfig;
    
    @PostMapping("/addUsers")
    public String saveUsers() {
    	Users users = new Users(appConfig.getFirstName(),appConfig.getLastName());
        usersRepository.save(users);
        return users.toString();
    }
    @GetMapping("/findLasName")
    public List<Users> findByLastName() {    	
    	return usersRepository.findUsersByLastName(appConfig.getLastName());

    }
    @GetMapping("/findFirstName")
    public List<Users> findByFirstName() {    	
    	return usersRepository.findUsersByFirstName(appConfig.getFirstName());

    }
}
