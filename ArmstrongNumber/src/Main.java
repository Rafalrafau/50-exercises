public class Main {

    public static void main(String[] args) {

        System.out.println(isArmstrong(153));
    }

    public static boolean isArmstrong(int number){
        int originalNumber, remainder, result = 0, n = 0;
        originalNumber = number;

        for (;originalNumber != 0; originalNumber /= 10){
            ++n;
        }
        originalNumber = number;

        for (;originalNumber != 0; originalNumber /= 10)
        {
            remainder = originalNumber % 10;
            result += Math.pow(remainder, n);
        }

        if(result == number) {
            System.out.println(number + " is an Armstrong number.");
            return true;
        } else{
            System.out.println(number + " is not an Armstrong number.");
            return false;
        }
    }
}