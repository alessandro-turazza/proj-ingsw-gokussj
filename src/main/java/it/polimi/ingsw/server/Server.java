package it.polimi.ingsw.server;

import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.user.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 4500;
    private static ServerSocket serverSocket;
    private static ArrayList<ServerGame> gameList;


    public void loadDatas() throws IOException, ParseException {
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadTokens("src/data/Tokens_Data.json");
        GameData.loadIdCommonGoals("src/data/Common_Goals_Setup.json");
        GameData.loadRuleCommons();
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
    }

    public synchronized ArrayList<ServerGame> getGameList() {
        return gameList;
    }

    public synchronized int insertNewGame(ServerThread st, User user, int numPlayers){
        this.gameList.add(new ServerGame(st, user, numPlayers, gameList.size()+1));
        return gameList.size();
    }

    public synchronized ServerGame getServerGameFromId(int id){
        for(ServerGame s: gameList){
            if(s.getIdGame() == id)
                return s;
        }
        return null;
    }

    public void startServer() throws IOException, ParseException {
        gameList = new ArrayList<>();
        serverSocket = new ServerSocket(PORT);

        System.out.println("Server ON");

        this.loadDatas();

        while(true){
            Socket socket = serverSocket.accept();
            ServerThread st = new ServerThread(this, socket);
            st.start();
        }
    }

}
