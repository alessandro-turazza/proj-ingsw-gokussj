package it.polimi.ingsw.user.personal_goal;

import it.polimi.ingsw.object_card.Color;

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
