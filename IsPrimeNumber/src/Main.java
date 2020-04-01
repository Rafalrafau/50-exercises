public class Main {

    public static void main(String[] args) {

    }
    public static boolean isPrimeNumber(int number){
        for(int i = 2; i<number; i++){
            if(number % i ==0){
                return false;
            }
        }
        return true;
    }
}
