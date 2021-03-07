import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = {  {' ', ' ', ' '},
                            {' ', ' ', ' '},
                            {' ', ' ', ' '}};
        Scanner scanner = new Scanner(System.in);
        printBoard(board);
        while(true) {
            playerTurn(board, scanner);
            if(isGameFinished(board)) {
                break;
            }
            computerTurn(board);
            if(isGameFinished(board)) {
                break;
            }
            printBoard(board);
        }
        scanner.close();

    }

    private static boolean hasWon(char[][] board, char symbol) {
        if(     (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||
                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||
                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    private static boolean isGameFinished(char[][] board) {
        if(hasWon(board, 'X')) {
            System.out.println("Player wins");
            return true;
        }

        if(hasWon(board, 'O')) {
            System.out.println("Computer wins");
            return true;
        }



        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("The game ended in a tie");
        return true;
    }

    private static void playerTurn(char[][] board, Scanner scanner) {
        String userInput;
        while (true) {
            System.out.println("Where would you like to play? ");

            userInput = scanner.nextLine();

            if(isValidMove(board, Integer.parseInt(userInput))) {
                break;
            }
        }
        placeMove(board, userInput, 'X');
    }

    private static void placeMove(char[][] board, String position, char symbol) {
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
        }
    }

    private static void computerTurn(char[][] board) {
        Random rand = new Random();
        int computerMove;
        while (true) {
            computerMove = rand.nextInt(9) + 1;
            if (isValidMove(board, computerMove)) {
                break;
            }
        }
        placeMove(board, Integer.toString(computerMove), 'O');
    }

    private static boolean isValidMove(char[][] board, int position) {
        switch (position) {
            case 1:
                return board[0][0] == ' ';
            case 2:
                return board[0][1] == ' ';
            case 3:
                return board[0][2] == ' ';
            case 4:
                return board[1][0] == ' ';
            case 5:
                return board[1][1] == ' ';
            case 6:
                return board[1][2] == ' ';
            case 7:
                return board[2][0] == ' ';
            case 8:
                return board[2][1] == ' ';
            case 9:
                return board[2][2] == ' ';
            default:
                return false;
        }
    }

    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }






}
