package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.user.User;

public class RuleCommonXII implements RuleCommon{

    //Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile. Tiles can be of any type.
    @Override
    public boolean newRule(User user) {
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
            if(height==0)
                return false;
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
            if(height==1)
                return false;
            if(prevHeight-height!=1)
                break;
            prevHeight=height;
            if(j==1)
                return true;
        }
        return false;
    }


}
