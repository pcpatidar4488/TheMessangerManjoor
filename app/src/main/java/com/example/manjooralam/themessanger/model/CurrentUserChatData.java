package com.example.manjooralam.themessanger.model;

import java.util.Map;

/**
 * Created by manjooralam on 11/1/2017.
 */

public class CurrentUserChatData {
    public String last_message;
    public String type;
    public String time;
    public String count;
    public  CurrentUserChatData() {

    }
    public CurrentUserChatData(String lastMessage, String type, Map<String, String> timestamp, String count) {
        this.last_message = lastMessage;
        this.type = type;
        this.time = timestamp.toString();
        this.count = count;
    }
}
