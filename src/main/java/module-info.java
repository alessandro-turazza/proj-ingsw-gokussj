module it.polimi.ingsw.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens it.polimi.ingsw.client.view to javafx.fxml;
    exports it.polimi.ingsw.client.view;
    opens it.polimi.ingsw.client to javafx.fxml;
    exports it.polimi.ingsw.client;
    opens it.polimi.ingsw.server.state_game to com.google.gson;
    exports it.polimi.ingsw.server.state_game;
    opens it.polimi.ingsw.server.visitor to com.google.gson;
    exports it.polimi.ingsw.server.visitor;
    opens it.polimi.ingsw.server.model.user to com.google.gson;
    exports it.polimi.ingsw.server.model.user;
    opens it.polimi.ingsw.server.model.user.personal_goal to com.google.gson;
    exports it.polimi.ingsw.server.model.user.personal_goal;
    opens it.polimi.ingsw.server.model.user.bookshelf to com.google.gson;
    exports it.polimi.ingsw.server.model.user.bookshelf;
    opens it.polimi.ingsw.server.model.plank to com.google.gson;
    exports it.polimi.ingsw.server.model.plank;
    opens it.polimi.ingsw.server.model.object_card to com.google.gson;
    exports it.polimi.ingsw.server.model.object_card;
    opens it.polimi.ingsw.server.model.game_manager to com.google.gson;
    exports it.polimi.ingsw.server.model.game_manager;
    opens it.polimi.ingsw.server.model.common_goal to com.google.gson;
    exports it.polimi.ingsw.server.model.common_goal;
    opens it.polimi.ingsw.server.model.common_goal.rule_common to com.google.gson;
    exports it.polimi.ingsw.server.model.common_goal.rule_common;
    opens it.polimi.ingsw.server.message to com.google.gson;
    exports it.polimi.ingsw.server.message;
    opens it.polimi.ingsw.server.game_data to com.google.gson;
    exports it.polimi.ingsw.server.game_data;

}