package it.polimi.ingsw;

public class Costraints {
    private int row;
    private int column;
    private Color color;

    public Costraints(int row, int column, Color color) {
        this.row = row;
        this.column = column;
        this.color = color;
    }

    public int getRow() {
        return row;
    }


    public int getColumn() {
        return column;
    }


    public Color getColor() {
        return color;
    }

}
