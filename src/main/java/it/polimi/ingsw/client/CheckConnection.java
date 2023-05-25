package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.Controller;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class CheckConnection extends Thread {

    private Controller controller;

    @Override
    public void run() {
        while (true){
        try {
            sleep(10000);
            checkConnection();
        } catch (IOException e) {
            controller.showErrorMessage("Connessione a internet assente.\nControlla la connessione e riavvia l'applicazione.");
            break;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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