package it.polimi.ingsw.client;

import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.client.model.ClientModel;
import it.polimi.ingsw.client.view.ViewController;

import java.io.IOException;

public class Client {
    private ClientMessager messager;
    private ViewController viewController;
    private ClientModel model;
    private InputAction inputAction;
    private Chat chat;
    private boolean inputReady;

    public Client() throws IOException {
        this.chat= new Chat();
        this.messager = new ClientMessager(this);
        this.viewController = new ViewController(this);
        this.model = new ClientModel();
        this.inputReady = false;
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

    public void setInputReady(boolean inputReady) {
        this.inputReady = inputReady;
    }

    public void startClient() throws Exception {
        this.viewController.startViewController();
        this.viewController.setClientDatas();
        this.messager.start();
    }

    public void handleTurn() throws Exception {
        if(!inputReady){
            this.inputAction = new InputAction(viewController);
            this.inputReady = true;
            this.inputAction.start();
        }else
            this.viewController.getView().showNormalMessage("Digita un azione");
    }

    public Chat getChat() {
        return chat;
    }
}

