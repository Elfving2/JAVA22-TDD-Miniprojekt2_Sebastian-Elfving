package model;

public class MinMax implements Runnable{
    private int[] bestMoveP = new int[2];
    boolean flag = true;
     Player player;
     Board board;
    public MinMax(Player player, Board board) {
        this.player = player;
        this.board = board;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public void setBestMoveP(int valOne, int valTwo) {
        this.bestMoveP[0] = valOne;
        this.bestMoveP[1] = valTwo;
    }
    public int[] getBestMoveP() {
        return bestMoveP;
    }
    public int minMax(boolean isMaximizing, Board board, String player, String opponent, int depth) {
        if (board.hasPlayerWon(player) || board.hasPlayerWon(opponent)) {
            return !isMaximizing ? 10 : -10;
        }

        if (board.isGameOver(player, opponent) || depth == 0) {
            return 0;
        }

        if (isMaximizing) {
            int highestVal = Integer.MIN_VALUE;
            // int [] bestMove = new int[2];
            for (int i = 0; i < board.getBoardSize(); i++) {
                for (int j = 0; j < board.getBoardSize(); j++) {
                    if (board.isValidMove(i, j)) {
                        board.placeMarker(player, i, j);
                        int score = minMax(false, board, player, opponent, depth -1 );
                        if (score > highestVal) {
                            highestVal = score;
                            setBestMoveP(i, j);
                        }
                        board.placeMarker(" ", i, j);
                    }
                }
            }
            return  highestVal;
        }
        else {
            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.getBoardSize(); i++) {
                for (int j = 0; j < board.getBoardSize(); j++) {
                    if (board.isValidMove(i, j)) {
                        board.placeMarker(opponent, i, j);
                        minScore = Math.min(minMax(true, board, player, opponent, depth - 1), minScore);
                        board.placeMarker(" ", i, j);
                    }
                }
            }
            return minScore;
        }
    }
    @Override
    public void run() {
        if (flag) {
            minMax(true,board, player.getPlayerEmblem(), player.getComputerEmblem(), player.getDepth());
            this.flag = false;
        } else {
            minMax(true, board, player.getComputerEmblem(), player.getPlayerEmblem(), player.getDepth());
            this.flag = true;
        }
    }
}

