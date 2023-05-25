package it.polimi.ingsw.server;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.server.game_data.GameData;
import it.polimi.ingsw.server.model.user.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 50000;
    private static final int VERIFIER_READ_PORT = 50002;
    private static final int VERIFIER_WRITE_PORT = 50001;
    private static ServerSocket serverSocket;
    private static ServerSocket verifierReadSocket;
    private static ServerSocket verifierWriteSocket;
    private static ArrayList<ServerGame> gameList;

    public static ServerSocket getVerifierReadSocket() {
        return verifierReadSocket;
    }

    public static ServerSocket getVerifierWriteSocket() {
        return verifierWriteSocket;
    }

    /*Load each component from json files in order to start the games correctly*/
    public void loadDatas() throws IOException, ParseException {
        GameData.loadPlankConfig("src/data/Plank_Config_1.json");
        GameData.loadTokens("src/data/Tokens_Data.json");
        GameData.loadIdCommonGoals("src/data/Common_Goals_Setup.json");
        GameData.loadRuleCommons();
        GameData.loadPersonalGoals("src/data/PersonalGoals_Data.json");
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
    }

    /*Create a new game*/
    public synchronized int insertNewGame(ServerThread st, User user, int numPlayers){
        gameList.add(new ServerGame(st, user, numPlayers, gameList.size()+1));
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
        verifierReadSocket = new ServerSocket(VERIFIER_READ_PORT);
        verifierWriteSocket = new ServerSocket(VERIFIER_WRITE_PORT);
        System.out.println("----------------------------");
        System.out.println(Colors.WHITE_BOLD + "Stato del server" + Colors.COLOR_RESET);
        System.out.println(Colors.GREEN + "Server ON" + Colors.COLOR_RESET);

        this.loadDatas();

        while(true){
            Socket socket = serverSocket.accept();
            ServerThread st = new ServerThread(this, socket);
            st.start();
        }
    }

}
