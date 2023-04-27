package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.object_card.Color;
import it.polimi.ingsw.server.model.object_card.ObjectCard;
import it.polimi.ingsw.server.model.user.User;

public class RuleCommonXI implements RuleCommon{
    private int idRule = 11;

    @Override
    public int getIdRule() {
        return idRule;
    }

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

