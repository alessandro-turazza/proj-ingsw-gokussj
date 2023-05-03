package it.polimi.ingsw.client.chat;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Chat {
    private ArrayList<ChatMessage> chatBuffer = new ArrayList<>();

    private boolean open = false;
    public void chatAdd(JSONObject obj){
        chatBuffer.add(new ChatMessage(obj.get("namePlayer").toString(),obj.get("message").toString()));
        if(open)
            System.out.println(obj.get("namePlayer").toString() + ": " + obj.get("message").toString());

    }

    public void chatPrint(){
        for (ChatMessage m : chatBuffer)
            System.out.println(m.getNamePlayer() + ": " + m.getMessage());
    }

    public void setOpen(){
        open=true;
    }

    public void resetOpen(){
        open=false;
    }

}
