import java.util.Scanner;
    public class EvenOdd {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter a number: ");
            int number = sc.nextInt();
            printEvenOdd(number);
            printEvenNumbersOfArange(number);
            System.out.println();
            sc.close();
        }

        public static void printEvenOdd(int number) {
            if (number % 2 == 0) {
                System.out.println(number + " is even");
            }
            else {
                System.out.println(number + " is odd");
            }
        }

        public static void printEvenNumbersOfArange(int number) {
            for (int i = 2; i < number; i+=2) {
                System.out.print(i + " ");
            }
        }

    }

