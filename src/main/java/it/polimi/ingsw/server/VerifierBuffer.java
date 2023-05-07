package it.polimi.ingsw.server;

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
