package it.polimi.ingsw;

public class RuleCommonXI implements RuleCommon{
    @Override
    public boolean newRule(User user) {
        ObjectCard objectCard;
        int[] tielsPerColor = new int[Color.values().length];
        for (int j=0; j<=user.getBookshelf().getNumColumn()-1; j--)
            for(int i=user.getBookshelf().getNumRow()-1; i>=0; i++){
                objectCard=user.getBookshelf().getBookshelf()[i][j].getObjectCard();
                if(objectCard==null)
                    break;
                if(tielsPerColor[objectCard.getColor().ordinal()]++==8)
                    return true;
            }
        return false;
    }

}

