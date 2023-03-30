package it.polimi.ingsw;

public class RuleCommonX implements RuleCommon {
    @Override
    public boolean newRule(User user) {
        user.getBookshelf().deleteMark();
        for (int i = 0; i <= user.getBookshelf().getNumColumn() - 3; i++)
            for (int j = 0; j <= user.getBookshelf().getNumRow() - 3; j++) {
                if (IsCross(user.getBookshelf().getBookshelf(), j, i))
                    return true;
            }
        return false;
    }

    private boolean IsCross(CellShelf[][] bookshelf, int row, int column) {
        bookshelf[row][column].setMarked(true);
        if (bookshelf[row][column].getObjectCard() == null)
            return false;
        Color color = bookshelf[row][column].getObjectCard().getColor();
        if (bookshelf[row+1][column + 1].getObjectCard() == null || bookshelf[row+1][column + 1].getObjectCard().getColor() != color || bookshelf[row][column + 1].isMarked())
            return false;
        if (bookshelf[row + 2][column + 2].getObjectCard() == null || bookshelf[row + 2][column + 2].getObjectCard().getColor() != color || bookshelf[row + 2][column + 2].isMarked())
            return false;
        if (bookshelf[row + 2][column].getObjectCard() == null || bookshelf[row + 2][column].getObjectCard().getColor() != color || bookshelf[row + 2][column].isMarked())
            return false;
        if (bookshelf[row][column + 2].getObjectCard() == null || bookshelf[row][column + 2].getObjectCard().getColor() != color || bookshelf[row][column + 2].isMarked())
            return false;
        bookshelf[row+1][column + 1].setMarked(true);
        bookshelf[row + 2][column + 2].setMarked(true);
        bookshelf[row + 2][column].setMarked(true);
        bookshelf[row ][column + 2].setMarked(true);
        return true;
    }
}
