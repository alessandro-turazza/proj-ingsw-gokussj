package it.polimi.ingsw;

import java.util.ArrayList;

public class Plank {
    private static CellPlank board[][];
    private static ArrayList<ObjectCard> cardBag;
    private static final int NUM_OBJECTCARD=22;


    public Plank(int row,int column) {
        board=new CellPlank[row][column];
        cardBag=new ArrayList<>();
    }

    public void initializeCardBag(ArrayList<ObjectCard> dataObjectCard){

    }
}
