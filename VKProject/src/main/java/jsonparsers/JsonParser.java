package jsonparsers;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.markov.vkproject.entity.User;

public class JsonParser {

    public int getNumberUser(String json) {

        JSONObject obj = new JSONObject(json);
        int count = obj.getJSONObject("response").getInt("count");

//JSONArray arr = obj.getJSONArray("posts");
//for (int i = 0; i < arr.length(); i++)
//{
//    String post_id = arr.getJSONObject(i).getString("post_id");
//    ......
//}
        return count;
    }

//    public List<User> getMembers(String json) {
//
//        List<User> users = new ArrayList<>();
//
//        JSONObject obj = new JSONObject(json);
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

        response = response.replace("\"", "");
        String[] user = response.split(",");
      
        for (int i = 0; i < user.length; i++) {
            users.add(new User(Integer.valueOf(user[i])));
        }
        
        return users;
    }

    public boolean getError(String json) {

        if(!json.contains("error")) return true;
        else return false;

    }
}
