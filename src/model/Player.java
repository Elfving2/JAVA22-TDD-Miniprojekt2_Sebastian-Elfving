package model;

import model.Board;

public class Player {
    private String playerEmblem;
    private String computerEmblem;
    private int depth;
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public String getPlayerEmblem() {
        return playerEmblem;
    }
    public String getComputerEmblem() {
        return computerEmblem;
    }
    public void setPlayerEmblem(String playerEmblem) {
        this.playerEmblem = playerEmblem;
    }
    public void setComputerEmblem(String computerEmblem) {
        this.computerEmblem = computerEmblem;
    }
    public boolean validDepth(int userInput, Board board) {
        int maxDepth = (int) Math.pow(board.getBoardSize(), 2);
        return userInput <= maxDepth;
    }
}
