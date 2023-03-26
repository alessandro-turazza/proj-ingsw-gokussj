package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CardBagTest {
    private CardBag cardBag;
    private ArrayList<DataObjectCard> listDataObjectCard;
    private static Color convertToColor(String s){
        if(s.equals("YELLOW"))
            return Color.YELLOW;
        if(s.equals("BLUE"))
            return Color.BLUE;
        if(s.equals("PINK"))
            return Color.PINK;
        if(s.equals("LIGHT_BLUE"))
            return Color.LIGHT_BLUE;
        if(s.equals("GREEN"))
            return Color.GREEN;
        if(s.equals("WHITE"))
            return Color.WHITE;
        return null;

    }
    private static ArrayList<DataObjectCard> insertToFileObjectCard(String path) throws IOException {
        //struct file for each line "IdObjectCard;ColorObjectCard;NumObjectCard"
        BufferedReader buff = new BufferedReader(new FileReader(path));
        ArrayList<DataObjectCard> dataCardBag=new ArrayList<>();
        String line = buff.readLine();
        while(line != null){
            line.trim();
            String[] params = line.split(";");
                ObjectCard objectCard=new ObjectCard(Integer.parseInt(params[0]),convertToColor(params[1]));
                DataObjectCard dataObjectCard=new DataObjectCard(objectCard,Integer.parseInt(params[2]));
                dataCardBag.add(dataObjectCard);
            line = buff.readLine();
        }
        return dataCardBag;
    }
    @BeforeEach
    public void initCardBagTest(){
        cardBag=new CardBag();
        listDataObjectCard=new ArrayList<DataObjectCard>();
    }

    @Test
    public void CardBag_Test_hasNextTrue(){
        ObjectCard objectCard=new ObjectCard(1,Color.WHITE);
        DataObjectCard dataObjectCard=new DataObjectCard(objectCard,1);
        listDataObjectCard.add(dataObjectCard);
        cardBag.initializeCardBag(listDataObjectCard);
        //insert 1 element in to cardBag
        assertTrue(cardBag.hasNext());
    }
    @Test
    public void CardBag_Test_hasNextFalse(){
        assertFalse(cardBag.hasNext());
    }
    @Test
    public void CardBag_Test_nextFalse() {
        assertEquals(cardBag.next(),null);
    }
    @Test
    public void CardBag_Test_nextTrue(){
        ObjectCard objectCard=new ObjectCard(1,Color.WHITE);
        DataObjectCard dataObjectCard=new DataObjectCard(objectCard,1);
        listDataObjectCard.add(dataObjectCard);
        cardBag.initializeCardBag(listDataObjectCard);
        //insert 1 element in to cardBag
        assertEquals(cardBag.next(),objectCard);
    }

    @Test
    public void CardBag_Test_initializeCardBag() throws IOException {
        ObjectCard objectCard;
        cardBag.initializeCardBag(insertToFileObjectCard("src/test/TestFiles/CardBag_FileTest"));
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
