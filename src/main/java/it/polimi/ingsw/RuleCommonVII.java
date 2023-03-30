package it.polimi.ingsw;

public class RuleCommonVII implements RuleCommon {
    @Override
    public boolean newRule(User user) {
        user.getBookshelf().deleteMark();
        int counter = 0;
        for (int i = 0; i <= user.getBookshelf().getNumColumn() - 2; i++)
            for (int j = 0; j <= user.getBookshelf().getNumRow() - 2; j++) {
                if (IsSquare(user.getBookshelf().getBookshelf(), j, i))
                    counter++;
                if (counter == 2)
                    return true;
            }
        return false;
    }

    private boolean IsSquare(CellShelf[][] bookshelf, int row, int column) {
        bookshelf[row][column].setMarked(true);
        if (bookshelf[row][column].getObjectCard() == null)
            return false;
        Color color = bookshelf[row][column].getObjectCard().getColor();
        if (bookshelf[row][column + 1].getObjectCard() == null || bookshelf[row][column + 1].getObjectCard().getColor() != color || bookshelf[row][column + 1].isMarked())
            return false;
        if (bookshelf[row + 1][column + 1].getObjectCard() == null || bookshelf[row + 1][column + 1].getObjectCard().getColor() != color || bookshelf[row + 1][column + 1].isMarked())
            return false;
        if (bookshelf[row + 1][column].getObjectCard() == null || bookshelf[row + 1][column].getObjectCard().getColor() != color || bookshelf[row + 1][column].isMarked())
            return false;
        bookshelf[row][column + 1].setMarked(true);
        bookshelf[row + 1][column + 1].setMarked(true);
        bookshelf[row + 1][column].setMarked(true);
        return true;
    }
}
