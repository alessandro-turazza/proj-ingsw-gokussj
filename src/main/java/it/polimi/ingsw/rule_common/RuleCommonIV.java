package it.polimi.ingsw.rule_common;

import it.polimi.ingsw.user.User;

public class RuleCommonIV implements RuleCommon{

    //Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.linesChecker(user,3,4,false);
    }
}

