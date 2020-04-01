public class Main {


    public static void main(String[] args) {

        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(10));

    }


    public static int fibonacci(int i){

        if(i == 0){
            return 0;
        }else if(i ==1){
            return 1;
        }else{
            return fibonacci(i-1)+fibonacci(i-2);
        }
    }

}
