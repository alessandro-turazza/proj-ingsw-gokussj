package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonVTest extends RuleCommonTest{

    public void initRule(){ ruleCommon=new RuleCommonV(); }


    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonV1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_True");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonV2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_False1");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonV3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_False2");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonV4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonV_False3");
        assertFalse(ruleCommon.newRule(user));
    }

}
