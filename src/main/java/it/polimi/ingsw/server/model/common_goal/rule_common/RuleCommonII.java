package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;

public class RuleCommonII implements RuleCommon{
    private int idRule = 2;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Five tiels of the same type forming a diagonal.
    @Override
    public boolean newRule(User user) {
        Bookshelf bookshelf = user.getBookshelf();
        try{
        if(RuleCommonSupportClass.checkDiagonal(bookshelf,0, 0,5,true) || RuleCommonSupportClass.checkDiagonal(bookshelf,1, 0,5,true) || RuleCommonSupportClass.checkDiagonal(bookshelf,0, 4,5,false) || RuleCommonSupportClass.checkDiagonal(bookshelf,1, 4,5,false))
            return true;
        }
        catch (Exception e){return false;}
        return false;

    }


}
