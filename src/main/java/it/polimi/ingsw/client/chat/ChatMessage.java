package it.polimi.ingsw.client.chat;

public class ChatMessage {
    private String namePlayer;
    private String message;

    public ChatMessage(String namePlayer, String message) {
        this.message = message;
        this.namePlayer = namePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public String getMessage() {
        return message;
    }
}
