package it.polimi.ingsw.plank_test;

import it.polimi.ingsw.game_data.DataObjectCard;
import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.object_card.Color;
import it.polimi.ingsw.object_card.ObjectCard;
import it.polimi.ingsw.plank.CardBag;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static it.polimi.ingsw.object_card.ObjectCard.convertToColor;
import static org.junit.jupiter.api.Assertions.*;

public class CardBagTest {
    private CardBag cardBag;
    private ArrayList<DataObjectCard> listDataObjectCard;

    @BeforeEach
    public void initCardBagTest(){
        cardBag=new CardBag();
        listDataObjectCard=new ArrayList<>();
    }

    @Test
    public void CardBag_Test_hasNextTrue(){
        DataObjectCard dataObjectCard=new DataObjectCard(1,Color.WHITE,1);
        listDataObjectCard.add(dataObjectCard);
        cardBag.initializeCardBag(listDataObjectCard);
        //insert 1 element into cardBag
        assertTrue(cardBag.hasNext());
    }
    @Test
    public void CardBag_Test_hasNextFalse(){
        assertFalse(cardBag.hasNext());
    }
    @Test
    public void CardBag_Test_nextFalse() {
        assertNull(cardBag.next());
    }
    @Test
    public void CardBag_Test_nextTrue(){
        ObjectCard objectCard=new ObjectCard(1,Color.WHITE);
        DataObjectCard dataObjectCard=new DataObjectCard(1,Color.WHITE,1);
        listDataObjectCard.add(dataObjectCard);
        cardBag.initializeCardBag(listDataObjectCard);
        ObjectCard objectCardBag=cardBag.next();
        //insert 1 element into cardBag
        assertEquals(objectCardBag.getColor(),objectCard.getColor());
        assertEquals(objectCardBag.getId(),objectCard.getId());
    }

    @Test
    public void CardBag_Test_initializeCardBag() throws IOException, ParseException {
        ObjectCard objectCard;
        GameData.loadObjectCards("src/data/Object_Cards_Data.json");
        cardBag.initializeCardBag(GameData.getDataObjectCards());
        int num_ColorYELLOW_ID1=0;
        int num_ColorYELLOW_ID2=0;
        int num_ColorYELLOW_ID3=0;
        int num_ColorBLUE_ID1=0;
        int num_ColorBLUE_ID2=0;
        int num_ColorBLUE_ID3=0;
        int num_ColorPINK_ID1=0;
        int num_ColorPINK_ID2=0;
        int num_ColorPINK_ID3=0;
        int num_ColorLIGHT_BLUE_ID1=0;
        int num_ColorLIGHT_BLUE_ID2=0;
        int num_ColorLIGHT_BLUE_ID3=0;
        int num_ColorGREEN_ID1=0;
        int num_ColorGREEN_ID2=0;
        int num_ColorGREEN_ID3=0;
        int num_ColorWHITE_ID1=0;
        int num_ColorWHITE_ID2=0;
        int num_ColorWHITE_ID3=0;
        while(cardBag.hasNext()){
            objectCard=cardBag.next();
            if(objectCard.getColor()==Color.YELLOW&& objectCard.getId()==1)num_ColorYELLOW_ID1++;
            if(objectCard.getColor()==Color.YELLOW&& objectCard.getId()==2)num_ColorYELLOW_ID2++;
            if(objectCard.getColor()==Color.YELLOW&& objectCard.getId()==3)num_ColorYELLOW_ID3++;
            if(objectCard.getColor()==Color.BLUE&& objectCard.getId()==1)num_ColorBLUE_ID1++;
            if(objectCard.getColor()==Color.BLUE&& objectCard.getId()==2)num_ColorBLUE_ID2++;
            if(objectCard.getColor()==Color.BLUE&& objectCard.getId()==3)num_ColorBLUE_ID3++;
            if(objectCard.getColor()==Color.PINK&& objectCard.getId()==1)num_ColorPINK_ID1++;
            if(objectCard.getColor()==Color.PINK&& objectCard.getId()==2)num_ColorPINK_ID2++;
            if(objectCard.getColor()==Color.PINK&& objectCard.getId()==3)num_ColorPINK_ID3++;
            if(objectCard.getColor()==Color.LIGHT_BLUE&& objectCard.getId()==1)num_ColorLIGHT_BLUE_ID1++;
            if(objectCard.getColor()==Color.LIGHT_BLUE&& objectCard.getId()==2)num_ColorLIGHT_BLUE_ID2++;
            if(objectCard.getColor()==Color.LIGHT_BLUE&& objectCard.getId()==3)num_ColorLIGHT_BLUE_ID3++;
            if(objectCard.getColor()==Color.GREEN&& objectCard.getId()==1)num_ColorGREEN_ID1++;
            if(objectCard.getColor()==Color.GREEN&& objectCard.getId()==2)num_ColorGREEN_ID2++;
            if(objectCard.getColor()==Color.GREEN&& objectCard.getId()==3)num_ColorGREEN_ID3++;
            if(objectCard.getColor()==Color.WHITE&& objectCard.getId()==1)num_ColorWHITE_ID1++;
            if(objectCard.getColor()==Color.WHITE&& objectCard.getId()==2)num_ColorWHITE_ID2++;
            if(objectCard.getColor()==Color.WHITE&& objectCard.getId()==3)num_ColorWHITE_ID3++;
        }
        assertEquals(num_ColorYELLOW_ID1,7);
        assertEquals(num_ColorYELLOW_ID2,7);
        assertEquals(num_ColorYELLOW_ID3,8);
        assertEquals(num_ColorBLUE_ID1,7);
        assertEquals(num_ColorBLUE_ID2,7);
        assertEquals(num_ColorBLUE_ID3,8);
        assertEquals(num_ColorPINK_ID1,7);
        assertEquals(num_ColorPINK_ID2,7);
        assertEquals(num_ColorPINK_ID3,8);
        assertEquals(num_ColorLIGHT_BLUE_ID1,7);
        assertEquals(num_ColorLIGHT_BLUE_ID2,7);
        assertEquals(num_ColorLIGHT_BLUE_ID3,8);
        assertEquals(num_ColorGREEN_ID1,7);
        assertEquals(num_ColorGREEN_ID2,7);
        assertEquals(num_ColorGREEN_ID3,8);
        assertEquals(num_ColorWHITE_ID1,7);
        assertEquals(num_ColorWHITE_ID2,7);
        assertEquals(num_ColorWHITE_ID3,8);
    }
}
