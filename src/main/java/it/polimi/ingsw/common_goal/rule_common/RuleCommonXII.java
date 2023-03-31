package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.user.User;

public class RuleCommonXII implements RuleCommon{
    @Override
    public boolean newRule(User user) {
        int depths;
        int pervDepths=user.getBookshelf().getNumRow()-1;
        while(user.getBookshelf().getBookshelf()[pervDepths][0].getObjectCard()!=null || pervDepths>=0){
            pervDepths--;
        }
        for(int i=1; i<=user.getBookshelf().getNumColumn()-1; i++){
            depths=user.getBookshelf().getNumRow()-1;
            while(user.getBookshelf().getBookshelf()[depths][i].getObjectCard()!=null || depths>=0)
                depths--;
            if(depths-pervDepths!=1)
                break;
            if(i==user.getBookshelf().getNumColumn()-1)
                return true;
        }
        while(user.getBookshelf().getBookshelf()[pervDepths][user.getBookshelf().getNumColumn()-1].getObjectCard()!=null || pervDepths>=0){
            pervDepths--;
        }
        for(int i=user.getBookshelf().getNumColumn()-2; i>=0; i--){
            depths=user.getBookshelf().getNumRow()-1;
            while(user.getBookshelf().getBookshelf()[depths][i].getObjectCard()!=null || depths>=0)
                depths--;
            if(depths-pervDepths!=1)
                break;
            if(i==0)
                return true;
        }
        return false;

    }


}
