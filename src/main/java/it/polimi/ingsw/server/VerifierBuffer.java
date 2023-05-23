package it.polimi.ingsw.server;

/*This class contains the message buffer to check the connection state*/

public class VerifierBuffer {
    
    private String message;


    public VerifierBuffer() {
        message = "";
    }

    public synchronized String getMessage() {
        return message;
    }

    public synchronized void setMessage(String message) {
        this.message = message;
    }
    
    public synchronized void clear(){
        message="";
    }
}
