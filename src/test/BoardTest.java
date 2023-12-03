package test;
import model.Board;
import model.MinMax;
import model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.View;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;
class BoardTest {

    @DisplayName("Runs test and gives best possible move, in this case it is 0,0")
    @Test
    void testSetAndGetBestMoveP() {
        Player player = new Player();
        player.setPlayerEmblem("X");
        player.setComputerEmblem("O");
        Board board = new Board();
        board.setBoard(3);
        player.setDepth(9);
        MinMax minMax = new MinMax(player, board);
        minMax.minMax(true, board, player.getPlayerEmblem(), player.getComputerEmblem(), player.getDepth());


        assertArrayEquals(new int[]{0, 0}, minMax.getBestMoveP());
    }
    @DisplayName("Tests if we win in horizontal position")
    @Test
    void winningBoardHorizontal() {
        Board board = new Board();
        Player player = new Player();

        player.setPlayerEmblem("X");
        player.setComputerEmblem("O");
        player.setDepth(9);

        board.setBoard(3);

        board.placeMarker("X", 0,0);
        board.placeMarker("X", 0,1);
        board.placeMarker("X", 0,2);


        MinMax minMax = new MinMax(player, board);
        minMax.minMax(true, board,player.getPlayerEmblem(),player.getComputerEmblem(), player.getDepth());
        assertTrue(board.validateHorizontal("X"));
    }

    @DisplayName("Tests if we win in vertical position")
    @Test
    void winningBoardVertical() {
        Board board = new Board();
        Player player = new Player();

        player.setPlayerEmblem("X");
        player.setComputerEmblem("O");
        player.setDepth(9);

        board.setBoard(3);

        board.placeMarker("X", 0, 1);
        board.placeMarker("X", 1, 1);
        board.placeMarker("X", 2, 1);


        MinMax minMax = new MinMax(player, board);
        minMax.minMax(true, board, player.getPlayerEmblem(), player.getComputerEmblem(), player.getDepth());
        assertTrue(board.validateVertically("X"));
    }


    @DisplayName("Tests if we win in diagonally position")
    @Test
    void winningBoardDiagonally() {
        Board board = new Board();
        Player player = new Player();

        player.setPlayerEmblem("X");
        player.setComputerEmblem("O");
        player.setDepth(9);

        board.setBoard(3);

        board.placeMarker("X", 0, 2);
        board.placeMarker("X", 1, 1);
        board.placeMarker("X", 2, 0);


        MinMax minMax = new MinMax(player, board);
        minMax.minMax(true, board, player.getPlayerEmblem(), player.getComputerEmblem(), player.getDepth());
        assertTrue(board.validateDiagonally("X"));
    }

    @DisplayName("Test return type of board if there are no more possible moves")
    @Test
    void noWinner() {
        Board board = new Board();
        Player player = new Player();

        player.setPlayerEmblem("X");
        player.setComputerEmblem("O");

        player.setDepth(9);
        board.setBoard(3);

        board.placeMarker("X", 0, 0);
        board.placeMarker("O", 0, 1);
        board.placeMarker("X", 0, 2);

        board.placeMarker("X", 1, 0);
        board.placeMarker("X", 1, 1);
        board.placeMarker("O", 1, 2);

        board.placeMarker("O", 2, 0);
        board.placeMarker("X", 2, 1);
        board.placeMarker("O", 2, 2);

        assertFalse(board.hasEmptyCells());
    }
    // VIEW

    @DisplayName("Testing a valid board ")
    @Test
    void testIsValidBoard() {
        View view = new View();
        Board board = new Board();

        String input = "4\nY\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        view.userValidBoardInput(scanner, board);
       // int result = view.isValidBoard(scanner);
        assertEquals(4, board.getBoardSize());
    }
    @DisplayName("Testing invalid board")
    @Test
    void invalidBoardString() {
        assertThrows(NoSuchElementException.class, () -> {
            View view = new View();

            String input = "A";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            Scanner scanner = new Scanner(in);

            view.isValidBoard(scanner);
        });
    }

    @DisplayName("Test valid computer and player emblems")
    @Test
    void testUserAndComputerValidEmblems() {
        View view = new View();
        Player player = new Player();

        String input = "P\nX\nO";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        view.userAndComputerValidEmblems(scanner, player);

        assertEquals("X", player.getPlayerEmblem());
        assertEquals("O", player.getComputerEmblem());
    }


    @DisplayName("Tests invalid player and computer inputs")
    @Test
    void testCheckForValidInput() {
        Board board = new Board();
        View view = new View();

        board.setBoard(3);
        board.setBoardSize(3);

        String input = "1 1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        int[] result = view.checkForValidInput(scanner, board);

        assertArrayEquals(new int[]{1, 1}, result);
    }
    @DisplayName("Tests invalid user input to board")
    @Test
    void invalidInputString() {
        assertThrows(NoSuchElementException.class, () -> {
            Board board = new Board();
            View view = new View();

            board.setBoard(3);
            board.setBoardSize(3);


            String input = "a 1\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            Scanner scanner = new Scanner(in);

            view.checkForValidInput(scanner, board);
        });
    }

    @DisplayName("Tests valid depth")
    @Test
    void testValidDepth() {
        View view = new View();
        Player player = new Player();
        Board board = new Board();
        board.setBoard(3);
        board.setBoardSize(3);
        // Mock user input for testing
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        view.validDepth(scanner, player, board);

        assertEquals(3, player.getDepth());
    }

    @DisplayName("Testing input of higher depth than what is possible")
    @Test
    void invalidDepthNumber() {
        assertThrows(Exception.class, () -> {
            View view = new View();
            Player player = new Player();
            Board board = new Board();
            board.setBoard(3);
            board.setBoardSize(3);
            // Mock user input for testing
            String input = "10";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            Scanner scanner = new Scanner(in);

            view.validDepth(scanner, player, board);
        });
    }

    @DisplayName("Tests invalid String input to depth")
    @Test
    void invalidDepthString() {
        assertThrows(NoSuchElementException.class, () -> {
            View view = new View();
            Player player = new Player();
            Board board = new Board();
            board.setBoard(3);
            board.setBoardSize(3);
            // Mock user input for testing
            String input = "s";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            Scanner scanner = new Scanner(in);

            view.validDepth(scanner, player, board);
        });
    }

    @DisplayName("Tests if flag changes from true to false ")
    @Test
    void setFlagFalse() throws InterruptedException {
        Player player = new Player();
        Board board = new Board();
        MinMax minMax = new MinMax(player, board);
        player.setDepth(9);
        player.setPlayerEmblem("X");
        player.setComputerEmblem("O");
        board.setBoard(3);
        board.setBoardSize(3);


        minMax.setFlag(true);
        Thread t = new Thread(minMax);
        t.start();

        t.join();

        assertFalse(minMax.getFlag());

    }

    @DisplayName("Tests if flag changes from false to true")
    @Test
    void setFlagTrue() throws InterruptedException {
        Player player = new Player();
        Board board = new Board();
        MinMax minMax = new MinMax(player, board);
        player.setDepth(9);
        player.setPlayerEmblem("X");
        player.setComputerEmblem("O");
        board.setBoard(3);
        board.setBoardSize(3);


        minMax.setFlag(false);
        Thread t = new Thread(minMax);
        t.start();

        t.join();

        assertTrue(minMax.getFlag());

    }
}