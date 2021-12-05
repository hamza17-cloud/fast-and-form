/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.da50.fastandform.core.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author hassan
 */

// app fais refernce app.<blabla> qui est dans application.properties 
@ConfigurationProperties(prefix="app")
@Document(collection = "Users")
public class Users {
    @Id
    public String id;
    public String firstName;
    public String lastName;

    public Users(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Users(String firstName, String lastName) {
    	this.firstName = firstName;
        this.lastName = lastName;
	}

    public Users() {
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
}
