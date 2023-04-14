package it.polimi.ingsw.server;

import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.server.chat.ServerChatWriter;
import it.polimi.ingsw.user.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 4500;
    private static ServerSocket serverSocket;
    private static ArrayList<ServerGame> gameList;


    public static void loadDatas() throws IOException, ParseException {
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadTokens("src/data/Tokens_Data.json");
        GameData.loadIdCommonGoals("src/data/Common_Goals_Setup.json");
        GameData.loadRuleCommons();
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
    }

    public synchronized static ArrayList<ServerGame> getGameList() {
        return gameList;
    }

    public synchronized static int insertNewGame(ServerChatWriter w, ServerThread st, User user, int numPlayers){
        gameList.add(new ServerGame(w, st, user, numPlayers, gameList.size()+1));
        return gameList.size();
    }

    public synchronized static ServerGame getServerGameFromId(int id){

        for(ServerGame s: gameList){
            if(s.getIdGame() == id)
                return s;
        }

        return null;

    }

    public static void main(String[] args) throws IOException, ParseException {
        gameList = new ArrayList<>();
        serverSocket = new ServerSocket(PORT);

        System.out.println("Server ON");

        loadDatas();


        while(true){
            Socket socket = serverSocket.accept();
            ServerThread st = new ServerThread(socket);
            st.start();
        }


    }


}
