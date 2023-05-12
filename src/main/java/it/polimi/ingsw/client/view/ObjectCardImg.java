package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.object_card.Color;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ObjectCardImg {
    private Image cardImg;
    private Color color;
    private int id;

    public ObjectCardImg(String path, Color color, int id) throws FileNotFoundException {
        FileInputStream reader = new FileInputStream(path);
        this.cardImg = new Image(reader);
        this.color = color;
        this.id = id;
    }
    public Image getCardImg() {
        return cardImg;
    }

    public void setCardImg(Image cardImg) {
        this.cardImg = cardImg;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
