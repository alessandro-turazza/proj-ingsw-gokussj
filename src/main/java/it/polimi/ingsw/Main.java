package it.polimi.ingsw;

import it.polimi.ingsw.server.Server;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Server server = new Server();
        server.startServer();
    }

}