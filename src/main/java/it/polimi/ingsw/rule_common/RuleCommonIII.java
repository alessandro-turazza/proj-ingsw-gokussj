package it.polimi.ingsw.rule_common;

import it.polimi.ingsw.user.User;
import static it.polimi.ingsw.rule_common.RuleCommonSupportClass.countNumberAdjacences;

public class RuleCommonIII implements RuleCommon {

    //Four groups each containing at least 4 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group.
    @Override
    public boolean newRule(User user) {
        return countNumberAdjacences(user, 4, 4);
    }
}
