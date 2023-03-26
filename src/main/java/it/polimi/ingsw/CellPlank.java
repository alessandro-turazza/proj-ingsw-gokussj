package it.polimi.ingsw;

public class CellPlank {
    private ObjectCard objectCard;
    private int playable;

    public CellPlank(ObjectCard objectCard, int playable) {
        this.objectCard = objectCard;
        this.playable = playable;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }

    public int getPlayable() {
        return playable;
    }

    public void setObjectCard(ObjectCard objectCard) {
        this.objectCard = objectCard;
    }

    public void setPlayable(int playable) {
        this.playable = playable;
    }
}
