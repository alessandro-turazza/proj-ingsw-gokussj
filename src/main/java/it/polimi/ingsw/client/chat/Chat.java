package it.polimi.ingsw.client.chat;

import it.polimi.ingsw.client.view.Controller;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * This class represents the structure that contains each message on the chat of a game
 */
public class Chat {
    private ArrayList<ChatMessage> chatBuffer = new ArrayList<>();
    private Controller controller;
    private boolean open = false;
    public void chatAdd(JSONObject obj){
        chatBuffer.add(new ChatMessage(obj.get("namePlayer").toString(),obj.get("message").toString()));
        if(open)
            controller.showChatMessage(obj);

    }

    public ArrayList<ChatMessage> chatPrint(){
        return chatBuffer;
    }

    public void setOpen(){
        open=true;
    }

    public void resetOpen(){
        open=false;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
