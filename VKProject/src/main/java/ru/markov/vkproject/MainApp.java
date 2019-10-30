
package ru.markov.vkproject;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import java.util.List;
import ru.markov.vkproject.entity.User;
import ru.markov.vkproject.vkapi.UserActivityVKAPI;
import ru.markov.vkproject.vkapi.VKAPI;


public class MainApp {
    
    public static void main(String[] args) throws ApiException, ClientException {
        
        VKAPI vkapi = new VKAPI();
//        List<User> user = vkapi.getMembersExecute("152806540", 0, vkapi.getCountMembers("152806540"));
//        System.out.println("Count user: " + user.size());

//        List<Integer> friends = vkapi.getFriends(166416);
//        int i = 1;
//        for (Integer friend : friends) {
//            System.out.println(i + " - " +friend);
//            i++;
//        }
        (new UserActivityVKAPI()).getComments(-111905078, 22683, 50, 0);
    }
    
}
