package it.polimi.ingsw.server.model.plank;

import it.polimi.ingsw.server.game_data.DataObjectCard;
import it.polimi.ingsw.server.model.object_card.Color;
import it.polimi.ingsw.server.model.object_card.ObjectCard;

import java.util.ArrayList;

/**
 * This class represents the plank of the game
 */
public class Plank {
    private CellPlank[][] board;
    private CardBag cardBag;
    public static final int DIM=9;


    public Plank() {
        board=new CellPlank[DIM][DIM];
        cardBag=new CardBag();
    }

    public void setBoard(CellPlank[][] board) {
        this.board = board;
    }

    public CellPlank[][] getBoard() {
        return board.clone();
    }

    /**
     * This method inititialize the bag containing all the object cards
     */
    public void initializeCardBag(ArrayList<DataObjectCard> dataObjectCard){
        cardBag.initializeCardBag(dataObjectCard);
    }

    /**
     * This method build the plank in function of the number of the players in game
     */
    public void initializePlank(int[][] plank,int numUser){
        //plank[][] for generic position is the number of player required, 0 if not playable
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(plank[r][c]>numUser || plank[r][c]==0)board[r][c]=null;
                else{
                    CellPlank cellPlank=new CellPlank(null,r,c);
                    board[r][c]=cellPlank;
                }
            }
    }

    /**
     * This method takes some cards from the bag and fills the plank
     */
    public void fillPlank(){
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(board[r][c]!=null && board[r][c].getObjectCard()==null)
                    board[r][c].setObjectCard(cardBag.next());//if cardBag empty return null so not fill the cell
            }
        checkPlayable();
    }
    /**
     * This method controls which positions of the planks are available to be dragged
     */
    public void checkPlayable(){
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(board[r][c]!=null && board[r][c].getObjectCard()!=null){
                    if(r==0||r==DIM-1||c==0||c==DIM-1)board[r][c].setPlayable(true);
                    else if(board[r+1][c]==null || board[r+1][c].getObjectCard()==null)board[r][c].setPlayable(true);
                    else if(board[r-1][c]==null || board[r-1][c].getObjectCard()==null)board[r][c].setPlayable(true);
                    else if(board[r][c+1]==null || board[r][c+1].getObjectCard()==null)board[r][c].setPlayable(true);
                    else board[r][c].setPlayable(board[r][c - 1] == null || board[r][c - 1].getObjectCard() == null);
                }

            }
    }

    /**
     * This method controls if there are the conditions to refill the plank
     */
    public boolean checkRefull(){
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(board[r][c]!=null && board[r][c].getPlayable()){
                    if(r<DIM-1 && board[r+1][c]!=null && board[r + 1][c].getPlayable())return false;
                    if(r>0 && board[r-1][c]!=null && board[r - 1][c].getPlayable())return false;
                    if(c<DIM-1 && board[r][c+1]!=null && board[r][c + 1].getPlayable())return false;
                    if(c>0 && board[r][c-1]!=null && board[r][c - 1].getPlayable())return false;
                }
            }
        return true;
    }
    /**
     * This method modify the status of the plank after a drag action from the player
     */
    public ObjectCard dragObjectCard(int row, int column)throws Exception{
        if(board[row][column]==null || !board[row][column].getPlayable())throw new Exception("Cella non selezionabile");
        ObjectCard result=board[row][column].getObjectCard();
        board[row][column].setObjectCard(null);
        board[row][column].setPlayable(false);
        return result;
    }

    /**
     * This method modify the status of the plank after a drag action from the player
     */
    public ObjectCard dragObjectCard(CellPlank cellPlank)throws Exception{
        return dragObjectCard(cellPlank.getRow(),cellPlank.getColumn());
    }

    /**
     * This method converts a color to a string
     * @deprecated
     */
    private char colorChar(Color color){
        if(color == Color.YELLOW)
            return 'Y';
        if(color == Color.WHITE)
            return 'W';
        if(color == Color.PINK)
            return 'P';
        if(color == Color.BLUE)
            return 'B';
        if(color == Color.LIGHT_BLUE)
            return 'L';
        else
            return 'G';
    }

    /**
     * THis method print the plank in CLI
     * @deprecated
     */
    public void printPlank(){
        for(int i = 0; i < DIM; i++){
            for(int j = 0; j < DIM; j++){
                if(board[i][j] == null || board[i][j].getObjectCard() == null)
                    System.out.print(" ");
                else
                    System.out.print(colorChar(board[i][j].getObjectCard().getColor()));
            }
            System.out.println(" ");
        }
    }

    public int getDIM() {
        return DIM;
    }

    /**
     * This method returns the clone of the plank for state game
     */
    public Plank getPlankClone(){
        CellPlank[][] boardClone=new CellPlank[DIM][DIM];
        for(int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if(board[i][j]!=null && board[i][j].getObjectCard()!=null){
                    ObjectCard objectCard=new ObjectCard(board[i][j].getObjectCard().getId(),board[i][j].getObjectCard().getColor());
                    boardClone[i][j]=new CellPlank(objectCard,i,j);
                }
            }
        }
        Plank plankClone=new Plank();
        plankClone.setBoard(boardClone);
        plankClone.checkPlayable();
        return plankClone;
    }
}
