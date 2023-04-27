package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;

public class RuleCommonIV implements RuleCommon{
    private int idRule = 4;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.linesChecker(user,3,4,false);
    }
}

