package it.polimi.ingsw;

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
            for(int i=0;i<elemDataobjectCard.getNumObjectCard();i++)
                cardBag.add(elemDataobjectCard.getObjectCard());
        }
    }


    @Override
    public boolean hasNext() {
        if(cardBag.size()==0)return false;
        return true;
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
