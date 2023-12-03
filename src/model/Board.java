package model;

import java.util.Arrays;
import java.util.Scanner;
public class Board {
    private int boardSize;
    private String[][] board;
//    // TEMP Solution
    public void populateBoard() {
        for (String[] s : board) {
            Arrays.fill(s, " ");
        }
    }

    public Board(){}
    public void setBoard(int boardSize) {
        this.board = new String[boardSize][boardSize];
        populateBoard();
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    public int getBoardSize() {
        return boardSize;
    }
    public void print() {
        System.out.println();
        for (int i = 0; i < this.board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }
    public boolean isValidMove(int col, int row) {
        return board[col][row].equals(" ");
    }
    public void placeMarker(String emblem, int col, int row) {
        board[col][row] = emblem;
    }
    public boolean hasPlayerWon(String player) {
        return validateHorizontal(player) || validateVertically(player) || validateDiagonally(player);
    }
    public boolean validateHorizontal(String emblem) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if ( j != 0 && j != board.length - 1) {
                    if (board[i][j - 1].equals(emblem) && board[i][j].equals(emblem) && board[i][j + 1].equals(emblem)) {
                        return  true;
                    }
                }
            }
        }
        return false;
    }


    public boolean validateVertically(String emblem) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i > 0 && i < board.length -1 ) {
                    if (board[i - 1][j].equals(emblem) && board[i][j].equals(emblem) && board[i + 1][j].equals(emblem)) {
                        return  true;
                    }
                }
            }
        }
        return false;
    }
    public boolean validateDiagonally(String emblem) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i + 2 <= board.length - 1) {
                    if (j + 2 <= board.length - 1) {
                        if (board[i + 1][j + 1].equals(emblem) && board[i][j].equals(emblem) && board[i + 2][j + 2].equals(emblem)) {
                            return true;
                        }
                    }
                    if (j >= 2) {
                        if (board[i + 1][j - 1].equals(emblem) && board[i][j].equals(emblem) && board[i + 2][j - 2].equals(emblem)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean isGameOver(String player, String opponent) {
        return hasPlayerWon(player) || hasPlayerWon(opponent) || !hasEmptyCells();
    }
    public boolean hasEmptyCells() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals(" ")) {
                    return true;
                }
            }
        }
        return false;
    }
}