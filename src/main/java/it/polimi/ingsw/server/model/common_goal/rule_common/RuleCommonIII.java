package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;

public class RuleCommonIII implements RuleCommon {
    private int idRule = 3;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Four groups each containing at least 4 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.countNumberAdjacences(user, 4, 4);
    }
}
