package org.cis1200.twentyfortyeight;

/*
 * CIS 120 HW09 - 2048
 * (c) University of Pennsylvania
 * Created by Luna Chen in Fall 2020.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 *
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 *
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private TwentyFortyEight tfe; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        tfe = new TwentyFortyEight(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for keyPresses. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent k) {

                // updates the model given the type of key pressed
                tfe.play(k);
                updateStatus();
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        tfe.reset();
        status.setText("Play 2048");
        repaint();


        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void undo() {
        tfe.undo();
        repaint();


        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }


    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    public void updateStatus() {

        if (tfe.win()) {
            status.setText("You beat 2048!");
        } else if (tfe.lost()) {
            status.setText("You Lost :( Reset to try again!");
        } else {
            status.setText("High Score: " + tfe.getHighScore()
                    + "  Current Score: " + tfe.getScore());
        }

    }

    public void saveGameDataToFile(File file) {
        tfe.saveGameDataToFile(file);
        requestFocusInWindow();
    }

    public void loadGameDataFromFile(File file) {
        tfe.loadGameDataFromFile(file);
        repaint();
        requestFocusInWindow();
    }

    /**
     * Draws the game board.
     *
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);

        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);

        // Draws numbers in squares
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = tfe.getCell(i, j);
                if (num != 0) {
                    g.drawString("" + num, 50 + 100 * j, 50 + 100 * i);
                }
            }
        }

    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

}
