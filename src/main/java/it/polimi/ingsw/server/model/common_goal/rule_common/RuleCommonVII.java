package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;
import static it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommonSupportClass.IsSquare;

public class RuleCommonVII implements RuleCommon {
    private int idRule = 7;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.
    @Override
    public boolean newRule(User user) {
        user.getBookshelf().deleteMark();
        int counter = 0;
        for (int i = 0; i <= user.getBookshelf().getNumColumn() - 2; i++)
            for (int j = 0; j <= user.getBookshelf().getNumRow() - 2; j++) {
                if (IsSquare(user.getBookshelf().getBookshelf(), j, i))
                    counter++;
                if (counter == 2){
                    user.getBookshelf().deleteMark();
                    return true;
                }

            }
        user.getBookshelf().deleteMark();
        return false;
    }


}
