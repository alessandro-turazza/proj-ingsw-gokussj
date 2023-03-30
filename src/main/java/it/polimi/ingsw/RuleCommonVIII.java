package it.polimi.ingsw;

public class RuleCommonVIII implements RuleCommon{
    //Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.linesChecker(user,5,2,true);
    }
}
