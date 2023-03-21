package it.polimi.ingsw;

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

    public void setObjectCard(ObjectCard objectCard) {
        this.objectCard = objectCard;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
