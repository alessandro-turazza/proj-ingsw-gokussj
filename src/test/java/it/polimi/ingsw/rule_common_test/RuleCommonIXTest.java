package it.polimi.ingsw.rule_common_test;


import it.polimi.ingsw.common_goal.rule_common.RuleCommonIX;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonIXTest extends RuleCommonTest{

    public void initRule(){ ruleCommon=new RuleCommonIX(); }

    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonIX1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIX/RuleCommonIX_FullTrue");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIX/RuleCommonIX_FullFalse");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIX/RuleCommonIX_NotFullTrue");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIX/RuleCommonIX_NotFullFalse");
        assertFalse(ruleCommon.newRule(user));
    }
}

