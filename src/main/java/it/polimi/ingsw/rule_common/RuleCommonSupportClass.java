package it.polimi.ingsw.rule_common;

import it.polimi.ingsw.user.User;

import java.util.ArrayList;

public class RuleCommonSupportClass {

    //Support class for RuleCommon

    public static boolean linesChecker(User user, int colors, int lines, boolean greaterOrLesser){
        int i=user.getBookshelf().getNumRow()-1;
        int counter=0;
        while(i>=0){
            try{
                if(greaterOrLesser){
                    if(user.getBookshelf().checkColorsRow(i).size()>=colors)
                        counter++;
                    }
                else
                    if(user.getBookshelf().checkColorsRow(i).size()<=colors)
                        counter++;
                    i--;
                }
            catch(Exception e){return counter>=lines;}
        }
        return counter>=lines;
    }

   public static boolean columnsChecker(User user, int colors, int columns, boolean greaterOrLesser){
       int i=user.getBookshelf().getNumColumn()-1;
       int counter=0;
       while(i>=0){
           try{
               if(greaterOrLesser){
                   if(user.getBookshelf().checkColorsColumn(i).size()>=colors)
                       counter++;
               }
               else
               if(user.getBookshelf().checkColorsColumn(i).size()<=colors)
                   counter++;
           }
           catch(Exception e){}
           i--;}
       return counter>=columns;
   }

   public static boolean countNumberAdjacences(User user, int groups, int tiels){
       ArrayList<Integer> adj = user.getBookshelf().numberAdjacenses();
       adj.removeIf(i -> i < tiels);
       return adj.size()>=groups;
   }
}
