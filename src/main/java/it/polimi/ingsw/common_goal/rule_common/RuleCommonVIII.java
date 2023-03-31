package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.user.User;
import static it.polimi.ingsw.common_goal.rule_common.RuleCommonSupportClass.linesChecker;

public class RuleCommonVIII implements RuleCommon{
    //Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
    @Override
    public boolean newRule(User user) {
        return linesChecker(user,5,2,true);
    }
}
