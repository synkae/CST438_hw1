package com.codepath.synkae.hw1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class UserTest {
    private ArrayList<User> users;

    @Before
    public void init(){
        users = new ArrayList<User>();
        for (int i=1; i<=10; i++){
            users.add(new User(String.valueOf(i), "password"));
        }
    }
    @Test
    public void validUser(){
        User u = new User("1", "password");
        boolean check = false;
        for (User user : users){
            if (user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword()))
            {
                check = true;
                break;
            }
        }
        assertTrue(check);
    }
    @Test
    public void invalidUser(){
        User u = new User("asd", "password");
        boolean check = false;
        for (User user : users){
            if (user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword()))
            {
                check = true;
                break;
            }
        }
        assertFalse(check);
    }
}
