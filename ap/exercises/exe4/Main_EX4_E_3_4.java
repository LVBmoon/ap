package ap.exercises.exe4;

public class Main_EX4_E_3_4 {
    public static void main(String[] args) {
        HallwayLight Lamp1  = new HallwayLight();
        System.out.println("Initial state: \nFirst Switch: " + Lamp1.getFirstSwitchState() +
                "\nSecond Switch: " + Lamp1.getSecondSwitchState() + "\nLamp State: " + Lamp1.getLampState());
        Lamp1.toggleFirstSwitch();
        System.out.println("\nToggle switch one: \nFirst Switch: " + Lamp1.getFirstSwitchState() +
                "\nSecond Switch: " + Lamp1.getSecondSwitchState() + "\nLamp State: " + Lamp1.getLampState());
        Lamp1.toggleSecondSwitch();
        System.out.println("\nToggle switch two: \nFirst Switch: " + Lamp1.getFirstSwitchState() +
                "\nSecond Switch: " + Lamp1.getSecondSwitchState() + "\nLamp State: " + Lamp1.getLampState());
    }
}

class HallwayLight{
    private int firstSwitch;
    private int secondSwitch;

    public HallwayLight() {
        this.firstSwitch = 0;
        this.secondSwitch = 0;
    }

    public int getFirstSwitchState(){ //0 for down & 1 for up
        return firstSwitch;
    }
    public int getSecondSwitchState(){
        return secondSwitch;
    }
    public int getLampState(){ // 0 for off and 1 for on
        if(firstSwitch == secondSwitch){
            return 0;
        }
        else{
            return 1;
        }
    }

    public void toggleFirstSwitch(){
//        firstSwitch = firstSwitch == 0 ? 1 : 0;
        firstSwitch = 1  - firstSwitch;
    }
    public void toggleSecondSwitch(){
//        secondSwitch = secondSwitch == 0 ? 1 : 0;
        secondSwitch = 1 - secondSwitch;
    }
}
