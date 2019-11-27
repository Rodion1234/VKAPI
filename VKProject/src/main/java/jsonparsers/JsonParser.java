package jsonparsers;

import com.vk.api.sdk.objects.wall.WallComment;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.markov.vkproject.entity.Comment;
import ru.markov.vkproject.entity.Like;
import ru.markov.vkproject.entity.Repost;
import ru.markov.vkproject.entity.User;

public class JsonParser {

    public int getNumberUser(String json) {

        JSONObject obj = new JSONObject(json);
        int count = obj.getJSONObject("response").getInt("count");

        return count;
    }

//    public List<User> getMembers(String json) {
//
//        List<User> users = new ArrayList<>();
//
//        JSONObject obj = new JSONObject(json);
//        
//        JSONObject response = obj.getJSONObject("response");
//
//        JSONArray arr = response.getJSONArray("items");
//        for (int i = 0; i < arr.length(); i++) {
//            users.add(new User(arr.getString(i)));
//        }
//
//        return users;
//    }
    public List<User> getMembersExsecute(String response) {

        List<User> users = new ArrayList<>();

        JSONArray arr = new JSONArray(response.replace("][", ","));
        for (int i = 0; i < arr.length(); i++) {
            User user = new User();
            JSONObject obj = arr.getJSONObject(i);
            user.setId(obj.getInt("id"));
            user.setFirstName(obj.getString("first_name"));
            user.setLastName(obj.getString("last_name"));
            if (obj.has("sex")) {
                user.setSex(obj.getInt("sex"));
            }
            if (obj.has("bdate")) {

                String bdate[] = obj.getString("bdate").split("\\.");
                if (bdate.length == 3) {
                    user.setbDateYear(Integer.valueOf(bdate[2]));
                }
            }
            if (obj.has("city")) {
                user.setCity(obj.getJSONObject("city").getString("title"));
            }
            if (obj.has("country")) {
                user.setCountry(obj.getJSONObject("country").getString("title"));
            }

            users.add(user);
        }
        return users;
    }

    public List<Comment> getComments(List<WallComment> wallComments, Integer owner_id, Integer item_id) {
        List<Comment> comments = new ArrayList<>();
        for (WallComment wallComment : wallComments) {
            Comment comment = new Comment();

            comment.setFrom_id(wallComment.getFromId());
            comment.setOwner_id(owner_id);
            comment.setItem_id(item_id);
            comment.setDate(new java.util.Date((long) wallComment.getDate() * 1000));
            comment.setText(wallComment.getText());

            comments.add(comment);
        }
        return comments;
    }

    public List<Like> getLike(String response, Integer owner_id, Integer item_id) {
        List<Like> likes = new ArrayList();

        String[] like = response.replace("\"", "").split(",");

        for (int i = 0; i < like.length; i++) {
            Like like1 = new Like();
            like1.setOwner_id(owner_id);
            like1.setItem_id(item_id);
            like1.setUser_id(Integer.valueOf(like[i]));
            likes.add(like1);
        }
        return likes;
    }

    public List<Repost> getRepost(String response, Integer owner_id, Integer item_id) {
        JSONArray arr = new JSONArray(response);
        List<Repost> reposts = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject object = arr.getJSONObject(i);
            Repost repost = null;
            if (!object.isNull("repost")) {

                JSONArray arrReposts = object.getJSONArray("repost");
                for (int j = 0; j < arrReposts.length(); j++) {
                    JSONObject object1 = arrReposts.getJSONObject(j);
                    if (object1.has("copy_history")) {
                        JSONArray arrRepost = object1.getJSONArray("copy_history");
                        for (int k = 0; k < arrRepost.length(); k++) {
                            JSONObject object2 = arrRepost.getJSONObject(k);
                            if (object2.has("id")) {
                                if (object2.getInt("id") == item_id) {
                                    repost = new Repost();
                                    repost.setItem_id(item_id);
                                    repost.setOwner_id(owner_id);
                                }
                            }
                        }
                    } else {
                        break;
                    }
                    if (repost != null) {
                        repost.setDate(new java.util.Date((long) object1.getInt("date") * 1000));
                    }
                }
                if (repost != null) {
                    if (!object.isNull("user_id")) {
                        repost.setUser_id(object.getInt("user_id"));
                    }
                    boolean flag = true;
                    for (Repost repost1 : reposts) {
                        if (repost.getUser_id() != null) {
                            if (repost.getUser_id().equals(repost1.getUser_id())) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                    if (flag == true) {
                        reposts.add(repost);
                    }
                }
            }
        }
        return reposts;
    }

    public boolean getError(String json) {

        if (!json.contains("error")) {
            return true;
        } else {
            return false;
        }

    }
}
