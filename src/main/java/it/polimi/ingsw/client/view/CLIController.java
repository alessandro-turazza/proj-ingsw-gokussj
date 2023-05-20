package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientMessager;
import it.polimi.ingsw.client.InputAction;
import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLIController implements Controller{
    private CLI view;
    private Client client;
    private Chat chat;
    private ArrayList<String> actions = new ArrayList<>(Arrays.asList("HELP","DRAG/DROP","BOOKSHELF","PLANK","USERS","COMMON_GOALS","PERSONAL_GOAL", "OPEN_CHAT","EXIT"));
    private InputAction inputAction;
    private boolean inputReady;

    private boolean endGame;

    public CLIController(Client client){
        this.client = client;
        chat =client.getChat();
        this.inputReady = false;
        this.view=new CLI(client);
    }
    public CLI getView() {
        return view;
    }
    public ArrayList<String> getActions() {
        return actions;
    }


    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    @Override
    public void startController(){
        char choose= view.selectTypeGame();
        this.endGame = false;
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

    @Override
    public void resetStart(){
        startController();
    }

    public void handleAction() throws Exception {

        try{
            String action = "";

            action = catchAction();//myTurn);


            if(action == null)
                view.showErrorMessage("Azione non valida");
            else if(action.equals(actions.get(8))){
                client.setChat(new Chat());
                client.setMessager(new ClientMessager(client));
                client.startClient('C');
                this.startController();
            } else if(action.equals(actions.get(0))){
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
    @Override
    public void showStateGame() throws Exception {
        view.showStateGame();
        handleTurn();
    }
    @Override
    public void showEndGame() throws Exception {
        view.showStateGame();
        view.showEndGame();
    }

    @Override
    public  void showErrorMessage(String message){
        view.showErrorMessage(message);
    }
    @Override
    public void showOkConnection(Integer idGame){
        view.showNormalMessage("----------------------------");
        view.showCorrectMessage("Sei stato aggiunto correttamente alla partita " + idGame);
        view.showNormalMessage("In attesa degli altri giocatori...");
    }

    @Override
    public void showChatMessage(JSONObject jsonObject) {
        String message= jsonObject.get("namePlayer").toString() + ": " + jsonObject.get("message").toString();
        view.showNormalMessage(message);
    }

    public String catchAction(/*boolean myTurn*/) {
        String action = "";
        ArrayList<String> possibleActions = this.getActions();

        boolean actOk = false;

        view.showNormalMessage("Digita un azione");
        Scanner in = new Scanner(System.in);

        action = in.nextLine();

        String[] control = action.split(" ");

        if(action.equals(possibleActions.get(1))) {
            if (client.getModel().getMyName().equals(client.getModel().getActiveUser()))
                actOk = true;
        }else if(control[0].equals(possibleActions.get(2))){
            actOk = true;
        }else if(possibleActions.contains(action))
            actOk = true;

        if(actOk == true)
            return action;
        return null;

    }
    public void handleTurn() {
        if(!inputReady){
            this.inputAction = new InputAction(this);
            this.inputReady = true;
            this.inputAction.start();
        }else
            this.getView().showNormalMessage("Digita un azione");
    }


}
