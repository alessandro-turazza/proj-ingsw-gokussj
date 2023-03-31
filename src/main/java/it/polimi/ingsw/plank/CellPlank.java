package it.polimi.ingsw.plank;

import it.polimi.ingsw.object_card.ObjectCard;

public class CellPlank {
    private ObjectCard objectCard;
    private boolean playable;

    public CellPlank(ObjectCard objectCard) {
        this.objectCard = objectCard;
        this.playable=false;
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
}
