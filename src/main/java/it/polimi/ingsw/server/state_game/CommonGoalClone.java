package it.polimi.ingsw.server.state_game;

import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.common_goal.TokenCard;

import java.util.ArrayList;

/**
 * This class rpresents the clone of the Common Goals for the state game
 */
public class CommonGoalClone {

    protected int id;
    protected int idRule;
    protected ArrayList<TokenCard> tokens;
    public CommonGoalClone(CommonGoal commonGoal){
        this.id = commonGoal.getId();
        this.idRule = commonGoal.getRule().getIdRule();
        this.tokens = new ArrayList<>();

        for(TokenCard t: commonGoal.getTokenCards()){
            tokens.add(new TokenCard(t.getPoints(), t.getSeries()));
        }
    }

    public int getId() {
        return id;
    }

    public int getIdRule() {
        return idRule;
    }

    public ArrayList<TokenCard> getTokens() {
        return tokens;
    }

    public TokenCard getLastTokenCard(){
        if(tokens.size()==0)return null;
        return tokens.get(0);
    }
}
