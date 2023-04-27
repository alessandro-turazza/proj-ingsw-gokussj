package it.polimi.ingsw.server.model.common_goal.rule_common;

import it.polimi.ingsw.server.model.user.User;

public interface RuleCommon {
    int getIdRule();
    // *goal description form rulebook
    boolean newRule(User user);
}
