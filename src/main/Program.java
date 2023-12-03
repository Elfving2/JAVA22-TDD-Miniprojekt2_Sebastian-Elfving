package main;
import controller.Controller;
import model.Player;
import model.Board;
import view.View;
public class Program {
    public static void main(String[] args) {
        Player player = new Player();
        Board board = new Board();
        View view = new View();

        Controller controller = new Controller(board, view, player);

        runProgram(controller);
    }

    private static void runProgram(Controller controller) {
        controller.boardSize();
        controller.depth();
        controller.emblems();
        controller.playGame();
    }
}
