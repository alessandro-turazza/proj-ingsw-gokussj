package it.polimi.ingsw.common_goal_clone_test;

import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.common_goal.TokenCard;
import it.polimi.ingsw.server.model.common_goal.rule_common.RuleCommonI;
import it.polimi.ingsw.server.state_game.CommonGoalClone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CommonGoalCloneTest {
    public static CommonGoalClone commonGoalClone;
    public static CommonGoal commonGoal;
    @BeforeAll
    public static void init(){
        ArrayList<TokenCard> tokenCards=new ArrayList<>();
        tokenCards.add(new TokenCard(8,1));
        tokenCards.add(new TokenCard(6,1));
        tokenCards.add(new TokenCard(4,1));
        tokenCards.add(new TokenCard(2,1));
        commonGoal=new CommonGoal(1,new RuleCommonI(),tokenCards);
        commonGoalClone=new CommonGoalClone(commonGoal);
    }
    @BeforeEach
    public void reinit(){
        init();
    }

    @Test
    public void testClone(){

        assertEquals(commonGoal.getId(),commonGoalClone.getId());
        assertEquals(commonGoal.getRule().getIdRule(),commonGoalClone.getIdRule());

        assertEquals(commonGoal.getTokenCards().size(),commonGoalClone.getTokens().size());

        for(int i=0;i<commonGoal.getTokenCards().size();i++){
            assertEquals(commonGoal.getTokenCards().get(i).getPoints(),commonGoalClone.getTokens().get(i).getPoints());
            assertEquals(commonGoal.getTokenCards().get(i).getSeries(),commonGoalClone.getTokens().get(i).getSeries());
        }

    }
    @Test
    public void test_getLastTokenCard1(){
        assertEquals(commonGoalClone.getLastTokenCard().getPoints(),8);
        assertEquals(commonGoalClone.getLastTokenCard().getSeries(),1);
    }
    @Test
    public void test_getLastTokenCard2(){
        ArrayList<TokenCard> tokenCards=new ArrayList<>();
        commonGoal=new CommonGoal(1,new RuleCommonI(),tokenCards);
        commonGoalClone=new CommonGoalClone(commonGoal);
        assertNull(commonGoalClone.getLastTokenCard());
    }
}
