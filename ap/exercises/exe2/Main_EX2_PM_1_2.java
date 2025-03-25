package ap.exercises.exe2;

import java.util.Scanner;

public class Main_EX2_PM_1_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter k: ");
        int k = sc.nextInt();

        // Approach 1:
        char[][] rect = methodOneOfCreating(k);
        print(rect);

        // Approach 2:
        // char[][] rect = methodTwoOfCreating(k);
        // printRectangle(rect2);
        }

    private static char[][] methodOneOfCreating(int k) {
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
        return arr;
    }
    /*
    private static char[][] methodTwoOfCreating(int k) {
        char[][] arr = new char[k+2][k+2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (i == 0  i == arr.length-1  j == 0 || j == arr[i].length-1) ? '*' : ' ';
            }
        }
        return arr;
    }
    */

    // Shared print method
    private static void print(char[][] rectangle) {
        for (char[] row : rectangle) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }
}
