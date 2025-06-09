package src.selfTraining.Ch2;


import java.awt.Rectangle;

public class Ch_2_InTextExamples {
    public static void main(String[] args) {
        System.out.println("2.3 : ");
        exampleCodesInText2_3();
        System.out.println("----\n2.4 :");
        exampleCodesInText2_4();
        System.out.println("----\n2.7 :");
        exampleCodesInText2_7();
    }

    public static void exampleCodesInText2_3() {
        String greeting = "Hello, World!";
        System.out.println(greeting.length());
        //--
        String river = "Mississippi";
        System.out.println(river.toUpperCase());
        //--
        System.out.println(river.replace("issipp", "our"));
        //--
        System.out.println(greeting.replace("l", "3"));
    }

    public static void exampleCodesInText2_4() {
        Rectangle box = new Rectangle(5, 10, 20, 30);
        System.out.println(new Rectangle());
    }

    public static void exampleCodesInText2_7() {
        Rectangle box = new Rectangle(5, 10, 20, 30);
        // Move the rectangle
        box.translate(15, 25);
        // Print information about the moved rectangle
        System.out.print("x: " + box.getX() + "\nExpected: 20");
        System.out.print("y: " + box.getY() + "Expected: 35");
    }
}

/*
http://docs.oracle.com/javase/10/docs/api/index.html

in JdK10+ :
var milesPerGallon = 22.5;
var greeting = "Hello";
*/