/*The question:
Write programs with loops that compute
a) The sum of all even numbers between 2 and 100 (inclusive).
e) The sum of all odd digits of an input. (For example, if the input is 32677, the
sum would be 3 + 7 + 7 = 17.)*/
import java.util.Scanner;

public class Exercise6_1_a_and_e {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //a:
        for (int i = 2; i <= 100; i += 2) {
            System.out.print(i + " ");
        }

        //e:
        System.out.println();
        int result = 0;
        System.out.println("Please enter a number: ");
        int number = scanner.nextInt();
        while (number != 0) {
            int remain = number % 10;
            if (remain % 2 == 1) result += remain ;
            number = number / 10;
        }
        System.out.println(result);
    }
}
