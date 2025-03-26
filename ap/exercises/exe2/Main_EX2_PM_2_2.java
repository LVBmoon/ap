package ap.exercises.exe2;

import java.util.Scanner;
import java.util.Random;

public class Main_EX2_PM_2_2 {
    private static int x = 1, y = 1;
    private static int k, c;
    private static char[][] points;
    private static int score = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter k: ");
        k = sc.nextInt();

        while(true){
            System.out.println("Enter c: ");
            c = sc.nextInt();
            if(c <= (k*k) && c >= 0){
                break;
            }
            else{
                System.out.println("Invalid input. Try again.");
            }
        }

        points = createPoints(k, c, rand);
        char[][] rect = creating(k, x, y);
        print(rect);
        long start = System.currentTimeMillis();

        while(true) {
            int move = printMove(sc);
            char[][] newRect = makeMove(rect, move);

            if(newRect != null) {
                rect = newRect;
                if(points[x][y] == '.') {
                    points[x][y] = ' ';
                    score++;
                    if(score == c) {
                        System.out.println("\nYOU WON! :)");
                        long finish = System.currentTimeMillis();
                        long timeElapsed = finish - start;
                        System.out.println("Elapsed time: " + timeElapsed/1000 + " seconds");
                        break;
                    }
                }
            }

            print(rect);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
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

    private static char[][] creating(int k, int x, int y) {
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

    private static int printMove(Scanner sc) {
        while(true) {
            System.out.println("Please enter your move <up(0)-right(1)-left(2)-down(3)>: ");
            int move = sc.nextInt();
            if(move >= 0 && move <= 3){
                switch(move){
                    case 0:
                        System.out.println("UP");
                        break;
                    case 1:
                        System.out.println("RIGHT");
                        break;
                    case 2:
                        System.out.println("LEFT");
                        break;
                    case 3:
                        System.out.println("DOWN");
                        break;
                }
            return move;
            }
            System.out.println("Invalid move. Try again.");
        }
    }

    private static char[][] makeMove(char[][] arr, int move) {
        int newX = x;
        int newY = y;

        switch (move) {
            case 0:
                newX--;
                break;
            case 1:
                newY++;
                break;
            case 2:
                newY--;
                break;
            case 3:
                newX++;
                break;
        }

        if(arr[newX][newY] == '*') {
            System.out.println("hitting the game wall!!");
            return null;
        }

        arr[x][y] = ' ';
        arr[newX][newY] = 'X';
        x = newX;
        y = newY;
        return arr;
        }


    private static void print(char[][] rectangle) {
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