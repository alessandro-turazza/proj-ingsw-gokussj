package it.polimi.ingsw;

public class Bookshelf {
    private CellShelf bookshelf[][]; //Convemzione (0,0) alto a sinistra
    private static final int NUM_ROW=6;
    private static final int NUM_COLUMN = 5;

    public Bookshelf() {
        this.bookshelf = new CellShelf[NUM_ROW][NUM_COLUMN];
    }

    public CellShelf[][] getBookshelf() {
        return bookshelf;
    }

    private Integer checkColumn(int column){
        for(int i = NUM_ROW - 1; i >= 0; i--){
            if(bookshelf[i][column] == null)
                    return i;
        }

        return null;
    }

    public void insertBookshelf (ObjectCard card, int column)throws Exception{
        CellShelf shelf = new CellShelf(card);

        Integer row = checkColumn(column);

        if(row == null) throw new Exception("Colonna piena");
        bookshelf[row][column] = shelf;

    }

    public boolean isFull(){
        for(int i = 0; i < NUM_COLUMN; i++){
            if(checkColumn(i) != null)
                return false;
        }

        return true;

    }

    public int checkAdjacences(CellShelf cellShelf, int row, int column){
        int counter = 1;
        cellShelf.setMarked(true);

        if(row > 0 && bookshelf[row-1][column] != null &&  cellShelf.getObjectCard().getColor() == bookshelf[row-1][column].getObjectCard().getColor() && bookshelf[row-1][column].isMarked() == false)
            counter += checkAdjacences(bookshelf[row-1][column], row-1, column);

        if(row < NUM_ROW-1 && bookshelf[row+1][column] != null && cellShelf.getObjectCard().getColor() == bookshelf[row+1][column].getObjectCard().getColor() && bookshelf[row+1][column].isMarked() == false)
            counter += checkAdjacences(bookshelf[row+1][column], row+1, column);

        if(column > 0 && bookshelf[row][column-1] != null && cellShelf.getObjectCard().getColor() == bookshelf[row][column-1].getObjectCard().getColor() && bookshelf[row][column-1].isMarked() == false)
            counter += checkAdjacences(bookshelf[row][column-1], row, column-1);

        if(column < NUM_COLUMN-1 && bookshelf[row][column+1] != null && cellShelf.getObjectCard().getColor() == bookshelf[row][column+1].getObjectCard().getColor() && bookshelf[row][column+1].isMarked() == false)
            counter += checkAdjacences(bookshelf[row][column+1], row, column+1);

        return counter;

    }
}
