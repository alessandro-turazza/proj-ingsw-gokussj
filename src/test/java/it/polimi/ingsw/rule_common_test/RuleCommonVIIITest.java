package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.common_goal.rule_common.RuleCommonVIII;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonVIIITest extends RuleCommonTest{

    public void initRule(){ ruleCommon=new RuleCommonVIII(); }

    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }
    @Test
    public void ruleCommonVIII1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVIII/RuleCommonVIII_FullTrue");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVIII2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVIII/RuleCommonVIII_FullFalse");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVIII3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVIII/RuleCommonVIII_NotFullTrue");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVIII4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVIII/RuleCommonVIII_NotFullFalse");
        assertFalse(ruleCommon.newRule(user));
    }
}
