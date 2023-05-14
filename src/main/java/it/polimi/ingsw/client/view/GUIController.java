package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;

import static javafx.application.Application.launch;

public class GUIController implements Controller{
    private GUI view;

    private static Client client;

    public GUIController(Client client) {
        GUIController.client = client;
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

    public static Client getClient(){
        return client;
    }

}
