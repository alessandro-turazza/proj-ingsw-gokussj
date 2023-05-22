package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;

public class RuleCommonI implements RuleCommon{
    private int idRule = 1;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Six groups each containing at least 2 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group.
    @Override
    public boolean newRule(User user) {

        return RuleCommonSupportClass.countNumberAdjacences(user, 6, 2);

    }
}
