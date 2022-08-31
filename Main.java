package tictactoe;
import java.util.Scanner;

public class Main {
    static String[][] gameboard;
    static final int size = 4;
    static String line = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        gameboard = new String[size][size];
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                gameboard[i][j] = " ";
            }
        }
        printArray(gameboard);

        int movesMade = 0;
        String player = "X";
        boolean gameOver = false;

        while (!gameOver) {

            checkInputCoordinates(gameboard, player);

            gameOver = checkBoardCondition(gameboard);

            movesMade++;
            if (movesMade % 2 == 0) {
                player = "X";
            } else {
                player = "O";
            }
        }
    }

    public static void printArray(String[][] gameboard) {
        System.out.println("---------");
        System.out.println("| " + gameboard[1][1] + " " + gameboard[1][2] + " " + gameboard[1][3] + " |");
        System.out.println("| " + gameboard[2][1] + " " + gameboard[2][2] + " " + gameboard[2][3] + " |");
        System.out.println("| " + gameboard[3][1] + " " + gameboard[3][2] + " " + gameboard[3][3] + " |");
        System.out.println("---------");
    }


    public static void checkInputCoordinates(String[][] gameboard, String player) {
        int coordinate_X = 0;
        int coordinate_O = 0;
        int nQuotient = 0;
        Scanner input = new Scanner(System.in);
        boolean bError = true;
        do {
            try {
                coordinate_X = Integer.parseInt(input.next());

                coordinate_O = Integer.parseInt(input.next());

                nQuotient = coordinate_X / coordinate_O;

                bError = false;
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                // input.reset();
                //input.next();
            }
        } while (bError);
        //System.out.printf("%d/%d = %d", coordinate_X, coordinate_O, nQuotient);
        checkInputNumbers(coordinate_X, coordinate_O, gameboard, player);

    }


    public static void checkInputNumbers(int coordinate_X, int coordinate_Y, String[][] gameboard, String player) {
        if ((coordinate_X < 1 || coordinate_X > 3) || (coordinate_Y < 1 || coordinate_Y > 3)) {
            System.out.println("Coordinates should be from 1 to 3!");
            checkInputCoordinates(gameboard, player);
        } else {
            checkIfCellIsEmpty(coordinate_X, coordinate_Y, gameboard, player);
        }
    }

    public static void checkIfCellIsEmpty(int coordinate_X, int coordinate_Y, String[][] gameboard, String player) {
        while (true) {
            if (IsCellEmpty(coordinate_X, coordinate_Y, gameboard)) {
                gameboard[coordinate_X][coordinate_Y] = player;
                printArray(gameboard);
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                checkInputCoordinates(gameboard, player);
                break;
            }
        }
    }

    public static boolean IsCellEmpty(int coordinate_X, int coordinate_Y, String[][] gameboard) {
        if (gameboard[coordinate_X][coordinate_Y].equals(" ")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkBoardCondition(String[][] gameboard) {
        int xWins = 0;

        if (gameboard[1][1].equals("X") && gameboard[1][2].equals("X") && gameboard[1][3].equals("X")) {
            xWins++;
        } if (gameboard[2][1].equals("X") && gameboard[2][2].equals("X") && gameboard[2][3].equals("X")) {
            xWins++;
        } if (gameboard[3][1].equals("X") && gameboard[3][2].equals("X") && gameboard[3][3].equals("X")) {
            xWins++;
        } if (gameboard[1][1].equals("X") && gameboard[2][1].equals("X") && gameboard[3][1].equals("X")) {
            xWins++;
        } if (gameboard[1][2].equals("X") && gameboard[2][2].equals("X") && gameboard[3][2].equals("X")) {
            xWins++;
        } if (gameboard[1][3].equals("X") && gameboard[2][3].equals("X") && gameboard[3][3].equals("X")) {
            xWins++;
        } if (gameboard[1][1].equals("X") && gameboard[2][2].equals("X") && gameboard[3][3].equals("X")) {
            xWins++;
        } if (gameboard[1][3].equals("X") && gameboard[2][2].equals("X") && gameboard[3][1].equals("X")) {
            xWins++;
        }

        int oWins = 0;

        if (gameboard[1][1].equals("O") && gameboard[1][2].equals("O") && gameboard[1][3].equals("O")) {
            oWins++;
        } if (gameboard[2][1].equals("O") && gameboard[2][2].equals("O") && gameboard[2][3].equals("O")) {
            oWins++;
        } if (gameboard[3][1].equals("O") && gameboard[3][2].equals("O") && gameboard[3][3].equals("O")) {
            oWins++;
        } if (gameboard[1][1].equals("O") && gameboard[2][1].equals("O") && gameboard[3][1].equals("O")) {
            oWins++;
        } if (gameboard[1][2].equals("O") && gameboard[2][2].equals("O") && gameboard[3][2].equals("O")) {
            oWins++;
        } if (gameboard[1][3].equals("O") && gameboard[2][3].equals("O") && gameboard[3][3].equals("O")) {
            oWins++;
        } if (gameboard[1][1].equals("O") && gameboard[2][2].equals("O") && gameboard[3][3].equals("O")) {
            oWins++;
        } if (gameboard[1][3].equals("O") && gameboard[2][2].equals("O") && gameboard[3][1].equals("O")) {
            oWins++;
        }

        int numX = 0;
        int numO = 0;
        int num_ = 0;

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (gameboard[i][j].equals("X")) {
                    numX++;
                } else if (gameboard[i][j].equals("O")) {
                    numO++;
                } else {
                    num_++;
                }
            }
        }




        if (xWins + oWins > 1) {
            System.out.println("Impossible");
            return true;
        } else if (Math.abs(numX - numO) > 1) {
            System.out.println("Impossible");
            return true;
        } else if (xWins == 1) {
            System.out.println("X wins");
            return true;
        } else if (oWins == 1) {
            System.out.println("O wins");
            return true;
        } else if (num_ > 0) {
            //System.out.println("Game not finished");
            return false;
        } else {
            System.out.println("Draw");
            return true;
        }
    }
}
