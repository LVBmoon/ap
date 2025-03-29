
package ap.exercises.exe2;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main_EX2_PM_2_3 {
    private static int x = 1, y = 1;
    private static int k, c;
    private static char[][] points;
    private static int score = 0;
    private static final String SAVE_FILE = "save_game.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("_Welcome_\nDo you want to continue your previous game? (y/n): ");
        char answer = sc.next().toUpperCase().charAt(0);
        while(true){
            if (answer == 'Y' || answer == 'N') break;
            System.out.println("Invalid input.Try again answering with y/n: ");
            answer = sc.next().toUpperCase().charAt(0);
        }
        if (answer == 'Y') {
            if(!loadGame()){
                System.out.println("No saved game found. Starting new game...");
                initializeNewGame(sc);
            }
        }
        else{
            initializeNewGame(sc);
        }
        playGame(sc, rand);
    }

    private static void initializeNewGame(Scanner sc){
        System.out.print("Enter k (size of the board will be k*k & k must be bigger than 0): ");
        k = sc.nextInt();
        while (true){
            if (k > 0) break;
            System.out.println("Invalid input.Try again. ");
            k = sc.nextInt();
        }

        while(true){
            System.out.println("Enter c (number of points): ");
            c = sc.nextInt();
            if(c <= (k*k) && c > 0){
                break;
            }
            else{
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static void playGame(Scanner sc, Random rand){
        if(points == null) {
            points = createPoints(k, c, rand);
        }
        char[][] board = createBoard(k, x, y);
        printBoard(board);
        long start = System.currentTimeMillis();

        while(true) {
            int move = getMove(sc);
            if(move == 5) {
                saveGame(board, sc);
                System.out.println("Goodbye :(\nExiting...");
                return;
            }

            char[][] newBoard = makeMove(board, move);

            if(newBoard != null) {
                board = newBoard;
                if(points[x][y] == '.') {
                    points[x][y] = ' ';
                    score++;
                    if(score == c) {
                        printWinMessage(start);
                        new File(SAVE_FILE).delete();
                        return;
                    }
                }
            }

            printBoard(board);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }

    private static void printWinMessage(long startTime) {
        System.out.println("\nYOU WON! :)");
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - startTime;
        System.out.println("Elapsed time: " + timeElapsed/1000 + " seconds");
    }

    private static boolean loadGame() {
        try (Scanner scanner = new Scanner(new File(SAVE_FILE))) {
            k = scanner.nextInt();
            c = scanner.nextInt();
            x = scanner.nextInt();
            y = scanner.nextInt();
            score = scanner.nextInt();
            scanner.nextLine(); // consume newline

            points = new char[k+2][k+2];
            for(int i = 0; i < points.length; i++) {
                String line = scanner.nextLine();
                points[i] = line.toCharArray();
            }
            System.out.println("Game loaded successfully!");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void saveGame(char[][] board, Scanner sc) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write(k + "\n" + c + "\n" + x + "\n" + y + "\n" + score + "\n");

            for(char[] row : points) {
                writer.write(row);
                writer.write("\n");
            }
            System.out.println("Game saved successfully!");
        }
        catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
            System.out.println("Do you want to exit anyway? (y/n): ");
            if(sc.next().equalsIgnoreCase("y")) {
                System.exit(0);
            }
        }
    }

    private static int getMove(Scanner sc) {
        while(true) {
            System.out.println("Please enter your move <up(0)-right(1)-left(2)-down(3)-save&exit(5)>: ");
            int move = sc.nextInt();
            if((move >= 0 && move <= 3) || move == 5) {
                switch(move) {
                    case 0: System.out.println("UP"); break;
                    case 1: System.out.println("RIGHT"); break;
                    case 2: System.out.println("LEFT"); break;
                    case 3: System.out.println("DOWN"); break;
                    case 5: System.out.println("SAVE & EXIT"); break;
                }
                return move;
            }
            System.out.println("Invalid move. Try again.");
        }
    }

    private static char[][] createPoints(int k, int c, Random rand) {
        char[][] points = new char[k+2][k+2];
        int filled = 0;

        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points[i].length; j++) {
                points[i][j] = ' ';
            }
        }
        while(filled < c) {
            int m = 1 + rand.nextInt(k);
            int n = 1 + rand.nextInt(k);

            if (m == 1 && n == 1) {
                continue;
            }
            if (points[m][n] != '.') {
                points[m][n] = '.';
                filled++;
            }
        }
        return points;
    }

    private static char[][] createBoard(int k, int x, int y) {
        char[][] arr = new char[k+2][k+2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == 0 || i == arr.length-1 || j == 0 || j == arr[i].length-1) {
                    arr[i][j] = '*';
                }
                else {
                    arr[i][j] = (points[i][j] == '.') ? '.' : ' ';
                }
            }
        }
        arr[x][y] = 'X';
        return arr;
    }

    private static char[][] makeMove(char[][] arr, int move) {
        int newX = x;
        int newY = y;

        switch (move) {
            case 0: newX--; break;
            case 1: newY++; break;
            case 2: newY--; break;
            case 3: newX++; break;
        }

        if(arr[newX][newY] == '*') {
            System.out.println("Hitting the game wall!!");
            return null;
        }

        arr[x][y] = ' ';
        arr[newX][newY] = 'X';
        x = newX;
        y = newY;
        return arr;
    }

    private static void printBoard(char[][] rectangle) {
        for (char[] row : rectangle) {
            for (char column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        System.out.println("Score: " + score + "/" + c);
        System.out.println();
    }
}