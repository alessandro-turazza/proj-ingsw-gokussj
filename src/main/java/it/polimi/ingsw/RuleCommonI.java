package it.polimi.ingsw;

public class RuleCommonI implements RuleCommon{

    //Six groups each containing at least 2 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.countNumberAdjacences(user, 6, 2);
    }
}
