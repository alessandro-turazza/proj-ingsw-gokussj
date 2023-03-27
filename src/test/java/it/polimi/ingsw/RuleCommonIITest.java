package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleCommonIITest extends RuleCommonTest{


    private void initRule(){ ruleCommon=new RuleCommonII(); }


    @BeforeEach
    public void initalizer(){
        initUser();
        initRule();
    }

    @Test
    public void ruleCommonII1() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting00");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII2() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting01");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII3() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting40");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII4() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Starting41");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII5() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Parallel");
        assertTrue(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII6() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Cross");
        assertTrue(ruleCommon.newRule(user));
    }
    @Test
    public void ruleCommonII7() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Failed1");
        assertFalse(ruleCommon.newRule(user));
    }

    @Test
    public void ruleCommonII8() throws Exception {
        this.readFile("src/test/TestFiles/RuleCommonII_Failed2");
        assertFalse(ruleCommon.newRule(user));
    }

}
