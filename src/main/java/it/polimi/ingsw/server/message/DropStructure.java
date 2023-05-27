package it.polimi.ingsw.server.message;

import java.util.ArrayList;

/**
 * This class represents the structure after a drop command, containing the rows and columns of the cards and the column of the bookshelf
 * in which insert them
 */
public class DropStructure {
    int column;

    ArrayList<Integer> rows;
    ArrayList<Integer> columns;

    public DropStructure(){
        rows = new ArrayList<>();
        columns = new ArrayList<>();
    }

    public ArrayList<Integer> getColumns() {
        return columns;
    }

    public ArrayList<Integer> getRows() {
        return rows;
    }

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

}
