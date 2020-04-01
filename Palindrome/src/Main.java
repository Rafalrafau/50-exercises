public class Main {

    public static void main(String[] args) {


        String name = "owo";

        System.out.println(reverseString(name));
        System.out.println(isPalindrome(name));
    }


    public static boolean isPalindrome(String string){

        String reversedString = reverseString(string);
        return string.equals(reversedString);
    }

    public static String reverseString(String string){

        char[] temp = new char[string.length()];

        for(int i=0; i<string.length(); i++){
            temp[i] = string.charAt(string.length()-1-i);
        }
        return String.valueOf(temp);
    }
}
