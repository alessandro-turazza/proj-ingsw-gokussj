package it.polimi.ingsw.server;

import it.polimi.ingsw.user.User;
import org.json.simple.JSONObject;

public class MessageEnterInGame implements MessageServer{
    private int idGame;
    private User user;

    private ServerThread serverThread;

    public MessageEnterInGame(ServerThread st){
        this.serverThread = st;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void accept(VisitorServer v, JSONObject obj) {
        v.visitMessageAddPlayer(this, obj);
        boolean res = Server.getServerGameFromId(idGame).addNewPlayer(serverThread, user);

        if(res){
            serverThread.setIdGame(idGame);
            serverThread.getSs().sendOk();
            serverThread.setUser(user);
        }else
            serverThread.getSs().sendKO();
    }
}
