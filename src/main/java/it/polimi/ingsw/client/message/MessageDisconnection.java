package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.client.visitor.VisitorClient;
import org.json.simple.JSONObject;

public class MessageDisconnection implements MessageClient{

    private JSONObject obj;

    private View view;

    public MessageDisconnection(JSONObject obj, View view) {
        this.obj = obj;
        this.view = view;
    }

    @Override
    public void accept(VisitorClient visitor) throws Exception {
        visitor.visit(this);
    }

    public JSONObject getObj() {
        return obj;
    }

    public View getView() {
        return view;
    }
}
