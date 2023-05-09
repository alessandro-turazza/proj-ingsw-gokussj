module it.polimi.ingsw.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.google.gson;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens it.polimi.ingsw.client.view to javafx.fxml;
    exports it.polimi.ingsw.client.view;
}