package it.polimi.ingsw.turn_manager_test;

import it.polimi.ingsw.game_data.GameData;
import it.polimi.ingsw.game_manager.TurnManager;
import it.polimi.ingsw.plank.CellPlank;
import it.polimi.ingsw.plank.Plank;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TurnManagerTest {
    private static TurnManager turnManager;
    @BeforeAll
    public static void reInit() throws IOException, ParseException {
        turnManager=new TurnManager(null,null,null);
    }
    @Test
    public void turnManagerTest_checkDragColumn(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertTrue(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDrag2Column(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        CellPlank temp=cellPlanks.get(1);
        cellPlanks.set(1,cellPlanks.get(0));
        cellPlanks.set(0,temp);
        assertTrue(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragRow(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,0,i);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        CellPlank temp=cellPlanks.get(1);
        cellPlanks.set(1,cellPlanks.get(0));
        cellPlanks.set(0,temp);
        assertTrue(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragNotAdjacense(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,0,i*2);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertFalse(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragNotAdjacense2(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<3;i++){
            CellPlank cellPlank=new CellPlank(null,i*2,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        assertFalse(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragOneCell(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        cellPlanks.add(new CellPlank(null,0,0));
        cellPlanks.get(0).setPlayable(true);
        boolean result=turnManager.checkDrag(cellPlanks);
        assertTrue(result);
    }
    @Test
    public void turnManagerTest_checkDragTwoCell(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        cellPlanks.add(new CellPlank(null,4,4));
        cellPlanks.get(0).setPlayable(true);
        cellPlanks.add(new CellPlank(null,5,4));
        cellPlanks.get(1).setPlayable(true);
        boolean result=turnManager.checkDrag(cellPlanks);
        assertTrue(result);
    }
    @Test
    public void turnManagerTest_checkDragDifficultColumn(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<15;i++){
            CellPlank cellPlank=new CellPlank(null,i,0);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        Collections.shuffle(cellPlanks);
        assertTrue(turnManager.checkDrag(cellPlanks));
    }
    @Test
    public void turnManagerTest_checkDragDifficultRow(){
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i<15;i++){
            CellPlank cellPlank=new CellPlank(null,0,i);
            cellPlank.setPlayable(true);
            cellPlanks.add(cellPlank);
        }
        Collections.shuffle(cellPlanks);
        assertTrue(turnManager.checkDrag(cellPlanks));
    }
}
