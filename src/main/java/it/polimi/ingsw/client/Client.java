package it.polimi.ingsw.client;

import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.client.model.ClientModel;
import it.polimi.ingsw.client.view.CLIController;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.Controller;
import it.polimi.ingsw.client.view.GUIController;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    private ClientMessager messager;
    private ClientModel model;

    private Chat chat;

    private Controller controller;

    public Client() throws IOException {
        this.chat= new Chat();
        this.messager = new ClientMessager(this);
        this.model = new ClientModel();
    }

    public void setMessager(ClientMessager messager) {
        this.messager = messager;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void startViewController(){
        String choose;

        System.out.println("----------------------------");
        System.out.println(Colors.WHITE_BOLD + "Scelta interfaccia utente" + Colors.COLOR_RESET);
        System.out.println("----------------------------");

        System.out.println("Premi C per usare l'interfaccia CLI");
        System.out.println("Premi G per usare l'interfaccia GUI");

        Scanner in = new Scanner(System.in);
        choose = in.nextLine();


        while(!choose.equalsIgnoreCase("C") && !choose.equalsIgnoreCase("G")){
            System.out.println(Colors.RED + "Errore, carattere invalido" + Colors.COLOR_RESET);
            System.out.println("Premi C per usare l'interfaccia CLI");
            System.out.println("Premi G per usare l'interfaccia GUI");
            choose = in.nextLine();
        }

        char chooseChar = Character.toUpperCase(choose.charAt(0));

        switch(chooseChar){
            case 'C':
                controller = new CLIController(this);
                break;
            case 'G':
                controller= new GUIController(this);
                chat.setOpen();
                break;
        }
        chat.setController(controller);

    }

    public Controller getViewController() {
        return controller;
    }

    public ClientMessager getMessager() {
        return messager;
    }

    public ClientModel getModel() {
        return model;
    }


    public void startClient() {
        this.startViewController();
        this.controller.startController();
        this.messager.start();
    }

    public void startClient(char chooseChar) {
        if(chooseChar=='G')chat.setOpen();
        chat.setController(controller);
        this.messager.start();
    }


    public Chat getChat() {
        return chat;
    }
}

