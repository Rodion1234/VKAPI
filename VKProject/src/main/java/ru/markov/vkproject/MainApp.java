package ru.markov.vkproject;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.likes.LikesType;
import java.util.List;
import ru.markov.vkproject.entity.Comment;
import ru.markov.vkproject.entity.Like;
import ru.markov.vkproject.entity.User;
import ru.markov.vkproject.vkapi.UserActivityVKAPI;
import ru.markov.vkproject.vkapi.VKAPI;

public class MainApp {

    public static void main(String[] args) throws ApiException, ClientException {

        VKAPI vkapi = new VKAPI();
        UserActivityVKAPI activityVKAPI = new UserActivityVKAPI();
//        List<User> user = vkapi.getMembersExecute("94062508", 0, vkapi.getCountMembers("94062508"));
//        System.out.println("Count user: " + user.size());
//        for (User user1 : user) {
//            System.out.println(user1);
//        }
//        System.out.println("-----------------------");
//        List<Comment> comments = activityVKAPI.getComments(-111905078, 23767, 50, 100);
//         System.out.println("Count user: " + comments.size());
//        for (Comment user1 : comments) {
//            System.out.println(user1);
//        }
//
//        List<Like> like = activityVKAPI.getLike(-111905078, 23767, LikesType.POST);
//        System.out.println("-----------------------");
//        System.out.println("Count user: " + like.size());
//        for (Like user1 : like) {
//            System.out.println(user1);
//        

    activityVKAPI.getRepost(-111905078, 23767, LikesType.POST);

//        List<Integer> friends = vkapi.getFriends(166416);
        //        int i = 1;
        //        for (Integer friend : friends) {
        //            System.out.println(i + " - " +friend);
        //            i++;
        //        }
    }

}
