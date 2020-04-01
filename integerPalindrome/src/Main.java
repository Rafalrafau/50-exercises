public class Main {

    public static void main(String[] args) {

        int number = 123215;

        System.out.println(integerPalindrome(number));
    }


    public static boolean integerPalindrome(int number){

        String numberString = Integer.toString(number);
        StringBuilder sb = new StringBuilder(numberString);
        String reversedString = sb.reverse().toString();

        if(numberString.equals(reversedString)){
            return true;
        }
        return false;
    }
}
