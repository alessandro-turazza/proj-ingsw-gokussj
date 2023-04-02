package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.user.User;

public class RuleCommonXII implements RuleCommon{

    //Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile. Tiles can be of any type.
    @Override
    public boolean newRule(User user) {
        /*int depths;
        int pervDepths;
        for(pervDepths=user.getBookshelf().getNumRow()-1;pervDepths>=1;pervDepths--)
            if(user.getBookshelf().getBookshelf()[pervDepths][0]==null)
                break;
        for(int i=1; i<=user.getBookshelf().getNumColumn()-1; i++){
            for(depths=user.getBookshelf().getNumRow()-1;depths>=1;depths--)
                if(user.getBookshelf().getBookshelf()[depths][i]==null)
                    break;
            if(depths-pervDepths!=1)
                break;
            if(i==user.getBookshelf().getNumColumn()-1)
                return true;
        }
        for(pervDepths=user.getBookshelf().getNumRow()-1;pervDepths>=1;pervDepths--)
            if(user.getBookshelf().getBookshelf()[pervDepths][0]==null)
                break;
        for(int i=user.getBookshelf().getNumColumn()-2; i>=0; i--){
            for(depths=user.getBookshelf().getNumRow()-1;pervDepths>=1;pervDepths--)
                if(user.getBookshelf().getBookshelf()[depths][i]==null)
                    break;
            if(depths-pervDepths!=1)
                break;
            if(i==0)
                return true;
        }
        return false;*/
        int prevHeight=user.getBookshelf().getNumRow();
        int height;
        int startingPoint=0;
        for(int i=0; i<=user.getBookshelf().getNumRow()-1; i++){
            if(user.getBookshelf().getBookshelf()[i][startingPoint]!=null)
                break;
            prevHeight--;
        }
        for (int j=startingPoint+1; j<user.getBookshelf().getNumColumn(); j++ ){
            height=user.getBookshelf().getNumRow();
            for(int i=0; i<=user.getBookshelf().getNumRow()-1; i++){
                if(user.getBookshelf().getBookshelf()[i][j]!=null)
                    break;
                height--;
            }
            if(prevHeight-height!=1)
                break;
            prevHeight=height;
            if(j==user.getBookshelf().getNumColumn()-1)
                return true;
        }
        prevHeight=user.getBookshelf().getNumRow();
        startingPoint=user.getBookshelf().getNumColumn()-1;
        for(int i=0; i<=user.getBookshelf().getNumRow()-1; i++){
            if(user.getBookshelf().getBookshelf()[i][startingPoint]!=null)
                break;
            prevHeight--;
        }
        for (int j=startingPoint-1; j>0; j--){
            height=user.getBookshelf().getNumRow();
            for(int i=0; i<=user.getBookshelf().getNumRow()-1; i++){
                if(user.getBookshelf().getBookshelf()[i][j]!=null)
                    break;
                height--;
            }
            if(prevHeight-height!=1)
                break;
            prevHeight=height;
            if(j==user.getBookshelf().getNumColumn()-1)
                return true;
        }
        return false;

    }


}
