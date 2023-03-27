package it.polimi.ingsw;

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

    private boolean checkDiagonal(Bookshelf bookshelf,int startingpointrow, int startingpointcolumn,int numofelem, boolean direction) throws Exception{     //in direction true for from left to right, flase for form right to left
        if(bookshelf.getBookshelf()[startingpointrow][startingpointcolumn]==null)
            return false;
        Color checkcolor;
        if(direction){
            if(startingpointrow>=bookshelf.NUM_ROW || startingpointrow<0 ||startingpointcolumn>=bookshelf.NUM_COLUMN ||startingpointcolumn<0 || !(startingpointrow+numofelem-1<bookshelf.NUM_ROW) || !(startingpointcolumn+numofelem-1<bookshelf.NUM_COLUMN))
                throw new Exception();
            checkcolor=bookshelf.getBookshelf()[startingpointrow][startingpointcolumn].getObjectCard().getColor();
            for(int i=1; i<=numofelem-1;i++)
                if(bookshelf.getBookshelf()[startingpointrow+i][startingpointcolumn+i].getObjectCard().getColor()!=checkcolor)
                    return false;
            return true;}
        if(startingpointrow>=bookshelf.NUM_ROW || startingpointrow<0 ||startingpointcolumn>=bookshelf.NUM_COLUMN ||startingpointcolumn<0 || !(startingpointcolumn-numofelem+1>=0) || !(startingpointrow+numofelem-1<bookshelf.NUM_ROW))
            throw new Exception();
        checkcolor=bookshelf.getBookshelf()[startingpointrow][startingpointcolumn].getObjectCard().getColor();
        for(int i=1; i<=numofelem-1;i++)
            if(bookshelf.getBookshelf()[startingpointrow+i][startingpointcolumn-i].getObjectCard().getColor()!=checkcolor)
                return false;
        return true;


    }
}
