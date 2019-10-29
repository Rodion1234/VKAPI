/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.markov.vkproject.entity;

/**
 *
 * @author rodion
 */
public class User {
    private  Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + '}';
    }
    
    
}
