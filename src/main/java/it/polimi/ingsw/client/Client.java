package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.ClientModel;
import it.polimi.ingsw.client.view.ViewController;

import java.io.IOException;

public class Client {
    private ClientMessager messager;
    private ViewController viewController;
    private ClientModel model;

    public Client() throws IOException {
        this.messager = new ClientMessager(this);
        this.viewController = new ViewController(this);
        this.model = new ClientModel();
    }
    public ViewController getViewController() {
        return viewController;
    }

    public ClientMessager getMessager() {
        return messager;
    }

    public ClientModel getModel() {
        return model;
    }

    public void startClient() throws Exception {
        this.viewController.startViewController();
        this.viewController.setClientDatas();
        this.messager.start();
    }

    public void handleTurn() throws Exception {
        while (true)
            this.viewController.handleAction();
    }


}

