package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;

import static javafx.application.Application.launch;

public class GUIController implements Controller{
    private GUI view;

    private Client client;

    public GUIController(Client client) {
        this.view = new GUI();
        this.client=client;
    }


    @Override
    public void startController() {
        launch(GUI.class);
    }

    @Override
    public void showStateGame() throws Exception {

    }

    @Override
    public void showEndGame() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showOkConnection(Integer idGame) {

    }

    public Client getClient() {
        return client;
    }
}
