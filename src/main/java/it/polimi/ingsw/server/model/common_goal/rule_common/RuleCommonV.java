package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;
import it.polimi.ingsw.server.model.user.User;

public class RuleCommonV implements RuleCommon {
    //Four tiles of the same type in the four corners of the bookshelf.
    @Override
    public boolean newRule(User user) {
        CellShelf[][] bookshelf=user.getBookshelf().getBookshelf();
        int maxrow=user.getBookshelf().getNumRow()-1,maxcolumn=user.getBookshelf().getNumColumn()-1;
        if(bookshelf[0][0]==null || bookshelf[0][maxcolumn]==null ||bookshelf[maxrow][0]==null ||bookshelf[maxrow][maxcolumn]==null)
            return false;
        if(bookshelf[0][0].getObjectCard()==null || bookshelf[0][maxcolumn].getObjectCard()==null ||bookshelf[maxrow][0].getObjectCard()==null ||bookshelf[maxrow][maxcolumn].getObjectCard()==null )
            return false;
        return (bookshelf[0][0].getObjectCard().getColor()==bookshelf[0][maxcolumn].getObjectCard().getColor() && bookshelf[0][maxcolumn].getObjectCard().getColor()==bookshelf[maxrow][maxcolumn].getObjectCard().getColor() && bookshelf[maxrow][maxcolumn].getObjectCard().getColor()==bookshelf[maxrow][0].getObjectCard().getColor());

    }
}
