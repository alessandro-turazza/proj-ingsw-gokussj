package it.polimi.ingsw;

public class RuleCommonVIII implements RuleCommon{
    //Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
    @Override
    public boolean newRule(User user) {
        int i=user.getBookshelf().getNumRow()-1;
        int counter=0;
        while(i>=0){
            try{
                if(user.getBookshelf().checkColorsRow(i).size()==5){
                    counter++;

                }
                i--;
            }catch(Exception e){return counter>=2;}
        }
        return counter>=2;
    }
}
