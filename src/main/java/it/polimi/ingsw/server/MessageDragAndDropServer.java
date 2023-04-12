package it.polimi.ingsw.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MessageDragAndDropServer implements MessageServer{

    private ArrayList<Integer> x_coordinate;

    private ArrayList<Integer> y_coordinate;
    private int column;

    public MessageDragAndDropServer(ServerThread st, JSONObject obj) {
        Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
        Gson gobj= new Gson();
        x_coordinate=gobj.fromJson(obj.get("x_coordinate").toString(),listType);
        y_coordinate=gobj.fromJson(obj.get("y_coordinate").toString(),listType);
        column=(Integer) obj.get("column");
    }

    @Override
    public void accept(VisitorServer v) {
        v.visitMessageDragAndDrop(this);

    }

    public void setX_coordinate(ArrayList<Integer> x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public void setY_coordinate(ArrayList<Integer> y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
