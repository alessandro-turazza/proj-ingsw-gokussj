package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.server.state_game.CommonGoalClone;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI implements View{

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private Client client;

    public CLI(Client client) {
        this.client = client;
    }

    @Override
    public JSONObject lobby() {
        JSONObject userDatas = new JSONObject();

        showNormalMessage("----------------------------");

        showNormalMessage("Inserisci il nome utente");
        Scanner in = new Scanner(System.in);

        userDatas.put("username", in.nextLine());

        showNormalMessage("Premi C per creare una nuova partita");
        showNormalMessage("Premi J per unirti ad una partita esistente");

        char choose = Character.toUpperCase(in.nextLine().charAt(0));

        while(choose != 'C' && choose != 'J'){
            showErrorMessage("Carattere invalido, reinserisci la scelta");
            choose = Character.toUpperCase(in.nextLine().charAt(0));
        }

        switch (choose){
            case 'C':

                userDatas.put("type", "create");
                int nPlayers;

                do{
                    showNormalMessage("Inerisci il numero dei giocatori (da 2 a 4)");
                    nPlayers = in.nextInt();

                }while(nPlayers < MIN_PLAYERS || nPlayers > MAX_PLAYERS);

                userDatas.put("numPlayers", nPlayers);
                break;
            case 'J':
                showNormalMessage("Inserisci l'ID della partita");
                userDatas.put("type", "join");
                userDatas.put("idGame", in.nextInt());
                break;
        }

        showNormalMessage("----------------------------");

        return userDatas;
    }

    @Override
    public void showNormalMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showCorrectMessage(String message) {
        System.out.println(Colors.GREEN + message + Colors.COLOR_RESET);
    }
    @Override
    public void showErrorMessage(String message) {
        System.out.println(Colors.RED + message + Colors.COLOR_RESET);
    }

    @Override
    public void showStateGame() throws Exception {
        showNormalMessage("----------------------------");
        this.showUsers();
        this.showPlank();
        this.showCommonGoals();
        this.showBookshelf(client.getModel().getMyName());
        this.showPersonalGoal();
    }

    @Override
    public void showPlank() {
        int dimPlank = client.getModel().getPlank().getDIM();
        CellPlank[][] board = client.getModel().getPlank().getBoard();

        for(int i = 0; i < dimPlank; i++){
            for(int j = 0; j < dimPlank; j++){
                if(board[i][j] == null || board[i][j].getObjectCard() == null)
                    System.out.print(" ");
                else
                    System.out.print(Colors.colorChar(board[i][j].getObjectCard().getColor()));
            }
            System.out.println(" ");
        }
    }

    @Override
    public void showBookshelfs() {

    }

    @Override
    public void showBookshelf(String username) throws Exception {
        Bookshelf bookshelf = client.getModel().getBookshelf(username);
        CellShelf[][] cellShelf = bookshelf.getBookshelf();

        showNormalMessage("Libreria di " + username);

        for(int i = 0; i < bookshelf.getNumRow(); i++){
            for(int j = 0; j < bookshelf.getNumColumn(); j++){
                if(cellShelf[i][j] != null)
                    System.out.print(cellShelf[i][j].getObjectCard().getId());
                else
                    System.out.print("-");
            }
            System.out.println(" ");
        }
    }

    @Override
    public void showPersonalGoal() {
        User user = client.getModel().getUserByName(client.getModel().getMyName());
        showNormalMessage("Personal goal: " + user.getPersonalGoal().getId());
    }


    @Override
    public void showUsers() {
        showNormalMessage("Giocatori: ");

        for(User u: client.getModel().getPlayers()){
            if(u.getName().equals(client.getModel().getActiveUser()))
                showNormalMessage("->"+u.getName()+"<-");
            else
                showNormalMessage(u.getName());
        }
    }


    @Override
    public void showCommonGoals() {
        for(CommonGoalClone commonGoal: client.getModel().getCommonGoals()){
            showNormalMessage("Obiettivo comune " + commonGoal.getId() + ": " + commonGoal.getIdRule());
        }
    }

    @Override
    public void showCommonGoal(int idCommonGoal) {
    }

    @Override
    public String catchAction(boolean myTurn) {
        String action = "";
        ArrayList<String> possibleActions = client.getViewController().getActions();
        boolean exit = false;

        do{
            showNormalMessage("Digita un azione (DRAG/DROP/BOOKSHELF <NAME>)");
            Scanner in = new Scanner(System.in);
            action = in.nextLine();
            String[] control = action.split(" ");

            if(control[0].equals(possibleActions.get(0)) || control.equals(possibleActions.get(1)))
                exit = true;

            if((control[0].equals(possibleActions.get(0)) && !myTurn) || (control[0].equals(possibleActions.get(1)) && !myTurn) ){
                showErrorMessage("Non è il tuo turno");
                exit = false;
            }

            if(control[0].equals(possibleActions.get(2))){
                for(User user: client.getModel().getPlayers()){
                    if(control[1].equals(user.getName()))
                        exit = true;
                }
            }

            if(!exit)
                showErrorMessage("Azione non valida");

        }while(!exit);

        return action;

    }

    @Override
    public ArrayList<CellPlank> drag() {
        return null;
    }

    @Override
    public int drop() {
        return 0;
    }

    @Override
    public void showEndGame() {

    }
}
