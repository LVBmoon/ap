package src.lvbmoon_selfTraining.quera;

public class question_2_magicMultiplicationTable {
    public static void main(String[] args) {
        for(int i = 1; i <= 10 ; i++){
            for(int j = 1 ; j <= 10 ; j++){
                if (i > j){
                    System.out.print((i*j) + " ");
                }
                else if (i == j){
                    System.out.print("0 ");
                }
                else{
                    System.out.print((-i*j) + " ");
                }
            }
            System.out.println();
        }
    }
}
