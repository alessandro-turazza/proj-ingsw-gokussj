package it.polimi.ingsw.server;

import org.json.simple.JSONObject;

public class MessageDragAndDropServer implements MessageServer{

    private int x_coordinate;

    private int Y_coordinate;
    private int column;
    @Override
    public void accept(VisitorServer v, JSONObject obj) {
        v.visitMessageDragAndDrop(this,obj);
        //Chiama UpdateGame passando la drag e la drop generate dai suoi attribbuti.
        //gestisce anche il caso di eccezioni(nel qual caso manda una KO al Client)
        //gestisce ovviamente anche il caso di return=null
        //prepara il ServerThread per inviare i dati aggiornati
    }

    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public void setY_coordinate(int y_coordinate) {
        Y_coordinate = y_coordinate;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
