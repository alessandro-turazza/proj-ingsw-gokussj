package it.polimi.ingsw.server.model.plank;

import it.polimi.ingsw.server.game_data.DataObjectCard;
import it.polimi.ingsw.server.model.object_card.ObjectCard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class represents the bag that contains each object card in order to fill the plank, implemented as an iterator
 */

public class CardBag implements Iterator {
    private ArrayList<ObjectCard> cardBag;

    public CardBag() {
        cardBag=new ArrayList<>();
    }

    /**
     * This method loads the object cards into the bag
     */
    public void initializeCardBag(ArrayList<DataObjectCard> dataObjectCard){
        for(DataObjectCard elemDataobjectCard: dataObjectCard){
            for(int i=0;i<elemDataobjectCard.getNumObjectCard();i++) {
                ObjectCard objectCard = new ObjectCard(elemDataobjectCard.getId(), elemDataobjectCard.getColor());
                cardBag.add(objectCard);
            }
        }
    }


    @Override
    public boolean hasNext() {
        return cardBag.size() != 0;
    }

    @Override
    public ObjectCard next() {
        if(!hasNext())return null;
        Random rnd=new Random();
        int rndIndex= rnd.nextInt(cardBag.size());
        ObjectCard result=cardBag.get(rndIndex);
        cardBag.remove(rndIndex);
        return result;
    }
}
