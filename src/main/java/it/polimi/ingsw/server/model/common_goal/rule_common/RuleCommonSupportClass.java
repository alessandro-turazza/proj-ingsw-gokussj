package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.object_card.Color;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;
import it.polimi.ingsw.server.model.user.bookshelf.CellShelf;

import java.util.ArrayList;
import java.util.Objects;

public class RuleCommonSupportClass {

    //Support class for RuleCommon

    //return the arraylist of different colors contained in a full row. If the row is not full, it returns null
    static ArrayList<Color> checkColorsRow(User user , int row){
        CellShelf[][] bookshelf = user.getBookshelf().getBookshelf();
        ArrayList<Color> checkcolors = new ArrayList<>();
        for(int i=0; i<=user.getBookshelf().getNumColumn()-1; i++ ){
            if (bookshelf[row][i]==null)
                return null;
            if(!checkcolors.contains(bookshelf[row][i].getObjectCard().getColor()))
                checkcolors.add(bookshelf[row][i].getObjectCard().getColor());
        }
        return checkcolors;
    }

    //return the arraylist of different colors contained in a full column. If the column is not full, it returns null
    static ArrayList<Color> checkColorsColumn(User user,int column){
        CellShelf[][] bookshelf = user.getBookshelf().getBookshelf();
        ArrayList<Color> checkcolors = new ArrayList<>();
        for(int i=0; i<=user.getBookshelf().getNumRow()-1; i++ ){
            if (bookshelf[i][column]==null)
                return null;
            if( !checkcolors.contains(bookshelf[i][column].getObjectCard().getColor()))
                checkcolors.add(bookshelf[i][column].getObjectCard().getColor());
        }
        return checkcolors;
    }

    /*count how many lines in the user's bookshelf has more or less (depending on the value of greaterOrLesser: true for more, false for less) different color form the integer colors,
    then return true if the number of this lines are grater or equals to the integer lines*/
    static boolean linesChecker(User user, int colors, int lines, boolean greaterOrLesser){
        int i=user.getBookshelf().getNumRow()-1;
        int counter=0;
        while(i>=0){
            if(checkColorsRow(user, i)==null)
                return counter>=lines;
            if(greaterOrLesser){
                if(Objects.requireNonNull(checkColorsRow(user, i)).size()>=colors)
                    counter++;
            } else if(Objects.requireNonNull(checkColorsRow(user, i)).size()<=colors) {
                counter++;
            }
            i--;
        }
        return counter>=lines;
    }

    /*count how many columns in the user's bookshelf has more or less (depending on the value of greaterOrLesser: true for more, false for less) different color form the integer colors,
    then return true if the number of this columns are grater or equals to the integer columns*/
   static boolean columnsChecker(User user, int colors, int columns, boolean greaterOrLesser){
       int i=user.getBookshelf().getNumColumn()-1;
       int counter=0;
       while(i>=0){
           if (checkColorsColumn(user, i)!=null) {
               if (greaterOrLesser) {
                   if (Objects.requireNonNull(checkColorsColumn(user, i)).size() >= colors)
                       counter++;
               } else if (Objects.requireNonNull(checkColorsColumn(user, i)).size() <= colors)
                   counter++;
           }
           i--;
       }
       return counter>=columns;
   }

   public static boolean countNumberAdjacences(User user, int groups, int tiels){
       ArrayList<Integer> adj = user.getBookshelf().numberAdjacenses();
       adj.removeIf(i -> i < tiels);

       return adj.size()>=groups;
   }

