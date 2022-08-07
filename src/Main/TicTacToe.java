// Copyright© by Fin & https://stackoverflow.com/questions/23233456/tic-tac-toe-in-java-using-2-d-arrays

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
    static String seeYou = "See you next time!";

    static int player = 1;

    static boolean done = false;
    static boolean flag = false;
    static boolean flag1 = false;
    static boolean flag2 = false;
    static boolean flag3 = false;
    static boolean flag4 = false;

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
        for (int horizontal = 0; horizontal <= 2; horizontal++) {
            if (Objects.equals(board[horizontal][0], board[horizontal][1]) && Objects.equals(board[horizontal][1], board[horizontal][2]) && !Objects.equals(board[horizontal][2], " ")) {
                flag1 = true;
                break;
            }
        }

        for (int vertical = 0; vertical <= 2; vertical++) {
            if (Objects.equals(board[vertical][0], board[vertical][1]) && Objects.equals(board[vertical][1], board[vertical][2]) && !Objects.equals(board[vertical][2], " ")) {
                flag2 = true;
                break;
            }
        }

        if (Objects.equals(board[0][0], board[1][1]) && Objects.equals(board[1][1], board[2][2]) && !Objects.equals(board[2][2], " ")) {
            flag3 = true;
        }

        if (Objects.equals(board[0][2], board[1][1]) && Objects.equals(board[2][0], board[2][2]) && !Objects.equals(board[2][0], " ")) {
            flag4 = true;
        }

        if (flag1 || flag2 || flag3 || flag4) {
            flag = true;
            flag1 = false;
            flag2 = false;
            flag3 = false;
            flag4 = false;

            for (int i = 0; i < board.length; i++) {
                for (int i1 = 0; i1 < board.length; i1++) {
                    board[i][i1] = " ";
                }
            }

            System.out.println(player == 1 ? "Player 2 is the winner!" : "Player 1 is the winner!");
            System.out.println();
            System.out.println("Again? y/n");

            player = 1;

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