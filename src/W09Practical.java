import java.util.Arrays;
import java.util.Scanner;

public class W09Practical {
    //    Initialize array of 6 rows and 7 columns
    String[][] gameBoard = new String[6][7];
    //    create a player
    String player;

    //    Parameterized constructor, for setting the initial player
    W09Practical(String player) {
        this.player = player;
    }

    //    This is a a method that fetch the value inside an array
    private String getColumnNumber(int row, int column) throws ArrayIndexOutOfBoundsException {
        return gameBoard[row][column];
    }

    //This method is responsible for placing value inside the array
    private void setColumnValue(int row, int column, String value) throws ArrayIndexOutOfBoundsException {
        gameBoard[row][column] = value;
    }

    //    Picks the value from the top position of the row and check below it to see if there is a winner vertically
    private boolean checkUpWard(int row, int column, String player) {
        int count = 4;
        int countUp = row + 3;
        while (count >= 0
                && countUp >= 0
                && gameBoard[countUp][column].equalsIgnoreCase(player)
        ) {
            countUp--;
            count--;
        }

        return count == 0;
    }

    //   Picks the value from the left position of the column and checks to the right horizontally
    private boolean checkRight(int row, int column, String player) {
        int count = 0;
        int countRight = column;
        while (count <= 4
                && countRight <= 6
                && gameBoard[row][countRight] != null
                && gameBoard[row][countRight].equalsIgnoreCase(player)) {
            countRight++;
            count++;
        }
        return count == 4;
    }

    //    //    Picks the value from the right position and check below it to see if there is a winner horizontally
    private boolean checkLeft(int row, int column, String player) {
        int count = 4;
        int countLeft = column;
        while (count > 0
                && gameBoard[row][countLeft] != null
                && gameBoard[row][countLeft].equalsIgnoreCase(player)) {
            count--;
            countLeft--;
        }

        return count == 0;
    }

    //    Picks the value from the bottom position and checks to the left upward
    private boolean checkUpwardDiagonalLeft(int row, int column, String player) {
        int count = 4;
        int countLeft = column;
        int countUpward = row;

        while (count > 0

                && gameBoard[countUpward][countLeft] != null
                && gameBoard[countUpward][countLeft].equalsIgnoreCase(player)) {
            countLeft--;
            countUpward--;
            count--;
        }
        return count == 0;
    }

    //    Picks the value from the bottom position and checks to the right upward
    private boolean checkUpwardDiagonalRight(int row, int column, String player) {
        int count = 0;
        int countRight = column;
        int countUpward = row;
        while (count <= 4
                && countRight <= 6
                && gameBoard[countUpward][countRight] != null
                && gameBoard[countUpward][countRight].equalsIgnoreCase(player)) {
            count++;
            countRight++;
            countUpward--;
        }
        return count == 4;
    }

    //    Picks the value from the top position and checks to the right bottom
    private boolean checkDownwardDiagonalRight(int row, int column, String player) {
        int count = 0;
        int countRight = column;
        int countDown = row;
        while (count < 4
                && gameBoard[countDown][countRight] != null
                && gameBoard[countDown][countRight].equalsIgnoreCase(player)) {
            count++;
            countRight++;
            countDown++;
        }
        return count == 4;
    }

    //check downward left starts from the top right down to the left corner bottom
    private boolean checkDownwardDiagonalLeft(int row, int column, String player) {
        int count = 0;
        int countLeft = column;
        int countDown = row;
        while (count < 4
                && gameBoard[countDown][countLeft] != null
                && gameBoard[countDown][countLeft].equalsIgnoreCase(player)) {
            count++;
            countLeft--;
            countDown++;
        }
        return count == 4;
    }

    //    A method that checks if there is a winner
    public boolean detectWinner(int row, int column, String player) {

        if (row <= 2) {
            if (checkUpWard(row, column, player)) return true;

        }

        if (column + 3 <= 6) {
            if (checkRight(row, column, player)) return true;

        }

        if (column - 3 >= 0) {
            if (checkLeft(row, column, player)) return true;

        }


        if (row > 2 && column - 3 >= 0) {
            if (checkUpwardDiagonalLeft(row, column, player)) return true;

        }

        if (row > 2 && column + 3 <= 6) {
            if (checkUpwardDiagonalRight(row, column, player)) return true;

        }

        if (row <= 2 && column + 3 <= 6) {
            if (checkDownwardDiagonalRight(row, column, player)) return true;

        }

        if (row <= 2 && column - 3 >= 0) {
            if (checkDownwardDiagonalLeft(row, column, player)) return true;

        }

        return false;
    }

    //    Draws the game board
    public void printBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j <= 6; j++) {
                if (gameBoard[i][j] == null) {
                    gameBoard[i][j] = ".";
                }
            }

            System.out.println(Arrays.toString(gameBoard[i]));
        }
    }

    //    Check if the user tries to quit by passing in 0
    public boolean detectQuit(int value) {
        if (value == 0) {
            printBoard();
            System.out.println("Game Over: User quits");
            return true;
        }
        return false;
    }

    //The method to check if there is an illegal value
    public boolean detectIllegalInput(int value) {
        if (value < 0 || value > 7) {
            return true;
        }
        return false;
    }

    //    This is the method to Place column on board
    public boolean placeColumnOnboard(int column, String player) {
        try {
            int i = 5;
            while (i >= 0) {
                int columnPos = column - 1;
                String columnValue = getColumnNumber(i, columnPos);
                if (columnValue == null || columnValue.equals(".")) {
                    setColumnValue(i, columnPos, player);
                    if (detectWinner(i, columnPos, player)) {
                        printBoard();
                        System.out.println("Game Over " + player + " wins");
                        return true;
                    }
                    printBoard();
                    return false;
                }
                i--;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You cannot Insert into a filled column");
        }
        return false;
    }

    public void playGame() {
        Scanner reader = new Scanner(System.in);

        int countMoves = 1;

        System.out.println("Player " + player + ": Enter column number (1-7), or 0 to quit: 0");
        while (reader.hasNext()) {
            int input = reader.nextInt();

            if (detectQuit(input)) break;
            if (!detectIllegalInput(input)) {
                if (placeColumnOnboard(input, player)) break;

                this.player = this.player == "Y" ? "R" : "Y";
            } else {
                System.out.println("Illegal Input Column");
            }

            System.out.println("Player " + player + ": Enter column number (1-7), or 0 to quit: 0");
            if (countMoves == 42) {
                System.out.println("The game ends in Draw!!!");
                break;
            }
            countMoves++;
        }
    }
}
