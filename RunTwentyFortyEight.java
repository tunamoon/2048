package org.cis1200.twentyfortyeight;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;

public class RunTwentyFortyEight implements Runnable, Serializable {
    final JFrame frame = new JFrame("2048");
    final JPanel statusPanel = new JPanel();
    final JLabel status = new JLabel("Setting up...");
    final org.cis1200.twentyfortyeight.GameBoard board = new GameBoard(status);
    final JPanel controlPanel = new JPanel();
    final JButton reset = new JButton("Reset");
    final JButton undo = new JButton("Undo");

    final JButton load = new JButton("Load");
    final JButton save = new JButton("Save");

    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        //final JFrame frame = new JFrame("2048");
        frame.setLocation(400, 400);

        // Status panel
        //final JPanel statusPanel = new JPanel();
        frame.add(statusPanel, BorderLayout.SOUTH);
        //final JLabel status = new JLabel("Setting up...");
        statusPanel.add(status);

        // Game board
        //final org.cis1200.twentyfortyeight.GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        //final JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        //final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        controlPanel.add(reset);

        //undo button
        //final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> board.undo());
        undo.addActionListener(e -> board.updateStatus());
        controlPanel.add(undo);

        File file = Paths.get("files/StoredData.csv").toFile();
        save.addActionListener(e -> board.saveGameDataToFile(file));
        save.addActionListener(e -> board.updateStatus());
        controlPanel.add(save);

        load.addActionListener(e -> board.updateStatus());
        load.addActionListener(e -> board.loadGameDataFromFile(file));
        controlPanel.add(load);


        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game

        board.reset();

        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Hello! Let's play 2048 :) Move the blocks" +
                " using the \narrow keys to merge them and get a 2048 block to win." +
                " \nYour score will update every time you merge a block. ");
    }



}
