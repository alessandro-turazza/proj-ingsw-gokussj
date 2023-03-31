package it.polimi.ingsw.game_data;

import it.polimi.ingsw.object_card.ObjectCard;

public class DataObjectCard {
    private ObjectCard objectCard;
    private int numObjectCard;

    public DataObjectCard(ObjectCard objectCard, int numObjectCard) {
        this.objectCard = objectCard;
        this.numObjectCard = numObjectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }

    public void setObjectCard(ObjectCard objectCard) {
        this.objectCard = objectCard;
    }

    public int getNumObjectCard() {
        return numObjectCard;
    }

    public void setNumObjectCard(int numObjectCard) {
        this.numObjectCard = numObjectCard;
    }
}
