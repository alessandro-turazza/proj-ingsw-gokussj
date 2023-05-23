package it.polimi.ingsw.server.model.user.bookshelf;

import it.polimi.ingsw.server.model.object_card.ObjectCard;

/*This class represents the single cell in the bookshelf*/
public class CellShelf {
    private ObjectCard objectCard;
    private boolean marked;

    public CellShelf(ObjectCard objectCard) {
        this.objectCard = objectCard;
        this.marked = false;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
