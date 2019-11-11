package ru.markov.vkproject.vkapi;

import com.google.gson.JsonElement;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import com.vk.api.sdk.objects.wall.CommentAttachment;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.responses.GetCommentsResponse;
import com.vk.api.sdk.queries.likes.LikesGetListFilter;
import com.vk.api.sdk.queries.likes.LikesType;
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
        while (offset < count) {
            try {
                gcr = Authorization.initVkApiClient()
                        .wall()
                        .getComments(Authorization.initUserActor(), post_id)
                        .ownerId(owner_id)
                        .previewLength(preview_length)
                        .count(count)
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
                    java.util.Date time = new java.util.Date((long) wallComment.getDate() * 1000);
                    date = time.toString();
                    replyToComment = String.valueOf(wallComment.getReplyToComment());
                    replyToUser = String.valueOf(wallComment.getReplyToUser());
                    text = wallComment.getText();
                    System.out.println("id: " + id + "fromId: " + fromId + " owner_ID: " + owner_ID + " date: " + date + " replyToComment: " + replyToComment + " replyToUser: " + replyToUser + " text:" + text);
                }
                count += 100;
            } catch (ApiException ex) {
                System.out.println("Too many requests per second (6): Too many requests per second");
            } catch (ClientException ex) {
                Logger.getLogger(VKAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getLike(Integer owner_id, Integer item_id, LikesType type) {

        GetListResponse glr;
        JsonElement response = null;
        int count = 0, offset = 0;
        String user = "";
        try {
            glr = Authorization.initVkApiClient()
                    .likes()
                    .getList(Authorization.initUserActor(), type)
                    .ownerId(owner_id)
                    .itemId(item_id)
                    .filter(LikesGetListFilter.LIKES)
                    .friendsOnly(Boolean.FALSE)
                    .offset(0)
                    .count(1)
                    .execute();
            count = glr.getCount();
        } catch (ApiException ex) {
            System.out.println(ex.getMessage());
        } catch (ClientException ex) {
            Logger.getLogger(VKAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (offset < count) {
            try {

                response = Authorization.initVkApiClient().execute().code(Authorization.initUserActor(),
                        "var like;\n"
                        + "var off =0;\n"
                        + "while(off<25000){\n"
                        + "like= like + API.likes.getList({\n"
                        + "\"type\": \"post\",\n"
                        + "\"owner_id\": \"-29573241\",\n"
                        + "\"item_id\": '13377582',\n"
                        + "\"filter\": \"likes\",\n"
                        + "\"friends_only\": '0',\n"
                        + "\"extended\": '0',\n"
                        + "\"friends_only\": '0',\n"
                        + "\"count\": '1000',\n"
                        + "\"offset\": (off+"+offset+")\n"
                        + "}).items;\n"
                        + "off =off+1000;\n"
                        + "}\n"
                        + "return like;"
                ).execute();
                offset += 25000;
                System.out.println(response.toString());
            } catch (ApiException ex) {
                System.out.println(ex.getMessage());
            } catch (ClientException ex) {
                Logger.getLogger(VKAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println(
                "users : " + user);
    }
}
