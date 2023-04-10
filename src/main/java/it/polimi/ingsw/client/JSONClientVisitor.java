package it.polimi.ingsw.client;

import org.json.simple.JSONObject;

public class JSONClientVisitor implements VisitorClient {
    @Override
    public void visit(MessageEndGameClient element, Object obj) {

    }

    @Override
    public void visit(MessageGameStateClient element, Object obj) {

    }

    @Override
    public void visit(MessageNewTurnClient element, Object obj) {
        JSONObject jobj= (JSONObject) obj;
        element.setPlank((String) jobj.get("plank"));
        element.setUsername((String) jobj.get("active_user"));


    }

    @Override
    public void visit(MessageOKClient element, Object obj) {

    }

    @Override
    public void visit(MessageKOClient element, Object obj) {

    }
}
