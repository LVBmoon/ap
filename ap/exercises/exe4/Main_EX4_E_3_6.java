package ap.exercises.exe4;

public class Main_EX4_E_3_6 {
    public static void main(String[] args) {
    HallwayLights newLamp = new HallwayLights();

        newLamp.printState();
        newLamp.toggleSwitch(1);
        newLamp.printState();

        newLamp.toggleSwitch(2);
        newLamp.printState();

        newLamp.toggleSwitch(1);
        newLamp.printState();
    }
}

class HallwayLights{
    private int[] switches;

    public HallwayLights() {
        switches = new int[2];
    }

    public int getSwitchState(int switchNum){
        if (switchNum == 1 || switchNum == 2) {
            return switches[switchNum - 1];
        }
        else {
            throw new IllegalArgumentException("The switch number must be 1 or 2.");
        }
    }

    public int getLampState(){
        return (switches[0] != switches[1]) ? 1 : 0;
    }

    public void toggleSwitch(int switchNum){
        if (switchNum == 1 || switchNum == 2) {
            switches[switchNum - 1] = 1 - switches[switchNum - 1];
        }
         else {
            throw new IllegalArgumentException("The switch number must be 1 or 2.");
        }
    }

    public void printState() {
        System.out.println("First Switch: " + getSwitchState(1) + "\nSecond Switch: "
                + getSwitchState(2) + "\nLamp State: " + getLampState());
        System.out.println("---------------------------");
    }
}

