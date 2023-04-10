package it.polimi.ingsw.client;

public class MessageNewTurnClient implements MessageClient {

    private String username;

    private  String plank;

    public void setPlank(String plank) {
        this.plank = plank;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public String getPlank() {
        return plank;
    }

    @Override
    public void accept(VisitorClient visitor, Object obj) {
        visitor.visit(this, obj);
    }


}
