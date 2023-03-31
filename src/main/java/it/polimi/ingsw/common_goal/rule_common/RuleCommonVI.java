package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.user.User;
import static it.polimi.ingsw.common_goal.rule_common.RuleCommonSupportClass.columnsChecker;

public class RuleCommonVI implements RuleCommon{

    //Two columns each formed by 6 different types of tiles.
    @Override
    public boolean newRule(User user) {
        return columnsChecker(user,6,2,true);
    }
}
