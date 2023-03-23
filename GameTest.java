package org.cis1200.twentyfortyeight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {


    @Test
    public void testCheckWinLoss() {
        TwentyFortyEight tfe = new TwentyFortyEight();
        //should only have one 2 block, no 2048 block -> no win condition
        assertFalse(tfe.win());

        //you can't get 2048 from two blocks
        tfe.moveDown();
        tfe.generateRandom();
        tfe.moveRight();
        tfe.generateRandom();
        assertFalse(tfe.win());

        //there's a 2048 block
        tfe.setCell(0, 0, 2048);
        assertTrue(tfe.win());

        //if you have a 4096 block, that means you got a 2048 block already!
        tfe.setCell(0, 0, 4096);
        assertTrue(tfe.win());

        int block = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tfe.setCell(i, j, block *= 2);
            }
        }
        assertTrue(tfe.lost());

    }

    @Test
    public void testCheckScore() {
        TwentyFortyEight tfe = new TwentyFortyEight();

        //the scores are saved
        int initScore = tfe.getScore();
        tfe.moveDown();
        tfe.generateRandom();
        tfe.moveRight();
        tfe.generateRandom();
        tfe.undo();
        tfe.undo();
        int currentScore = tfe.getScore();
        assertEquals(initScore, currentScore);

        TwentyFortyEight tfe1 = new TwentyFortyEight();
        tfe1.setCell(0, 0, 2);
        tfe1.setCell(0, 1, 2);
        tfe1.moveUp();
        //the high score should be updated to four
        assertEquals(4, tfe1.getHighScore());
        //the high score should stay four even after undo
        tfe1.undo();
        assertEquals(4, tfe1.getHighScore());

    }

    @Test
    public void testReset() {
        TwentyFortyEight tfe = new TwentyFortyEight();
        tfe.moveDown();
        tfe.generateRandom();
        tfe.moveRight();
        tfe.generateRandom();

        //there should only be one cell with one two
        tfe.reset();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tfe.getBoard()[i][j] == 2) {
                    count++;
                }
            }
        }
        assertEquals(count, 1);
    }

    @Test
    public void testGenerateRandom() {
        TwentyFortyEight tfe = new TwentyFortyEight();
        //there should be 2 random blocks none merged
        tfe.generateRandom();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tfe.getBoard()[i][j] == 2) {
                    count++;
                }
            }
        }
        assertEquals(2, count);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != 0 || j != 0) {
                    tfe.setCell(i, j, 2);
                }
            }
        }

        //the generateRandom should be in 0,0
        tfe.generateRandom();
        count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tfe.getCell(i, j) == 2) {
                    count++;
                }
            }
        }
        assertEquals(16, count);

    }

    @Test
    public void testUndo() {
        //right now the only move I have is the first 2
        TwentyFortyEight tfe = new TwentyFortyEight();
        assertEquals(1, tfe.getMoves().size());

        //the board should not change at first when you undo
        int[][] startBoard = tfe.getBoard();
        tfe.undo();
        assertEquals(startBoard, tfe.getBoard());


        //if you move two moves then undo them the boards should equal
        int[][] initBoard = tfe.getBoard();
        tfe.moveDown();
        tfe.generateRandom();
        tfe.moveRight();
        tfe.generateRandom();
        tfe.undo();
        tfe.undo();
        int[][] currentBoard = tfe.getBoard();
        assertEquals(initBoard, currentBoard);

        //calling another undo should not change the board
        tfe.undo();
        int[][] undoBoard = tfe.getBoard();
        assertEquals(undoBoard, currentBoard);

    }


}
