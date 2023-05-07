package it.polimi.ingsw.client.model;

public class TextCommonGoal {
    private Integer id;
    private String text;


    public TextCommonGoal(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