    static boolean checkDiagonal(Bookshelf bookshelf, int startingpointrow, int startingpointcolumn, int numofelem, boolean direction) throws Exception{     // direction equals true for from left to right, equals false for form right to left
        if(bookshelf.getBookshelf()[startingpointrow][startingpointcolumn]==null)
            return false;
        Color checkcolor;
        if(direction){
            if(startingpointrow>=bookshelf.getNumRow() || startingpointrow<0 ||startingpointcolumn>=bookshelf.getNumColumn()||startingpointcolumn<0 || !(startingpointrow+numofelem-1<bookshelf.getNumRow()) || !(startingpointcolumn+numofelem-1<bookshelf.getNumColumn()))
                throw new Exception();
            checkcolor=bookshelf.getBookshelf()[startingpointrow][startingpointcolumn].getObjectCard().getColor();
            for(int i=1; i<=numofelem-1;i++)
                if(bookshelf.getBookshelf()[startingpointrow+i][startingpointcolumn+i].getObjectCard().getColor()!=checkcolor)
                    return false;
            return true;}
        if(startingpointrow>=bookshelf.getNumRow() || startingpointrow<0 ||startingpointcolumn>=bookshelf.getNumColumn()||startingpointcolumn<0 || !(startingpointcolumn-numofelem+1>=0) || !(startingpointrow+numofelem-1<bookshelf.getNumRow()))
            throw new Exception();
        checkcolor=bookshelf.getBookshelf()[startingpointrow][startingpointcolumn].getObjectCard().getColor();
        for(int i=1; i<=numofelem-1;i++)
            if(bookshelf.getBookshelf()[startingpointrow+i][startingpointcolumn-i].getObjectCard().getColor()!=checkcolor)
                return false;
        return true;
    }

    static boolean IsSquare(CellShelf[][] bookshelf, int row, int column) {

        if(bookshelf[row][column]==null || bookshelf[row][column].isMarked()||bookshelf[row][column].getObjectCard() == null )
            return false;
        bookshelf[row][column].setMarked(true);
        Color color = bookshelf[row][column].getObjectCard().getColor();
        if (bookshelf[row][column + 1] == null || bookshelf[row][column + 1].getObjectCard() == null || bookshelf[row][column + 1].getObjectCard().getColor() != color || bookshelf[row][column + 1].isMarked())
            return false;
        if (bookshelf[row + 1][column + 1] == null || bookshelf[row + 1][column + 1].getObjectCard() == null || bookshelf[row + 1][column + 1].getObjectCard().getColor() != color || bookshelf[row + 1][column + 1].isMarked())
            return false;
        if (bookshelf[row + 1][column] == null || bookshelf[row + 1][column].getObjectCard() == null || bookshelf[row + 1][column].getObjectCard().getColor() != color || bookshelf[row + 1][column].isMarked())
            return false;
        bookshelf[row][column + 1].setMarked(true);
        bookshelf[row + 1][column + 1].setMarked(true);
        bookshelf[row + 1][column].setMarked(true);
        return true;
    }

    static boolean IsCross(CellShelf[][] bookshelf, int row, int column) {
        if(bookshelf[row][column]==null || bookshelf[row][column].getObjectCard() == null ) return false;
        else if(bookshelf[row][column].isMarked()) return false;
        bookshelf[row][column].setMarked(true);
        Color color = bookshelf[row][column].getObjectCard().getColor();

        if (bookshelf[row+1][column + 1]== null || bookshelf[row+1][column + 1].getObjectCard() == null || bookshelf[row+1][column + 1].getObjectCard().getColor() != color)
            return false;
        else if(bookshelf[row+1][column + 1].isMarked())return false;

        if (bookshelf[row + 2][column + 2] == null || bookshelf[row + 2][column + 2].getObjectCard() == null || bookshelf[row + 2][column + 2].getObjectCard().getColor() != color)
            return false;
        else if(bookshelf[row+2][column + 2].isMarked())return false;

        if (bookshelf[row + 2][column] == null || bookshelf[row + 2][column].getObjectCard() == null || bookshelf[row + 2][column].getObjectCard().getColor() != color)
            return false;
        else if(bookshelf[row+2][column].isMarked())return false;

        if (bookshelf[row][column + 2] == null || bookshelf[row][column + 2].getObjectCard() == null || bookshelf[row][column + 2].getObjectCard().getColor() != color)
            return false;
        else if(bookshelf[row][column + 2].isMarked())return false;

        bookshelf[row+1][column + 1].setMarked(true);
        bookshelf[row + 2][column + 2].setMarked(true);
        bookshelf[row + 2][column].setMarked(true);
        bookshelf[row ][column + 2].setMarked(true);
        return true;
    }
}
