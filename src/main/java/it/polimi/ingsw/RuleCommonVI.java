package it.polimi.ingsw;

public class RuleCommonVI implements RuleCommon{

    //Two columns each formed by 6 different types of tiles.
    @Override
    public boolean newRule(User user) {
        return RuleCommonSupportClass.columnsChecker(user,6,2,true);
    }
}
