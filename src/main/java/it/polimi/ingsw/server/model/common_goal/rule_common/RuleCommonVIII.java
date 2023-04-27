package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;

public class RuleCommonVIII implements RuleCommon{
    private int idRule = 8;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.linesChecker(user,5,2,true);
    }
}
