package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.common_goal.rule_common.RuleCommonVI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonVITest extends RuleCommonTest{

    public void initRule(){ ruleCommon=new RuleCommonVI(); }

    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonVI1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVI/RuleCommonVI_FullTrue");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVI/RuleCommonVI_FullFalse");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVI/RuleCommonVI_NotFullTrue");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonVI4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonVI/RuleCommonVI_NotFullFalse");
        assertFalse(ruleCommon.newRule(user));
    }
}
