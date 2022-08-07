// Copyright© by Fin & https://stackoverflow.com/questions/23233456/tic-tac-toe-in-java-using-2-d-arrays & https://stackoverflow.com/questions/22571979/2d-arrays-tictactoe-program

package Main;

import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {

    static Scanner input = new Scanner(System.in);
    static String[][] board =
            {
                    {" ", " ", " "},
                    {" ", " ", " "},
                    {" ", " ", " "}
            };

    static String row = "0";
    static String column = "0";
    static String choose = "0 or 1!";
    static String occupied = "Occupied!";
    static String tieGame = "Tie game!";
    static String seeYou = "See you next time!";

    static int player = 1;
    static int moveCount = 0;

    static boolean done = false;
    static boolean flag = false;
    static boolean horizontalFlag = false;
    static boolean verticalFlag = false;
    static boolean topLeftToBottomRightFlag = false;
    static boolean topRigthToBottomLeftFlag = false;

    public static void main(String[] args) {
        System.out.println("- Version 1.0 -");
        System.out.println();
        choosePosition();
    }

    static void choosePosition() {
        while (!done) {
            try {
                // Row
                System.out.println("Player " + player + ": Enter row (Zeile): ");
                row = input.nextLine();
                if (row.equals("exit")) {
                    exit();
                }
                if (!(Integer.parseInt(row) >= 0 && Integer.parseInt(row) <= 2)) {
                    System.out.println(choose);
                    choosePosition();
                }

                // Column
                System.out.println("Player " + player + ": Enter column (Spalte): ");
                column = input.nextLine();
                if (column.equals("exit")) {
                    exit();
                }
                if (!(Integer.parseInt(column) >= 0 && Integer.parseInt(column) <= 2)) {
                    System.out.println(choose);
                    choosePosition();
                }
                if (board[Integer.parseInt(row)][Integer.parseInt(column)].equals("X")
                        || board[Integer.parseInt(row)][Integer.parseInt(column)].equals("O")) {
                    System.out.println(occupied);
                    choosePosition();
                }

                setPlayerMark();
            } catch (NumberFormatException ignore) {
                System.out.println(choose);
                choosePosition();
            }
        }
    }

    static void setPlayerMark() {
        if (player == 1) {
            board[Integer.parseInt(row)][Integer.parseInt(column)] = "X";
            player = 2;
        } else {
            board[Integer.parseInt(row)][Integer.parseInt(column)] = "O";
            player = 1;
        }
        moveCount++;
        printBoard(board);
        checkForWinner(board);
        choosePosition();
    }

    static void printBoard(String[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print("|" + board[row][column] + "|");
            }
            System.out.println();
            System.out.println("---------");
        }
    }

    static void checkForWinner(String[][] board) {
        if (Objects.equals(board[Integer.parseInt(row)][0], board[Integer.parseInt(row)][1]) && Objects.equals(board[Integer.parseInt(row)][1], board[Integer.parseInt(row)][2]) && (Objects.equals(board[Integer.parseInt(row)][2], "X") || Objects.equals(board[Integer.parseInt(row)][2], "O"))) {
            horizontalFlag = true;
        }
        if (Objects.equals(board[0][Integer.parseInt(column)], board[1][Integer.parseInt(column)]) && Objects.equals(board[1][Integer.parseInt(column)], board[2][Integer.parseInt(column)]) && (Objects.equals(board[2][Integer.parseInt(column)], "X") || Objects.equals(board[Integer.parseInt(column)][2], "O"))) {
            verticalFlag = true;
        }

        if (Objects.equals(board[0][0], board[1][1]) && Objects.equals(board[1][1], board[2][2]) && (Objects.equals(board[0][0], "X") || Objects.equals(board[0][0], "O"))) {
            topLeftToBottomRightFlag = true;
        }

        if (Objects.equals(board[2][0], board[1][1]) && Objects.equals(board[1][1], board[0][2]) && (Objects.equals(board[2][0], "X") || Objects.equals(board[2][0], "O"))) {
            topRigthToBottomLeftFlag = true;
        }

        if (moveCount == 8) {
            System.out.println(tieGame);
            choosePosition();
        }

        if (horizontalFlag || verticalFlag || topLeftToBottomRightFlag || topRigthToBottomLeftFlag) {
            flag = true;
            horizontalFlag = false;
            verticalFlag = false;
            topLeftToBottomRightFlag = false;
            topRigthToBottomLeftFlag = false;

            for (int i = 0; i < board.length; i++) {
                for (int i1 = 0; i1 < board.length; i1++) {
                    board[i][i1] = " ";
                }
            }

            System.out.println(player == 1 ? "Player 2 is the winner!" : "Player 1 is the winner!");
            System.out.println();
            System.out.println("Again? y/n");

            player = 1;
            moveCount = 0;

            String choose = input.nextLine();
            if (choose.equals("y")) {
                choosePosition();
            } else {
                exit();
            }
        }
    }

    static void exit() {
        System.out.println(seeYou);
        System.exit(0);
    }
}