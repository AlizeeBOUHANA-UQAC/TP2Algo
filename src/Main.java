import java.util.ArrayList;
import java.util.*;

public class Main {

    private static final boolean DISPLAY_ARRAYS = false;

    public static void main(String args[]){

        //Demaande à l'utilisateur une taille de tableau
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle taille de tableau voulez vous ?");
        Integer tailleList = Integer.parseInt(scan.nextLine());

        //Prépare la liste d'algorithmes de merge à tester
        ArrayList<IMergeAlgorithm> algorithms = new ArrayList<>();
        algorithms.add(new MergeSort());
        algorithms.add(new ParallelMergeSort());

        //Génère l'instance
        int[] array = InstanceGenerator.generateRandomArray(tailleList);

        if (DISPLAY_ARRAYS) {
            //Affiche l'instance si voulu
            System.out.println("Original array :\n");
            System.out.println(Arrays.toString(array));
        }

        //Pour chaque algorithme de Merge
        for (IMergeAlgorithm algorithm : algorithms) {

            //Créez une copie du tableau original. Sinon lors du second algorithme le tableau sera déjà trié.
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            System.out.println(algorithm.toString());
            //Top Chrono
            long start = System.currentTimeMillis();

            //Tri!
            algorithm.sort(arrayCopy);

            if (DISPLAY_ARRAYS)
                System.out.println(Arrays.toString(arrayCopy));

            System.out.println("Done in: " + (System.currentTimeMillis()-start) + " ms\n");

            if (!isArraySorted(arrayCopy)) {
                System.out.println("Tableau mal trié!");
            }
        }

    }

    private static boolean isArraySorted(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i+1])
                return false;
        }
        return true;
    }

    static ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        if(list.isEmpty() || list.size() == 1){
            return list;
        } else { //récursivité
            ListDivided ld = divide(list);
            return merge(mergeSort(ld.getList1()), mergeSort(ld.getList2()));
        }
    }

    private static ListDivided divide(ArrayList<Integer> list){
        int index = (Math.round(list.size()/2)); // valeur de l'index de la moitié de la liste
        return new ListDivided(new ArrayList<Integer>(list.subList(0, index)),
                new ArrayList<Integer>(list.subList(index, list.size())));
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2){
        if(list1.isEmpty()){
            return list2;
        } else if(list2.isEmpty()){
            return list1;
        } else if(list1.get(0) < list2.get(0)){ //premier element de tab1 plus petit
            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.add(list1.get(0)); //ajout 1er element car plus petit dans newList
            list1.remove(0); //suppression du 1er element deja mis dans newList
            newList.addAll(merge(list1, list2)); //recursivité
            return newList;
        } else if(list1.get(0) >= list2.get(0)){ //premier element de tab2 plus petit
            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.add(list2.get(0)); //ajout 1er element car plus petit dans newList
            list2.remove(0); //suppression du 1er element deja mis dans newList
            newList.addAll(merge(list1, list2)); //recursivité
            return newList;
        } else { //erreur
            return new ArrayList<Integer>();
        }
    }


}
