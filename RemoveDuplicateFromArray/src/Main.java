import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) {

        ArrayList arr = new ArrayList<>();

        arr.add(2);
        arr.add(3);
        arr.add(3);
        arr.add(4);

        ArrayList removedDuplicate = removeDuplicate(arr);

        for(int i =0; i<removedDuplicate.size(); i++){
            System.out.println((i+1) + ": " + removedDuplicate.get(i));
        }
    }

    public static ArrayList removeDuplicate(ArrayList list){

        LinkedHashSet set = new LinkedHashSet(list);

        list.clear();
        list.addAll(set);

        return list;
    }
}
