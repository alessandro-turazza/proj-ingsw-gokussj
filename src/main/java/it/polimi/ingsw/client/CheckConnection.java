package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CheckConnection extends Thread {

    private Controller controller;

    @Override
    public void run() {
        while (true){
        try {
            checkConnection();
        } catch (IOException e) {
            controller.showErrorMessage("Connessione a internet assente");
        }
    }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void checkConnection() throws IOException {
        URL url = new URL("http://www.google.com");
        URLConnection connection = url.openConnection();
        connection.connect();
    }
}