package it.polimi.ingsw;

import java.awt.dnd.DragGestureEvent;
import java.util.ArrayList;

public class Plank {
    private static CellPlank board[][];
    private static CardBag cardBag;
    public static final int DIM=9;


    public Plank() {
        board=new CellPlank[DIM][DIM];
        cardBag=new CardBag();
    }
    public void initializeCardBag(ArrayList<DataObjectCard> dataObjectCard){
        cardBag.initializeCardBag(dataObjectCard);
    }
    public void initializePlank(int plank[][],int numUser){
        //plank[][] for generic position is the number of player required, 0 if not playable
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(plank[r][c]>numUser || plank[r][c]==0)board[r][c]=null;
                else{
                    CellPlank cellPlank=new CellPlank(null);
                }
            }
    }

    public void fillPlank(int numUser){
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(board[r][c]!=null && board[r][c].getObjectCard()==null)board[r][c].setObjectCard(cardBag.next());//if cardBag empty return null so not fill the cell
            }
        checkPlayable();
    }

    public void checkPlayable(){
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(board[r][c]!=null && board[r][c].getObjectCard()!=null){
                    if(r==0||r==DIM-1||c==0||c==DIM-1)board[r][c].setPlayable(true);
                    else if(board[r+1][c]==null || board[r+1][c].getObjectCard()!=null)board[r][c].setPlayable(true);
                    else if(board[r-1][c]!=null || board[r-1][c].getObjectCard()!=null)board[r][c].setPlayable(true);
                    else if(board[r][c+1]!=null || board[r][c+1].getObjectCard()!=null)board[r][c].setPlayable(true);
                    else if(board[r][c-1]!=null || board[r][c-1].getObjectCard()!=null)board[r][c].setPlayable(true);
                }
                else board[r][c].setPlayable(false);
            }
    }

    public boolean checkRefull(){
        for(int r=0;r<DIM;r++)
            for(int c=0;c<DIM;c++){
                if(board[r][c].getPlayable()){
                    if(r<DIM && board[r+1][c]==null)return false;
                    if(r<0 && board[r-1][c]!=null)return false;
                    if(c<DIM && board[r][c+1]!=null)return false;
                    if(c>0 && board[r][c-1]!=null)return false;
                }
            }
        return true;
    }

    public ObjectCard dragObjectCard(int row,int column)throws Exception{
        if(!board[row][column].getPlayable())throw new Exception("Cella non selezionabile");
        ObjectCard result=board[row][column].getObjectCard();
        board[row][column].setObjectCard(null);
        board[row][column].setPlayable(false);
        return result;
    }

}
