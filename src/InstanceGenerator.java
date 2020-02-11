import java.util.ArrayList;
import java.util.Random;

public class InstanceGenerator {

    public static ArrayList<Integer> generator(int taille){
        ArrayList<Integer> listReturn = new ArrayList<>();
        for(int i = 0 ; i<taille ; i++){
            Random r = new Random();
            listReturn.add(r.nextInt((100) + 1)); //nb aléatoire entre 0 et 100
        }
        return listReturn;
    }


}
