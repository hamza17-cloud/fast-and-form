/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.da50.fastandform.core.repository;

import fr.utbm.da50.fastandform.core.entity.Users;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author hassan
 */
public interface UsersRepository extends MongoRepository<Users, String>  {
	public List<Users> findUsersByFirstName(String firstName);
	public List<Users> findUsersByLastName(String lastName);
}
