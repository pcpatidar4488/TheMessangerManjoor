package com.example.manjooralam.themessanger.model;

import java.util.Map;

/**
 * Created by manjooralam on 11/1/2017.
 */

public class OtherUserChatData {
    public String last_message;
    public String type;
    public String time;

    public  OtherUserChatData() {

    }
    public OtherUserChatData(String lastMessage, String type, Map<String, String> timestamp) {
        this.last_message = lastMessage;
        this.type = type;
        this.time = timestamp.toString();
    }
}
