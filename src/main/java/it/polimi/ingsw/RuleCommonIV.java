package it.polimi.ingsw;

public class RuleCommonIV implements RuleCommon{

    //Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line.
    @Override
    public boolean newRule(User user) {
        int i=user.getBookshelf().getNumRow()-1;
        int counter=0;
        while(i>=0){
            try{
            if(user.getBookshelf().checkColorsRow(i).size()<=3){
                counter++;

            }
            i--;
            }catch(Exception e){return counter>=4;}
        }
        return counter>=4;
    }
}

