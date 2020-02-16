import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public static int[] generateRandomArray(int taille){
        int[] array = new int[taille];
        Random r = new Random();
        for(int i = 0 ; i<taille ; i++){
            array[i] = r.nextInt((100) + 1); //nb aléatoire entre 0 et 100
        }
        return array;
    }

    public static ArrayList<Integer> generateRandomNumberList(int taille) {

        //Generate random list

        ArrayList<Integer> possibleNumbers = new ArrayList<>();
        for(int i = 0 ; i<taille ; i++){
            possibleNumbers.add(i + 1);
        }

        Collections.shuffle(possibleNumbers);

        return possibleNumbers;
    }

    public static int[] generateRandomNumberArray(int taille) {

        //Generate random list

        ArrayList<Integer> possibleNumbers = generateRandomNumberList(taille);
        int[] array = new int[taille];
        for(int i = 0 ; i<taille ; i++){
            //Remove les instances depuis la fin sinon toute la liste est éditée.
            int number = possibleNumbers.remove(possibleNumbers.size()-1);
            array[i] = number;
        }
        return array;
    }

    public static int[] generateSortedArray(int taille){
        int[] array = new int[taille];
        for(int i = 0 ; i<taille ; i++){
            array[i] = i + 1;
        }
        return array;
    }

    public static int[] generateReversedSortedArray(int taille){
        int[] array = new int[taille];
        for(int i = 0 ; i<taille ; i++){
            array[i] = taille - i;
        }
        return array;
    }


}
