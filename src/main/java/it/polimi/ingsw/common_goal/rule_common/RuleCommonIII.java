package it.polimi.ingsw.common_goal.rule_common;

import it.polimi.ingsw.user.User;

public class RuleCommonIII implements RuleCommon {

    //Four groups each containing at least 4 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.countNumberAdjacences(user, 4, 4);
    }
}
