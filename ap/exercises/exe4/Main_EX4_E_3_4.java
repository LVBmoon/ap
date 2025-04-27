package ap.exercises.exe4;

public class Main_EX4_E_3_4 {
    public static void main(String[] args) {
        HallwayLight Lamp1  = new HallwayLight();
        System.out.println("Initial state: ");
        printState(Lamp1);

        System.out.println("Toggle switch one: ");
        Lamp1.toggleFirstSwitch();
        printState(Lamp1);

        System.out.println("Toggle switch two: ");
        Lamp1.toggleSecondSwitch();
        printState(Lamp1);
    }

    public static void printState(HallwayLight Lamp1){
        System.out.println("First Switch: " + Lamp1.getFirstSwitchState());
        System.out.println("Second Switch: " + Lamp1.getSecondSwitchState());
        System.out.println("Lamp State: " + Lamp1.getLampState());
    }
}

