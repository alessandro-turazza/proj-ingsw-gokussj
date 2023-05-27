package it.polimi.ingsw.client.chat;

/**
 * This class contains the structure for a chat message
 */
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
