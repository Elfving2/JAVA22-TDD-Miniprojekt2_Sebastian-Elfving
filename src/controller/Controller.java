package controller;

import model.Player;
import model.Board;
import model.MinMax;
import view.View;

import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    Board board;
    View view;
    Player player;
    Scanner scanner = new Scanner(System.in);
    public Controller(Board board, View view, Player player) {
        this.board = board;
        this.view = view;
        this.player = player;
    }
    public void boardSize() {
        view.userValidBoardInput(scanner, board);
    }
    //CHANGE
    public void depth() {
        view.validDepth(scanner, player, board);
    }
    public void emblems() {
        view.userAndComputerValidEmblems(scanner, player);
    }
    public int[] runInBackGround(MinMax minMax) {
        Thread thread = new Thread(minMax);
        thread.start();
        try {
            System.out.println("Loading...");
            thread.join();
            System.out.println("Done!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return minMax.getBestMoveP();
    }
    public void playGame() {
        MinMax minMax = new MinMax(player,board);
        while (true) {

            board.print();
            System.out.println(Arrays.toString(runInBackGround(minMax)));
            int[] userChoice = view.checkForValidInput(scanner, board);
            board.placeMarker(player.getPlayerEmblem(), userChoice[0], userChoice[1]);

            if (board.hasPlayerWon(player.getPlayerEmblem())) return;
            board.print();
            int[] computerChoice = runInBackGround(minMax);
            board.placeMarker(player.getComputerEmblem(), computerChoice[0], computerChoice[1]);


            if (board.hasPlayerWon(player.getComputerEmblem())) return;
            if(!board.hasEmptyCells()) return;
        }
    }
}
