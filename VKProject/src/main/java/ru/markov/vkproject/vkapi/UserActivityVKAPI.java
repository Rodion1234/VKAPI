package ru.markov.vkproject.vkapi;

import com.google.gson.JsonElement;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.wall.CommentAttachment;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.responses.GetCommentsResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.markov.vkproject.entity.Comment;

public class UserActivityVKAPI {

    public void getComments(Integer owner_id, Integer post_id, Integer preview_length, int count) {

        List<Comment> userID = new ArrayList<>();
        int offset = 0;
        GetCommentsResponse gcr;
//        while (offset < count) {
        try {
            gcr = Authorization.initVkApiClient()
                    .wall()
                    .getComments(Authorization.initUserActor(), post_id)
                    .ownerId(owner_id)
                    .previewLength(preview_length)
                    .count(100)
                    .offset(offset)
                    .execute();
//            Comment comment = new Comment();
            List<WallComment> wallComments = gcr.getItems();
            
            String id = "";
            String fromId = "";
            String owner_ID = "";
            String date = "";
            String replyToComment = "";
            String replyToUser = "";
            String text = "";
            for (WallComment wallComment : wallComments) {
                id = String.valueOf(wallComment.getId());
                fromId = String.valueOf(wallComment.getFromId());
                owner_ID = String.valueOf(owner_id);
                date = String.valueOf(new Date(wallComment.getDate()));
                replyToComment = String.valueOf(wallComment.getReplyToComment());
                replyToUser = String.valueOf(wallComment.getReplyToUser());
                text = wallComment.getText();
            }
            System.out.println("id: " + id + "fromId: "+fromId+" owner_ID: "+owner_ID+" date: "+date+" replyToComment: "+replyToComment+" replyToUser: "+replyToUser+" text:" + text);
        } catch (ApiException ex) {
            System.out.println("Too many requests per second (6): Too many requests per second");
        } catch (ClientException ex) {
            Logger.getLogger(VKAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
//        }
    }
}
