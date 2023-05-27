package it.polimi.ingsw.server.model.plank;

import it.polimi.ingsw.server.model.object_card.ObjectCard;

/**
 * This class identifies the cells of the plank containing the object card, row, column and a boolean to check the playability of the card
 */
public class CellPlank {
    private ObjectCard objectCard;
    private int row;
    private int column;
    private boolean playable;

    public CellPlank(ObjectCard objectCard,int row,int column) {
        this.objectCard = objectCard;
        this.playable=false;
        this.row=row;
        this.column=column;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }

    public boolean getPlayable() {
        return playable;
    }

    public void setObjectCard(ObjectCard objectCard) {
        this.objectCard = objectCard;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
