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

    private Integer checkColumn(int column){ //mettere privato
        for(int i = NUM_ROW - 1; i >= 0; i--){
            if(bookshelf[i][column] == null)
                    return i;
        }

        return null;
    }

    public void insertBookshelf (ObjectCard card, int column)throws Exception{
        CellShelf shelf = new CellShelf(card);

        Integer row = checkColumn(column);

        if(row==null)throw new Exception("Colonna piena");
        bookshelf[row][column] = shelf;
    }
}
