package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.object_card.Color;
import it.polimi.ingsw.object_card.ObjectCard;
import it.polimi.ingsw.user.User;

public class RuleCommonXI implements RuleCommon{

    //Eight tiles of the same type. There’s no restriction about the position of these tiles.
    @Override
    public boolean newRule(User user) {
        ObjectCard objectCard;
        int[] tielsPerColor = new int[Color.values().length];
        for (int j=0; j<=user.getBookshelf().getNumColumn()-1; j++)
            for(int i=user.getBookshelf().getNumRow()-1; i>=0; i--){
                if(user.getBookshelf().getBookshelf()[i][j]==null)
                    break;
                objectCard=user.getBookshelf().getBookshelf()[i][j].getObjectCard();
                if(objectCard==null)
                    break;
                tielsPerColor[objectCard.getColor().ordinal()]++;
                if( tielsPerColor[objectCard.getColor().ordinal()]==8)
                    return true;
            }
        return false;
    }

}

