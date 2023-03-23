package org.cis1200.twentyfortyeight;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.io.*;


public class TwentyFortyEight implements Serializable {
    private static int[][] board;


    private static LinkedList<int[][]> allMoves = new LinkedList<>();

    private static int currentScore = 0;

    private static int highScore = 0;

    private static LinkedList<Integer> allScores = new LinkedList<>();

    private static LinkedList<Integer> allHighScores = new LinkedList<>();



    /**
     * Constructor sets up game state.
     */
    public TwentyFortyEight() {
        reset();
    }

    /**
     * play allows board to move in a certain direction depending on the key the
     * player presses. Returns true if the game can does not have all zero's or
     * there's a pair of the same numbers next to each other.
     *
     * @param k KeyEvent
     * @return whether the turn was successful
     */
    public boolean play(KeyEvent k) {
        if (win() || lost()) {
            return false;
        }


        int keyCode = k.getKeyCode();
        int[][] initBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                initBoard[i][j] = board[i][j];
            }
        }
        int initScore = currentScore;
        int initHighScore = highScore;
        boolean validKey = keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT ||
                keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN;
        if (validKey) {
            allMoves.addLast(initBoard);
            allScores.addLast(initScore);
            allHighScores.addLast(initHighScore);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight();
        } else if (keyCode == KeyEvent.VK_UP) {
            moveUp();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown();
        }

        if (!boardEquals(initBoard, board) && validKey) {
            generateRandom();
        }



        return true;
    }

    public boolean boardEquals(int[][] initBoard, int[][] currentBoard) {
        boolean boardEquals = true;
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                if (currentBoard[i][j] != initBoard[i][j]) {
                    boardEquals = false;
                }
            }
        }
        return boardEquals;
    }

    public int[][] getBoard() {
        return board;
    }

    public LinkedList<Integer> getScores() {
        return allScores;
    }

    // codes the movement when the left arrow key is pressed
    public void moveLeft() {
        moveLeftHelper();

        for (int r = 0; r < board.length - 1; r++) {
            for (int c = 0; c < board[r].length; c++) {

                if (board[r][c] == board[r + 1][c]) {
                    board[r][c] = board[r][c] * 2;
                    board[r + 1][c] = 0;
                    moveLeftHelper();
                    currentScore += board[r][c];
                    if (highScore < currentScore) {
                        highScore = currentScore;
                    }
                }
            }
        }
    }

    public void moveLeftHelper() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    int count = r;
                    while (count < board.length && board[count][c] == 0) {
                        count++;
                    }
                    if (count < board.length) {
                        board[r][c] = board[count][c];
                        board[count][c] = 0;
                    }

                }

            }
        }
    }

    // codes the movement when the right arrow key is pressed
    public void moveRight() {
        moveRightHelper();
        for (int r = board.length - 1; r >= 1; r--) {
            for (int c = 0; c < board[r].length; c++) {

                if (board[r][c] == board[r - 1][c]) {
                    board[r][c] = board[r][c] * 2;
                    board[r - 1][c] = 0;
                    moveRightHelper();
                    currentScore += board[r][c];
                    if (highScore < currentScore) {
                        highScore = currentScore;
                    }
                }

            }
        }
    }

    public void moveRightHelper() {
        for (int r = board.length - 1; r >= 0; r--) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    int count = r;
                    while (count >= 0 && board[count][c] == 0) {
                        count--;
                    }
                    if (count >= 0) {
                        board[r][c] = board[count][c];
                        board[count][c] = 0;
                    }

                }

            }
        }
    }

    //code moves up when the up arrow key is pressed
    public void moveUp() {
        moveUpHelper();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length - 1; c++) {

                if (board[r][c] == board[r][c + 1]) {
                    board[r][c] = board[r][c] * 2;
                    board[r][c + 1] = 0;
                    moveUpHelper();
                    currentScore += board[r][c];
                    if (highScore < currentScore) {
                        highScore = currentScore;
                    }
                }

            }
        }
    }

    public void moveUpHelper() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    int count = c;
                    while (count < board[r].length && board[r][count] == 0) {
                        count++;
                    }
                    if (count < board[r].length) {
                        board[r][c] = board[r][count];
                        board[r][count] = 0;
                    }

                }

            }
        }
    }

    // codes the movement when the down arrow key is pressed
    public void moveDown() {
        moveDownHelper();

        for (int r = 0; r < board.length; r++) {
            for (int c = board[r].length - 1; c >= 1; c--) {

                if (board[r][c] == board[r][c - 1]) {
                    board[r][c] = board[r][c] * 2;
                    board[r][c - 1] = 0;
                    moveDownHelper();
                    currentScore += board[r][c];
                    if (highScore < currentScore) {
                        highScore = currentScore;
                    }
                }

            }
        }
    }
    public void moveDownHelper() {
        for (int r = 0; r < board.length; r++) {
            for (int c = board[r].length - 1; c >= 0; c--) {
                if (board[r][c] == 0) {
                    int count = c;
                    while (count >= 0 && board[r][count] == 0) {
                        count--;
                    }
                    if (count >= 0) {
                        board[r][c] = board[r][count];
                        board[r][count] = 0;
                    }

                }

            }
        }
    }


    //undo the board
    public void undo() {
        if (allMoves.size() >= 2) {
            board = allMoves.peekLast();
            allMoves.removeLast();
            currentScore = allScores.peekLast();
            allScores.removeLast();
            highScore = allHighScores.peekLast();
            allHighScores.removeLast();
        }
    }

    //get the current total score of the board
    public int getScore() {
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    //generates a two in a random clear square
    public void generateRandom() {
        boolean empty = true;
        while (empty) {
            int r = (int)(Math.random() * 4);
            int c = (int)(Math.random() * 4);
            if (board[r][c] == 0) {
                board[r][c] = 2;
                empty = false;
            }
        }
    }

    //clears the board to all 0
    public void regenerate() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        generateRandom();
    }

    //check if the board has 2048
    public boolean win() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean lost() {
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j] == board[i + 1][j] || board[i][j] == board[i][j + 1]) {
                    return false;
                }
            }
        }
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }



    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new int[4][4];
        regenerate();
        allMoves = new LinkedList<>();
        allMoves.add(board);
        if (highScore < currentScore) {
            highScore = currentScore;
        }
        currentScore = 0;

    }

    public static void saveGameDataToFile(File file) {

        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    objectStream.writeObject(board[r][c]);
                }
            }
            objectStream.writeObject(allMoves);
            objectStream.writeObject(currentScore);
            objectStream.writeObject(highScore);
            objectStream.writeObject(allScores);
            objectStream.writeObject(allHighScores);

            objectStream.close();
            fileStream.close();


        } catch (IOException e) {
            System.out.print("Error");
        }
    }

    public static void loadGameDataFromFile(File file) {
        try {

            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);


            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    board[r][c] = (int) objectStream.readObject();
                }
            }
            allMoves = (LinkedList<int[][]>) objectStream.readObject();
            currentScore = (int) objectStream.readObject();
            highScore = (int) objectStream.readObject();
            allScores = (LinkedList<Integer>) objectStream.readObject();
            allHighScores = (LinkedList<Integer>) objectStream.readObject();

        } catch (Exception e) { }
    }

    //public access can be used for test
    public LinkedList<int[][]> getMoves() {
        return allMoves;
    }

    public void setCell(int r, int c, int num) {
        board[r][c] = num;
    }


    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board. 0 = empty, 1 = Player 1, 2 = Player 2
     */
    public int getCell(int c, int r) {
        return board[r][c];
    }

}
