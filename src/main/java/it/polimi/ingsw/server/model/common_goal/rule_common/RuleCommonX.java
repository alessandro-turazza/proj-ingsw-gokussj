package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;
import static it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommonSupportClass.IsCross;

public class RuleCommonX implements RuleCommon {
    private int idRule = 10;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Five tiels of the same color shaped as an X

    @Override
    public boolean newRule(User user) {
        user.getBookshelf().deleteMark();
        for (int i = 0; i <= user.getBookshelf().getNumColumn() - 3; i++)
            for (int j = 0; j <= user.getBookshelf().getNumRow() - 3; j++) {
                if (IsCross(user.getBookshelf().getBookshelf(), j, i)){
                    user.getBookshelf().deleteMark();
                    return true;
                }

            }
        user.getBookshelf().deleteMark();
        return false;
    }


}
