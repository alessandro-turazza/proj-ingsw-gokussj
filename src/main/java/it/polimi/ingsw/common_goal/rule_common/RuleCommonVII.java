package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.user.User;
import static it.polimi.ingsw.common_goal.rule_common.RuleCommonSupportClass.IsSquare;

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


}
