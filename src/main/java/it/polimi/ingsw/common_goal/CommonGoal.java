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
        this.tokenCards = new ArrayList<>();
    }
    public void setTokenCards(ArrayList<TokenCard> tokenCards) {
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



    public boolean checkRule(User user){
        return rule.newRule(user);
    }
}
