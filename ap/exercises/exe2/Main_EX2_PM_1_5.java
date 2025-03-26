package ap.exercises.exe2;

import java.util.Scanner;
import java.util.Random;

public class Main_EX2_PM_1_5 {
    private static int x = 1;
    private static int y = 1;
    private static int k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter k: ");
        k = sc.nextInt();
        char[][] rect = creating(k, x, y);
        print(rect);

        while(true) {
            int move = printMove(rand);
            char[][] newRect = makeMove(rect, move);

            if(newRect != null) {
                rect = newRect;
            }

            print(rect);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
        }
    }

    private static char[][] creating(int k, int x, int y) {
        char[][] arr = new char[k+2][k+2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == 0 || i == arr.length-1 || j == 0 || j == arr[i].length-1) {
                    arr[i][j] = '*';
                }
                else {
                    arr[i][j] = ' ';
                }
            }
        }
        arr[x][y] = 'X';
        return arr;
    }

    private static int printMove(Random rand) {
        int move = rand.nextInt(4);
        switch (move) {
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
        else {
            char[][] newArr = creating(k, newX, newY);
            newArr[x][y] = ' ';
            x = newX;
            y = newY;
            return newArr;
        }
    }

    private static void print(char[][] rectangle) {
        for (char[] row : rectangle) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
        System.out.println();
    }
}

