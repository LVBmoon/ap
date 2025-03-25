package ap.exercises.exe2;

import java.util.Scanner;

public class Main_EX2_PM_1_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean end = true;
        while (end){
            System.out.println("Please enter one of the following letters(w, a, s, d, q): ");
            char letter = sc.next().charAt(0);
            switch (letter){
                case 'w':
                    System.out.println("UP");
                    break;
                case 'a':
                    System.out.println("LEFT");
                    break;
                case 's':
                    System.out.println("DOWN");
                    break;
                case 'd':
                    System.out.println("RIGHT");
                    break;
                case 'q':
                    System.out.println("EXIT");
                    end = false;
                    break;
                default:
                    System.out.println("WARNING");
                    break;
            }
        }
        sc.close();
    }
}
