package ap.exercises.exe4;

public class HallwayLight{
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

    public void setFirstSwitchState(int firstSwitch){
        if(firstSwitch == 0 || firstSwitch == 1) {
            this.firstSwitch = firstSwitch;
        }
    }
    public void setSecondSwitchState(int secondSwitch){
        if(secondSwitch == 0 || secondSwitch == 1) {
            this.secondSwitch = secondSwitch;
        }
    }

    public int getLampState(){
        if(firstSwitch == secondSwitch){
            return 0;
        }
        else{
            return 1;
        }
    }

    public void toggleFirstSwitch(){firstSwitch = 1  - firstSwitch;}
    public void toggleSecondSwitch(){secondSwitch = 1 - secondSwitch;}
}
