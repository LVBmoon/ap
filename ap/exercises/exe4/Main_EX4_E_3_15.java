package ap.exercises.exe4;

public class Main_EX4_E_3_15 {
    public static void main(String[] args) {
        Letter letter = new Letter("Mr.Rahmani", "LVB");
        letter.addLine("You must try harder.");
        letter.addLine("I wish you all the best.");

        System.out.println(letter.getText());
    }
}