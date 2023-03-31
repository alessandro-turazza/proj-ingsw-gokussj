package it.polimi.ingsw.plank;

import it.polimi.ingsw.game_data.DataObjectCard;
import it.polimi.ingsw.object_card.ObjectCard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CardBag implements Iterator {
    private static ArrayList<ObjectCard> cardBag;

    public CardBag() {
        cardBag=new ArrayList<>();
    }
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
