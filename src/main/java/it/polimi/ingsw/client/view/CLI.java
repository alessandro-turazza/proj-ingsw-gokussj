package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.client.chat.ClientChatWriter;
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

    private ClientChatWriter chatWriter;

    public CLI(Client client) {
        this.client = client;
        chatWriter = new ClientChatWriter();
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

        System.out.print("  ");
        for(int i = 0; i < dimPlank; i++)
            System.out.print(i+" ");

        System.out.println("");

        for(int i = 0; i < dimPlank; i++){
            System.out.print(i+" ");
            for(int j = 0; j < dimPlank; j++){

                if(board[i][j] == null || board[i][j].getObjectCard() == null)
                    System.out.print("- ");
                else
                    System.out.print(Colors.colorChar(board[i][j].getObjectCard().getColor()) + " ");
            }
            System.out.println(" ");
        }
    }

    @Override
    public void showBookshelfs() {

    }

    @Override
    public void showBookshelf(String username){
        Bookshelf bookshelf = client.getModel().getBookshelf(username);
        if(bookshelf==null){
            showErrorMessage("Errore nickname non trovato");
            return;
        }
        CellShelf[][] cellShelf = bookshelf.getBookshelf();

        showNormalMessage("Libreria di " + username);

        for(int i = 0; i < bookshelf.getNumColumn(); i++)
            System.out.print(i+" ");

        System.out.println("");

        for(int i = 0; i < bookshelf.getNumRow(); i++){
            for(int j = 0; j < bookshelf.getNumColumn(); j++){
                if(cellShelf[i][j] != null)
                    System.out.print(Colors.colorChar(cellShelf[i][j].getObjectCard().getColor()) + " ");
                else
                    System.out.print("- ");
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
            if(commonGoal.getTokens() != null && commonGoal.getTokens().size() > 0)
                showNormalMessage("Obiettivo comune " + commonGoal.getId() + ": " + commonGoal.getIdRule() + " Token: " + commonGoal.getLastTokenCard().getPoints());
        }
    }

    @Override
    public void showCommonGoal(int idCommonGoal) {
    }
    @Override
    public String catchAction(/*boolean myTurn*/) {
        String action = "";
        ArrayList<String> possibleActions = client.getViewController().getActions();

        boolean actOk = false;

        showNormalMessage("Digita un azione");
        Scanner in = new Scanner(System.in);

        action = in.nextLine();

        String[] control = action.split(" ");
    try {
        if(action.equals(possibleActions.get(1))) {
            if (client.getModel().getMyName().equals(client.getModel().getActiveUser()))
                actOk = true;
        }else if(control[0].equals(possibleActions.get(2))){
            for(User user: client.getModel().getPlayers()){
                if(control[1].equals(user.getName()))
                    actOk = true;
            }
        }else if(possibleActions.contains(action))
            actOk = true;
    }catch (ArrayIndexOutOfBoundsException e){
        showErrorMessage("Digita il nome dell'utente di cui vuoi vedere la libreria");
    }


        if(actOk == true)
            return action;
        return null;

    }


    @Override
    public ArrayList<CellPlank> drag() {
        this.showPlank();
        ArrayList<CellPlank> cells = new ArrayList<>();
        String input = "";
        boolean exit = false;


        do{
            showNormalMessage("Inserisci coordinate della carta da prelevare (<riga>,<colonna>/STOP)");
            Scanner in = new Scanner(System.in);

            input = in.nextLine();

            if(input.equals("STOP"))
                exit = true;
            else{
                int row = -1;
                int column = -1;

                try{
                    input.trim();
                    String[] coordinates = input.split(",");

                    row = Integer.parseInt(coordinates[0]);
                    column = Integer.parseInt(coordinates[1]);

                    CellPlank c = client.getModel().getCellPlank(row, column);

                    if(c != null){
                        cells.add(c);
                        if(client.getModel().checkDrag(cells)){
                            showCorrectMessage("Tessera aggiunta correttamente");
                        }else{
                            cells.remove(cells.size()-1);
                            showErrorMessage("Tessera bloccata");
                        }
                    }else{
                        showErrorMessage("Tessera vuota");
                    }
                }catch(Exception e){
                    showErrorMessage("Formato invalido");
                }

            }

        }while(!exit && cells.size() < 3);

        return cells;

    }

    @Override
    public int drop(int numCards) throws Exception {
        showBookshelf(client.getModel().getMyName());
        int numColonna = -1;
        boolean exit = true;

        Bookshelf bookshelf = client.getModel().getMyBookshelf();

        do{
            exit = true;
            showNormalMessage("Inserisci il numero della colonna dove inserire le tessere");
            Scanner in = new Scanner(System.in);
            numColonna = in.nextInt();

            if(numColonna < 0 || numColonna >= bookshelf.getNumColumn() || !client.getModel().checkDrop(numCards,numColonna)){
                exit = false;
                showErrorMessage("Impossibile riempire la colonna");
            }

        }while(!exit);

        return numColonna;
    }

    @Override
    public ArrayList<CellPlank> reorderCards(ArrayList<CellPlank> cells) {
        for(int i = 0; i < cells.size(); i++){
            System.out.println(i+1 + ". Tessera: " + Colors.colorChar(cells.get(i).getObjectCard().getColor()));
        }

        ArrayList<CellPlank> cellsOrdered = new ArrayList<>();
        ArrayList<Integer> alreadyInsert = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        for(int i = 0; i < cells.size(); i++){
            System.out.println("Inserisci il numero della "+ (i+1) + " tessera da inserire");
            int index = in.nextInt();

            while(index < 1 || index > cells.size() || alreadyInsert.contains(index)){
                showErrorMessage("Azione non valida");
                System.out.println("Inserisci il numero della "+ (i+1) + " tessera da inserire");
                index = in.nextInt();
            }

            alreadyInsert.add(index);
            cellsOrdered.add(cells.get(index-1));
        }

        return cellsOrdered;

    }

    @Override
    public void showEndGame() {
        ArrayList<User> users = client.getModel().getPlayers();

        for(int i = 0; i < users.size() - 1; i++){
            for(int j = i+1; j < users.size(); j++){
                if(users.get(j).getPoints() > users.get(i).getPoints()){
                    User temp = users.get(i).getUserClone();
                    users.set(i,users.get(j));
                    users.set(j,temp);
                }
            }
        }

        for(int i = 0; i < users.size(); i++){
            System.out.println((i+1)+". " +users.get(i).getName()+" punti:  " + users.get(i).getPoints());
        }
    }

    @Override
    public ClientChatWriter getChatWriter() {
        return chatWriter;
    }

    @Override
    public void openChat(Chat chat) {
        showNormalMessage("Chat:");
        showNormalMessage("Digita CLOSE_CHAT per chiudere");
        chat.chatPrint();
        Scanner in = new Scanner(System.in);String message;
        JSONObject obj = new JSONObject();
        while (true){
            message = in.nextLine();
            if(message.equals("CLOSE_CHAT"))
                break;
            chatWriter.sendMessage(message);
        }

    }

}
