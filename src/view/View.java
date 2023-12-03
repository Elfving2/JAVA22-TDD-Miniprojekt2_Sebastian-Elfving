package view;
import model.Player;
import model.Board;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class View {
    public int isValidBoard(Scanner scanner) {
        System.out.println("Enter a number board size");
        while (true)  {
            try {
                int input = scanner.nextInt();
                if (input > 3) {
                    System.out.println("Are you sure you want a big board? Y : N");
                    scanner.nextLine();
                    if (scanner.nextLine().equalsIgnoreCase("Y")) {
                        return input;
                    }
                    else {System.exit(1);}
                } else if (input < 3 ) throw new NumberFormatException();
                else return input;
            } catch(NumberFormatException e ) {
                System.out.println("Invalid Input, Enter a valid board size");
            } catch(InputMismatchException e) {
                System.out.println("Invalid Input, Enter a valid board size");
                scanner.next();
            }
        }
    }

    public void userValidBoardInput(Scanner scanner, Board board) {
        int boardSize = isValidBoard(scanner);
        board.setBoardSize(boardSize);
        board.setBoard(boardSize);
    }

    public void validDepth(Scanner scanner, Player player, Board board) {
        System.out.println("Enter a depth. Largest depth you can set is -> " + (int) Math.pow(board.getBoardSize(), 2) );
        while (true) {
            try {
                int input = scanner.nextInt();
                if (player.validDepth(input, board)) {
                    player.setDepth(input);
                    return;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid depth try again");
            } catch (NoSuchElementException e) {
                System.out.println("Invalid depth try again");
                scanner.next();
            }
        }
    }
    public void userAndComputerValidEmblems(Scanner scanner, Player player) {
        scanner.nextLine();
        while (true) {
            try {
                System.out.println("Player Emblem");
                String playerEmblem = scanner.nextLine().trim();

                System.out.println("Computer Emblem");
                String computerEmblem = scanner.nextLine().trim();

                if (playerEmblem.length() == 1 && computerEmblem.length() == 1 && !playerEmblem.equals(computerEmblem)) {
                    player.setPlayerEmblem(playerEmblem);
                    player.setComputerEmblem(computerEmblem);
                    return;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Input try Again");
            }
        }
    }
    public int[] checkForValidInput(Scanner scanner, Board board) {
        int[] arr = new int[2];

        while (true) {
            int indexCounter = 0;
            try {
                System.out.println("Enter two numbers between 0 and " + board.getBoardSize() + " seperated with a space");
                String str = scanner.nextLine();
                String[] split = str.split(" ");
                for (String input : split) {
                    if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < board.getBoardSize()) {
                        arr[indexCounter] = Integer.parseInt(input);
                        indexCounter++;
                    } else {
                        throw new NumberFormatException();
                    }
                }

                if (board.isValidMove(arr[0], arr[1])) {
                    return arr;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                arr = new int[2];
                System.out.println("spot already taken or Invalid input try again");
            }
        }
    }
}
