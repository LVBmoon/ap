package ap.exercises.exe2;

import java.util.Scanner;
import java.util.Random;

public class Main_EX2_PM_1_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter k: ");
        int k = sc.nextInt();
        int c;
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

        char[][] rect = creating(k,c,rand);
        print(rect);
    }

    private static char[][] creating(int k, int c, Random rand) {
        char[][] arr = new char[k+2][k+2];
        int filled = 0;
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
        while(filled < c) {
            int x = 1 + rand.nextInt(k);
            int y = 1 + rand.nextInt(k);
            if (arr[x][y] == ' ') {
                arr[x][y] = '.';
                filled++;
            }
        }
        return arr;
    }

    private static void print(char[][] rectangle) {
        for (char[] row : rectangle) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }
}
