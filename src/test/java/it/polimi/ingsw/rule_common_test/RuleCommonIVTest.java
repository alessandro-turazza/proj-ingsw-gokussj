package it.polimi.ingsw.rule_common_test;

import it.polimi.ingsw.rule_common.RuleCommonIV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonIVTest extends RuleCommonTest{

    private void initRule(){ ruleCommon=new RuleCommonIV(); }


    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonIV1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIV/RuleCommonIV_FullTrue");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonIV2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIV/RuleCommonIV_FullFalse");
        assertFalse(ruleCommon.newRule(user));
    }
    @Test
    public void ruleCommonIV3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIV/RuleCommonIV_NotFullTrue1");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonIV4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIV/RuleCommonIV_NotFullFalse1");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonIV5() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIV/RuleCommonIV_NotFullTrue2");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonIV6() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonTest/RuleCommonIV/RuleCommonIV_NotFullFalse2");
        assertFalse(ruleCommon.newRule(user));
    }

}
