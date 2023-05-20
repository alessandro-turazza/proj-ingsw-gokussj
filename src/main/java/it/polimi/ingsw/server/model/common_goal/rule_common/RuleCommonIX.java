package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;

public class RuleCommonIX implements RuleCommon{
    private int idRule = 9;

    @Override
    public int getIdRule() {
        return idRule;
    }

    //Three columns each formed by 6 tiles of maximum three different types. One column can show the same or a different combination of another column.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.columnsChecker(user,3,3,false);
    }
}
