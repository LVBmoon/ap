import java.util.Scanner;
    public class EvenOdd {
        public static void main(String[] args) {
            System.out.println("Please enter a number: ");
            Scanner sc = new Scanner(System.in);
            int number = sc.nextInt();
            if (number % 2 == 0) {
                System.out.println(number + " is even");
            }
            else {
                System.out.println(number + " is odd");
            }
        }
    }

