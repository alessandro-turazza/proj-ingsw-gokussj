package it.polimi.ingsw.server.model.user.bookshelf;

import it.polimi.ingsw.server.model.object_card.ObjectCard;

import java.util.ArrayList;

public class Bookshelf {
    private CellShelf[][] bookshelf; //Convemzione (0,0) alto a sinistra
    private static final int NUM_ROW=6;
    private static final int NUM_COLUMN = 5;

    public Bookshelf() {
        this.bookshelf = new CellShelf[getNumRow()][getNumColumn()];
    }

    public Bookshelf(CellShelf[][] bookshelf){
        this.bookshelf = bookshelf;
    }

    public CellShelf[][] getBookshelf() {
        return bookshelf;
    }

    public int getNumRow(){
        return NUM_ROW;
    }

    public int getNumColumn(){
        return NUM_COLUMN;
    }
    public Integer checkColumn(int column){
        for(int i = getNumRow() - 1; i >= 0; i--){
            if(bookshelf[i][column] == null)
                    return i;
        }

        return null;
    }



    public void insertBookshelf (ObjectCard card, int column)throws Exception{
        if(column < 0 || column >= getNumColumn()) throw new Exception("Colonna Non Valida");

        CellShelf shelf = new CellShelf(card);

        Integer row = checkColumn(column);

        if(row == null) throw new Exception("Colonna piena");
        bookshelf[row][column] = shelf;

    }

    public boolean isFull(){
        for(int i = 0; i < getNumColumn(); i++){
            if(checkColumn(i) != null)
                return false;
        }

        return true;

    }

    public void deleteMark(){
        for(int i = 0; i < getNumRow(); i++){
            for(int j = 0; j < getNumColumn(); j++){
                if(bookshelf[i][j] != null)
                    bookshelf[i][j].setMarked(false);
            }
        }
    }

    public ArrayList<Integer> numberAdjacenses(){
        ArrayList<Integer> numAdj = new ArrayList<>();
        this.deleteMark();
        for(int i = 0; i < getNumRow(); i++){
            for(int j = 0; j < getNumColumn(); j++){
                if(bookshelf[i][j] != null && !bookshelf[i][j].isMarked())
                    numAdj.add(checkAdjacences(bookshelf[i][j],i,j));
            }
        }

        this.deleteMark();

        return numAdj;
    }

    public int checkAdjacences(CellShelf cellShelf, int row, int column){
        int counter = 1;
        cellShelf.setMarked(true);

        if(row > 0 && bookshelf[row-1][column] != null &&  cellShelf.getObjectCard().getColor() == bookshelf[row-1][column].getObjectCard().getColor() && !bookshelf[row - 1][column].isMarked())
            counter += checkAdjacences(bookshelf[row-1][column], row-1, column);

        if(row < getNumRow()-1 && bookshelf[row+1][column] != null && cellShelf.getObjectCard().getColor() == bookshelf[row+1][column].getObjectCard().getColor() && !bookshelf[row + 1][column].isMarked())
            counter += checkAdjacences(bookshelf[row+1][column], row+1, column);

        if(column > 0 && bookshelf[row][column-1] != null && cellShelf.getObjectCard().getColor() == bookshelf[row][column-1].getObjectCard().getColor() && !bookshelf[row][column - 1].isMarked())
            counter += checkAdjacences(bookshelf[row][column-1], row, column-1);

        if(column < getNumColumn()-1 && bookshelf[row][column+1] != null && cellShelf.getObjectCard().getColor() == bookshelf[row][column+1].getObjectCard().getColor() && !bookshelf[row][column + 1].isMarked())
            counter += checkAdjacences(bookshelf[row][column+1], row, column+1);

        return counter;

    }
    public ObjectCard getObjectCard(int row,int column){
        if(bookshelf[row][column]==null)return null;
        return bookshelf[row][column].getObjectCard();
    }

}
