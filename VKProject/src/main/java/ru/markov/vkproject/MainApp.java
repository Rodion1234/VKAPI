
package ru.markov.vkproject;

import java.util.List;
import ru.markov.vkproject.entity.User;
import ru.markov.vkproject.vkapi.VKAPI;


public class MainApp {
    
    public static void main(String[] args) {
        VKAPI vkapi = new VKAPI();
//        vkapi.getUsersExecute("111905078", 0,vkapi.getCount("111905078"));
        List<User> users = vkapi.getUsers("111905078", 0, vkapi.getCount("111905078"));
       int i = 1;
        for (User user : users) {
            System.out.println(i+" "+ user);
            i++;
        }
    }
    
}
