package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ViewController {
    private View view;
    private Client client;
    private Chat chat;
    private ArrayList<String> actions = new ArrayList<>(Arrays.asList("HELP","DRAG/DROP","BOOKSHELF","PLANK","USERS","COMMON_GOALS","PERSONAL_GOAL", "OPEN_CHAT"));

    public ViewController(Client client){
        this.client = client;
        chat =client.getChat();
    }
    public View getView() {
        return view;
    }
    public ArrayList<String> getActions() {
        return actions;
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
                view = new CLI(client);
                break;
            case 'G':
                view = new GUI();
                break;
        }



    }

    public void setClientDatas(){
        char choose= view.selectTypeGame();
        JSONObject userDatas = view.lobby(choose);

        String nomeClient = userDatas.get("username").toString();
        client.getModel().setMyName(nomeClient);

        if(userDatas.get("type").toString().equals("create")){
            int numPlayers = Integer.parseInt(userDatas.get("numPlayers").toString());

            this.client.getMessager().sendMessage(this.client.getMessager().getMessageHandler().sendCreateGame(numPlayers,nomeClient));
        }else{
            int idGame = Integer.parseInt(userDatas.get("idGame").toString());
            this.client.getMessager().sendMessage(this.client.getMessager().getMessageHandler().sendJoinGame(idGame,nomeClient));
        }
    }

    public void handleAction() throws Exception {

        try{
            String action = "";

            action = view.catchAction();//myTurn);


            if(action == null)
                view.showErrorMessage("Azione non valida");
            else if(action.equals(actions.get(0))){
                for(String s: actions)
                    view.showNormalMessage(s);
            }else if(action.equals(actions.get(1))){
                //drag
                ArrayList<CellPlank> cells = view.drag();
                //drop
                int column = view.drop(cells.size());
                //Reorder
                cells = view.reorderCards(cells);

                client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendDragAndDrop(cells,column));
            }else if(action.equals(actions.get(3))){
                view.showPlank();
            }else if(action.equals(actions.get(4))){
                view.showUsers();
            }else if(action.equals(actions.get(5))){
                view.showCommonGoals();
            }else if(action.equals(actions.get(6))){
                view.showPersonalGoal();
            }else if (action.equals(actions.get(7))) {
                    view.openChat(chat);
            }else{
                try{
                String[] act = action.split(" ");
                String username = act[1];
                    view.showBookshelf(username);
                }catch (ArrayIndexOutOfBoundsException e){
                    view.showErrorMessage("Errore, digita il nome dell'utente di cui vuoi vedere la libreria");
                }
            }
        }catch(InterruptedException e){}

    }


}
