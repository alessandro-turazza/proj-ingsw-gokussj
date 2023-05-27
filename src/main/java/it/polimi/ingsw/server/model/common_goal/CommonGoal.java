package it.polimi.ingsw.server.model.common_goal;

import it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommon;
import it.polimi.ingsw.server.model.user.User;

import java.util.ArrayList;

/**
 * This class that represents the common goals
 */
public class CommonGoal {
    private int id;
    private RuleCommon rule;
    private ArrayList<TokenCard> tokenCards;

    public CommonGoal(int id, RuleCommon rule) {
        this.id = id;
        this.rule = rule;
        this.tokenCards = new ArrayList<>();
    }

    public CommonGoal(int id, RuleCommon rule, ArrayList<TokenCard> tokenCards) {
        this.id = id;
        this.rule = rule;
        this.tokenCards = tokenCards;
    }
    public void setTokenCardsInteger(ArrayList<Integer> tokenCards, int series){
        for(Object o: tokenCards){
            this.tokenCards.add(new TokenCard(Integer.parseInt(o.toString()), series));
        }
    }
    public ArrayList<TokenCard> getTokenCards(){
        return tokenCards;
    }

    public int getPoint(){
        if(tokenCards.size()==0)return 0;
        return tokenCards.remove(0).getPoints();
    }

    /**
     * This is a pattern strategy method
     */
    public boolean checkRule(User user){
        return rule.newRule(user);
    }

    public int getId() {
        return id;
    }

    public RuleCommon getRule() {
        return rule;
    }
}
