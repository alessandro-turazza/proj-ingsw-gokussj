package it.polimi.ingsw.rule_common;

import it.polimi.ingsw.user.User;
import it.polimi.ingsw.user.bookshelf.Bookshelf;

import static it.polimi.ingsw.rule_common.RuleCommonSupportClass.checkDiagonal;

public class RuleCommonII implements RuleCommon{

    //Five tiels of the same type forming a diagonal.
    @Override
    public boolean newRule(User user) {
        Bookshelf bookshelf = user.getBookshelf();
        try{
        if(checkDiagonal(bookshelf,0, 0,5,true) || checkDiagonal(bookshelf,1, 0,5,true) || checkDiagonal(bookshelf,0, 4,5,false) || checkDiagonal(bookshelf,1, 4,5,false))
            return true;
        }
        catch (Exception e){return false;}
        return false;

    }


}
