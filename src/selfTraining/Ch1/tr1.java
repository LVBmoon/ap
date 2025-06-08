package src.selfTraining.Ch1;

import javax.swing.JOptionPane;
// For ex1.18:
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;


public class tr1 {
    public static void main(String[] args) {
        System.out.println("ex1.1 :");
        ex1_1();
        System.out.println("----\nex1.2 :");
        ex1_2();
        System.out.println("----\nex1.3 :");
        ex1_3();
        System.out.println("----\nex1.15 :");
        ex1_15();
        System.out.println("----\nex1.16 :");
        ex1_16();
        System.out.println("----\nex1.17 :");
        ex1_17();
        System.out.println("----\nex1.18 :");
        ex1_18();
    }

    public static void ex1_1(){
        System.out.println("Hello World!");
    }

    public static void ex1_2() {
        int sum = 0;
        for (int i = 1; i < 11; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    public static void ex1_3() {
        int result = 1;
        for (int i = 1; i < 11; i++) {
            result *= i;
        }
        System.out.println(result);
    }

    public static void ex1_15() {
        JOptionPane.showMessageDialog(null, "Hello, LVB!");
    }

    public static void ex1_16() {
        String name = JOptionPane.showInputDialog("What is your name?");
        if (name != null && !name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hello dear " + name + "!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Hello dear unknown!");
        }
    }

    public static void ex1_17() {
        String name = JOptionPane.showInputDialog("What is your name?");
        if (name != null && !name.isEmpty()) {
            JOptionPane.showInputDialog("Hello dear " + name + "! My name is LVB! What would you like me to do?");
            JOptionPane.showMessageDialog(null, "I'm sorry," + name +". I'm afraid I can't do that.");
        }
        else {
            JOptionPane.showInputDialog("Hello dear unknown! My name is LVB! What would you like me to do?");
            JOptionPane.showMessageDialog(null, "I'm sorry,dear unknown. I'm afraid I can't do that.");
        }
    }

    private static void ex1_18() {
        URL imageLocation = null;
        try {
            imageLocation = new URL("https://avatars.githubusercontent.com/u/178637798?s=96&v=4");
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        JOptionPane.showMessageDialog(null, "Hello dear LVB!", "Greeting",
                JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
    }
}
