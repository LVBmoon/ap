package ap.exercises.exe4;

public class Main_EX4_E_3_5 {
    public static void main(String[] args) {
        HallwayLight testLamp = new HallwayLight();

        switchTest(testLamp,0,0);
        switchTest(testLamp,0,1);
        switchTest(testLamp,1,0);
        switchTest(testLamp,1,1);
    }

    private static void switchTest(HallwayLight testLamp,int firstSwitchState,int secondSwitchState){
        testLamp.setFirstSwitchState(firstSwitchState);
        testLamp.setSecondSwitchState(secondSwitchState);

        int expectedLampState = (firstSwitchState != secondSwitchState) ? 1 : 0;
        printResult(testLamp, firstSwitchState, secondSwitchState, expectedLampState);
    }

    private static void printResult(HallwayLight testLamp,int firstSwitchState,int secondSwitchState,int expectedLampState){
        System.out.println("first Switch state: " + firstSwitchState + "\nsecond Switch state: " + secondSwitchState);
        System.out.println("Expected lamp state: " + expectedLampState);
        System.out.println("Actual lamp state: " + testLamp.getLampState());
        System.out.println("----------------------------");
    }
}

