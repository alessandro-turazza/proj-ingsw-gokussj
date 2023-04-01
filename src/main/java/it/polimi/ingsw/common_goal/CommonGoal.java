package it.polimi.ingsw.common_goal;

import it.polimi.ingsw.common_goal.rule_common.RuleCommon;
import it.polimi.ingsw.user.User;

import java.util.ArrayList;

public class CommonGoal {
    private int id;
    private RuleCommon rule;
    private ArrayList<TokenCard> tokenCards;

    public CommonGoal(int id, RuleCommon rule) {
        this.id = id;
        this.rule = rule;
    }
    public void setTokenCards(ArrayList<TokenCard> tokenCards) {
        this.tokenCards = tokenCards;
    }

    public ArrayList<TokenCard> getTokenCards(){
        return tokenCards;
    }

    public boolean checkRule(User user){
        return rule.newRule(user);
    }
}
