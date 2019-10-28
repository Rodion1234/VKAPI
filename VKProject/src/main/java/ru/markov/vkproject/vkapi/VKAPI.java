package ru.markov.vkproject.vkapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import jsonparsers.JsonParser;
import ru.markov.vkproject.entity.User;

/*This is API for parsing data from VK. I write graduation work and use java for parsing. You can use js, php and others language 
 for java VK compani create sdk*/
public class VKAPI {

    /*Method gets user from group VK. There is execute method. It uses when you need parse great groap.
    becouse standart method have border time and numbers*/
    public List<User> getUsers(String group_id, int offset, int count) {
        List<User> users = new ArrayList<>();
        JsonParser parser = new JsonParser();
        while(offset<count){
            String url = generateURLForGetMembers(group_id,offset,count);
            String request = getRequest(url);
//            System.out.println(request);
            if(parser.getError(request)){
                users.addAll(parser.getMembers(request));
                offset+=1000;
            }
        }
        return users;
    }
    
    public List<User> getUsersExecute(String group_id, int offset,int count){
        List<User> users = new ArrayList<>();
        JsonParser parser = new JsonParser();
//https://vk.com/dev/execute?params[code]=var%20members%3B%0Avar%20num%3D0%3B%0Avar%20off%3D0%3B%0Avar%20cou%3D25000%3B%0Awhile(num%3C28%26%26off%3Ccou)%7B%20%0Amembers%3D%20members%20%2B%20%22%2C%22%2BAPI.groups.getMembers(%7B%22group_id%22%3A%20%0A%20%22111905078%22%2C%20%22v%22%3A%20%225.102%22%2C%20%22sort%22%3A%20%22id_asc%22%2C%20%22count%22%3A%20%221000%22%2C%20%22offset%22%3A%20%20%2210%22%7D).items%3B%0Anum%3Dnum%2B1%3B%0Aoff%3Doff%2B1000%3B%0A%7D%3B%0A%0Areturn%20members%3B&params[v]=5.102
//https://vk.com/dev/execute?params[code]=var%20members%3B%0Avar%20num%3D0%3B%0Avar%20off%3D0%3B%0Avar%20cou%3D25000%3B%0Awhile(num%3C28%26%26off%3Ccou)%7B%20%0Amembers%3D%20members%20%2BAPI.groups.getMembers(%7B%22group_id%22%3A%20%0A%20%22111905078%22%2C%20%22v%22%3A%20%225.102%22%2C%20%22sort%22%3A%20%22id_asc%22%2C%20%22count%22%3A%20%221000%22%2C%20%22offset%22%3A%20%20%2210%22%7D).items%3B%0Anum%3Dnum%2B1%3B%0Aoff%3Doff%2B1000%3B%0A%7D%3B%0A%0Areturn%20members%3B&params[v]=5.102
String code = "var%20members%3B%0Avar%20num%3D0%3B%0Avar%20off%3D"+offset+"%3B%0Avar%20cou%3D"+count+"%3B%0A"
                + "while(num%3C28%26%26off%3Ccou)"
                + "%7B%20%0Amembers%3D%20members%20%2B"
        + "API.groups.getMembers(%7B%22"
        + "group_id%22%3A%20%0A%20%22111905078%22%2C%20%22v%22%3A%20%225.102%22%2C%20%22sort%22%3A%20%22id_asc%22%2C%20%22count%22%3A%20%221000%22%2C%20%22offset%22%3A%20%20%2210%22%7D).items%3B%0Anum%3Dnum%2B1%3B%0Aoff%3Doff%2B1000%3B%0A%7D%3B%0A%0Areturn%20members%3B";
        System.out.println(generateURLForgetUsersExecute(code));
        String request = getRequest(generateURLForgetUsersExecute(code));
        users.addAll(parser.getMembersExsecute(request));
        
        System.out.println(request);
        return users;
    }

    private String generateURLForgetUsersExecute(String code){
       String url = "https://api.vk.com/method"
                + "/execute?" + "code=" + code + "&access_token=" + Authorization.access_token + "&v=5.102"; 
       return url;
    }
    private String generateURLForGetMembers(String group_id, int offset, int count) {
                String url = "https://api.vk.com/method"
                + "/groups.getMembers?" + "group_id=" + group_id + "&sort=id_asc&"
                + "offset=" + offset + "&count=1000"
                + "&access_token=" + Authorization.access_token
                + "&v=5.102";
                
                return url;
    }

    public int getCount(String group_id) {
        String url = "https://api.vk.com/method"
                + "/groups.getMembers?" + "group_id=" + group_id + "&sort=id_asc&"
                + "offset=0&count=0"
                + "&access_token=" + Authorization.access_token
                + "&v=5.102";
        JsonParser jsonParser = new JsonParser();
        int count = jsonParser.getNumberUser(getRequest(url));
        return count;
    }

    private String getRequest(String url) {
        HttpURLConnection connection = null;
        StringBuilder builder = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String string;
                while ((string = reader.readLine()) != null) {
                    builder.append(string);
                }
            } else {
                System.out.println("Error in request!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return builder.toString();
    }

}
