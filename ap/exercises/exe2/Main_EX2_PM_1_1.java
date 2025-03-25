package ap.exercises.exe2;

import java.util.Scanner;

public class Main_EX2_PM_1_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int k = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < k+2; i++) {
            System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < k ; i++) {
            System.out.print("*");
            for (int j = 0; j < k ; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
        for (int i = 0; i < k+2; i++) {
            System.out.print("*");
        }
    }
}

/*
second way:
public class Main_EX2_PM_1_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter k: ");
        int k = sc.nextInt();
        for (int i = 1; i <= k+2 ; i++) {
            for (int j = 1; j <= k+2 ; j++) {
                if (i == 1 || i == k+2){
                    System.out.print("*");
                }
                else {
                    if(j == 1 || j == k+2){
                        System.out.print("*");
                    }
                    else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

}*/