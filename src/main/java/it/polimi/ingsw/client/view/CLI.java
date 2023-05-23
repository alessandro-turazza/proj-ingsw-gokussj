package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.client.chat.ChatMessage;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.server.model.user.personal_goal.Costraints;
import it.polimi.ingsw.server.state_game.CommonGoalClone;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI{
    private static final int MAX_CELLS_DROP=3;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private Client client;


    public CLI(Client client) {
        this.client = client;
    }

    public Character selectTypeGame() {
        showNormalMessage("----------------------------");
        showNormalMessage(Colors.WHITE_BOLD + "Scelta tipo partita" + Colors.COLOR_RESET);
        showNormalMessage("----------------------------");
        showNormalMessage("Premi C per creare una nuova partita");
        showNormalMessage("Premi J per unirti ad una partita esistente");
        Scanner in = new Scanner(System.in);

        String choose = in.nextLine();

        while(!choose.equalsIgnoreCase("C") && !choose.equalsIgnoreCase("J")){
            showErrorMessage("Errore, carattere invalido");
            showNormalMessage("Premi C per creare una nuova partita");
            showNormalMessage("Premi J per unirti ad una partita esistente");
            choose = in.nextLine();
        }

        return Character.toUpperCase(choose.charAt(0));
    }


    public JSONObject lobby(Character choose) {

        JSONObject userDatas = new JSONObject();
        Scanner in = new Scanner(System.in);
        showNormalMessage("----------------------------");

        switch(choose){
            case 'C':
                showNormalMessage(Colors.WHITE_BOLD + "Creazione di una nuova partita" + Colors.COLOR_RESET);
                break;
            case 'J':
                showNormalMessage(Colors.WHITE_BOLD + "Inserimento in una partita esistente" + Colors.COLOR_RESET);
                break;
        }
        showNormalMessage("----------------------------");

        showNormalMessage("Inserisci il nome utente");
        String name = in.nextLine();

        while(name.equals("")){
            showNormalMessage("Inserisci il nome utente");
            name = in.nextLine();
        }

        userDatas.put("username", name);

        switch (choose){
            case 'C':

                userDatas.put("type", "create");
                int nPlayers;

                do {
                    showNormalMessage("Inerisci il numero dei giocatori (da 2 a 4)");
                    String num = in.nextLine();

                    try{
                        nPlayers = Integer.parseInt(num);

                        if(nPlayers < MIN_PLAYERS || nPlayers > MAX_PLAYERS)
                            showErrorMessage("Errore, Numero non valido");

                    }catch(Exception e){
                        showErrorMessage("Errore, Numero non valido");
                        nPlayers = 0;
                    }
                } while (nPlayers < MIN_PLAYERS || nPlayers > MAX_PLAYERS);

                    userDatas.put("numPlayers", nPlayers);
                break;
            case 'J':

                userDatas.put("type", "join");
                int idGame;

                do {
                    showNormalMessage("Inserisci l'ID della partita");
                    String num = in.nextLine();
                    try{
                        idGame = Integer.parseInt(num);
                        if(idGame <= 0)
                            showErrorMessage("Errore, IdGame non valido");
                    }catch(Exception e){
                        showErrorMessage("Errore, IdGame non valido");
                        idGame = 0;
                    }
                } while (idGame <= 0);

                userDatas.put("idGame", idGame);
                break;
        }


        return userDatas;
    }




    public void showNormalMessage(String message) {
        System.out.println(message);
    }


    public void showCorrectMessage(String message) {
        System.out.println(Colors.GREEN + message + Colors.COLOR_RESET);
    }

    public void showErrorMessage(String message) {
        System.out.println(Colors.RED + message + Colors.COLOR_RESET);
    }


    public void showStateGame() {
        showNormalMessage("----------------------------");
        if(!client.getModel().isLastTurn())
            showNormalMessage(Colors.WHITE_BOLD + "Nuovo turno"+ Colors.COLOR_RESET);
        else showNormalMessage(Colors.RED_BOLD + "Ultimo turno"+ Colors.COLOR_RESET);
        showNormalMessage("----------------------------");
        this.showUsers();
        this.showPlank();
        this.showCommonGoals();
    }


    public void showPlank() {
        int dimPlank = client.getModel().getPlank().getDIM();
        CellPlank[][] board = client.getModel().getPlank().getBoard();

        System.out.print("  ");
        for(int i = 0; i < dimPlank; i++)
            System.out.print(" "+i+"  ");

        System.out.println();

        for(int i = 0; i < dimPlank; i++){
            System.out.print(i+" ");
            for(int j = 0; j < dimPlank; j++){

                if(board[i][j] == null || board[i][j].getObjectCard() == null)
                    System.out.print("    ");
                else
                    System.out.print(Colors.colorChar(board[i][j].getObjectCard().getColor()) + " ");
            }
            System.out.println(" ");
            System.out.println(" ");
        }
    }



    public void showBookshelf(String username){
        Bookshelf bookshelf = client.getModel().getBookshelf(username);
        if(bookshelf==null){
            showErrorMessage("Errore, username non trovato");
            return;
        }
        CellShelf[][] cellShelf = bookshelf.getBookshelf();

        showNormalMessage("Libreria di " + username);

        for(int i = 0; i < bookshelf.getNumColumn(); i++)
            System.out.print("  "+i+" ");

        System.out.println();
        System.out.println("+---+---+---+---+---+");

        for(int i = 0; i < bookshelf.getNumRow(); i++){
            System.out.print("|");
            for(int j = 0; j < bookshelf.getNumColumn(); j++){
                if(cellShelf[i][j] != null)
                    System.out.print(Colors.colorChar(cellShelf[i][j].getObjectCard().getColor()) + "|");
                else
                    System.out.print("   |");
            }
            System.out.println(" ");
            System.out.println("+---+---+---+---+---+");
        }
    }

    public void showPersonalGoalBookshelf(){
        Bookshelf bookshelf = client.getModel().getMyBookshelf();
        ArrayList<Costraints> costraints = client.getModel().getUserByName(client.getModel().getMyName()).getPersonalGoal().getCostraints();
        if(bookshelf==null){
            showErrorMessage("Errore, username non trovato");
            return;
        }
        CellShelf[][] cellShelf = new CellShelf[bookshelf.getNumRow()][bookshelf.getNumColumn()];
        for(Costraints temp:costraints)cellShelf[temp.getRow()][temp.getColumn()]=new CellShelf(new ObjectCard(1,temp.getColor()));

        for(int i = 0; i < bookshelf.getNumColumn(); i++)
            System.out.print("  "+i+" ");

        System.out.println();
        System.out.println("+---+---+---+---+---+");

        for(int i = 0; i < bookshelf.getNumRow(); i++){
            System.out.print("|");
            for(int j = 0; j < bookshelf.getNumColumn(); j++){
                if(cellShelf[i][j] != null)
                    System.out.print(Colors.colorChar(cellShelf[i][j].getObjectCard().getColor()) + "|");
                else
                    System.out.print(" x |");
            }
            System.out.println(" ");
            System.out.println("+---+---+---+---+---+");
        }
    }

    public void showPersonalGoal() {
        User user = client.getModel().getUserByName(client.getModel().getMyName());
        showNormalMessage("Personal goal: " + user.getPersonalGoal().getId());
        showPersonalGoalBookshelf();
    }



    public void showUsers() {
        showNormalMessage("Giocatori: ");

        for(User u: client.getModel().getPlayers()){
            if(u.getName().equals(client.getModel().getActiveUser()))
                showNormalMessage(Colors.WHITE_BOLD + "->"+u.getName()+"<- "+Colors.COLOR_RESET+ "Punteggio: "+u.getPoints());
            else
                showNormalMessage(u.getName()+ " Punteggio: "+u.getPoints());
        }
        showNormalMessage("");
    }



    public void showCommonGoals() {
        for(CommonGoalClone commonGoal: client.getModel().getCommonGoals()){
            if(commonGoal.getTokens() != null && commonGoal.getTokens().size() > 0)
                showNormalMessage("Obiettivo comune " + commonGoal.getId() + ": " + commonGoal.getIdRule() + " Token: " + commonGoal.getLastTokenCard().getPoints()+"\n Testo: "+client.getModel().getTextCommonGoal(commonGoal.getIdRule()));
        }
        showNormalMessage("");
    }

    public ArrayList<CellPlank> drag() {
        this.showPlank();
        ArrayList<CellPlank> cells = new ArrayList<>();
        String input;
        boolean exit = false;
        int min=MAX_CELLS_DROP;
        int maxCellFreeBookshelf=0;
        for(int i=0;i<client.getModel().getMyBookshelf().getNumColumn();i++){
            Integer temp=client.getModel().getMyBookshelf().checkColumn(i);
            if(temp!=null) if(temp+1>maxCellFreeBookshelf)maxCellFreeBookshelf=client.getModel().getMyBookshelf().checkColumn(i)+1;
        }
        min=Math.min(min,maxCellFreeBookshelf);

        do{
            showNormalMessage("Inserisci coordinate della carta da prelevare (<riga>,<colonna>/STOP)");
            Scanner in = new Scanner(System.in);

            input = in.nextLine();

            if(input.equals("STOP")){
                exit = true;
                if(cells.size() == 0){
                    showErrorMessage("Errore, nessuna tessera scelta");
                    exit = false;
                }
            }else{
                int row = -1;
                int column = -1;

                try{
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

        }while(!exit && cells.size() < min);

        return cells;

    }


    public int drop(int numCards) {
        showBookshelf(client.getModel().getMyName());
        int numColonna = -1;
        boolean exit;
        Scanner in = new Scanner(System.in);
        Bookshelf bookshelf = client.getModel().getMyBookshelf();

        do{
            exit = true;
            showNormalMessage("Inserisci il numero della colonna dove inserire le tessere");

            String col = in.nextLine();

            try {
                numColonna = Integer.parseInt(col);

                if(numColonna < 0 || numColonna >= bookshelf.getNumColumn() || !client.getModel().checkDrop(numCards,numColonna)){
                    exit = false;
                    showErrorMessage("Impossibile riempire la colonna");
                }
            } catch (Exception e){
                showErrorMessage("Errore: inserire un numero");
                exit = false;
            }
        }while(!exit);

        return numColonna;
    }


    public ArrayList<CellPlank> reorderCards(ArrayList<CellPlank> cells) {
        for(int i = 0; i < cells.size(); i++){
            System.out.println(i+1 + ". Tessera: " + Colors.colorChar(cells.get(i).getObjectCard().getColor()));
            System.out.println(" ");
        }

        ArrayList<CellPlank> cellsOrdered = new ArrayList<>();
        ArrayList<Integer> alreadyInsert = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        for (int i = 0; i < cells.size(); i++) {
            System.out.println("Inserisci il numero della " + (i + 1) + " tessera da inserire");
            String ind = in.nextLine();

            try {
                int index = Integer.parseInt(ind);

                while (index < 1 || index > cells.size() || alreadyInsert.contains(index)) {
                    showErrorMessage("Azione non valida");
                    System.out.println("Inserisci il numero della " + (i + 1) + " tessera da inserire");
                    ind = in.nextLine();
                    index = Integer.parseInt(ind);
                }

                alreadyInsert.add(index);
                cellsOrdered.add(cells.get(index - 1));
            }
            catch (Exception e){
                showErrorMessage("Errore, inserire un numero");
                i--;
            }
        }
        return cellsOrdered;

    }


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

        showNormalMessage("----------------------------");
        showNormalMessage(Colors.WHITE_BOLD + "Fine partita" + Colors.COLOR_RESET);

        for(int i = 0; i < users.size(); i++){
            System.out.println((i+1)+". " +users.get(i).getName()+" punti:  " + users.get(i).getPoints());
        }

        showNormalMessage("Digita un azione");

        ((CLIController) client.getViewController()).setEndGame(true);

    }



    public void openChat(Chat chat) {
        chat.setOpen();
        showNormalMessage("Digita CLOSE_CHAT per chiudere");
        showNormalMessage("Chat:");
        for(ChatMessage chatMessage: chat.chatPrint())showNormalMessage(chatMessage.getNamePlayer()+": "+chatMessage.getMessage());
        Scanner in = new Scanner(System.in);
        String message;
        while (true){
            message = in.nextLine();
            if(message.equals("CLOSE_CHAT")) {
                chat.resetOpen();
                break;
            }
            client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendMessageChat(message, client.getModel().getMyName()));
        }

    }



}
