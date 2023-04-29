package it.polimi.ingsw.server.message;

import java.util.ArrayList;

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
