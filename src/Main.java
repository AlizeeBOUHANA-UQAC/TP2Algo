import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

public class Main {

    private static final boolean DISPLAY_ARRAYS = false;

    public static void main(String args[]){

        //Demande à l'utilisateur une taille de tableau
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle taille de tableau voulez vous ?");
        Integer tailleList = Integer.parseInt(scan.nextLine());

        //Génère la/les instances

        HashMap<int[], String> instances = new HashMap<>();

        instances.put(InstanceGenerator.generateRandomNumberArray(tailleList),
                "Aléatoire, n = " + tailleList);
        instances.put(InstanceGenerator.generateSortedArray(tailleList),
                "Déjà trié, n = " + tailleList);
        instances.put(InstanceGenerator.generateReversedSortedArray(tailleList),
                "Trié en ordre inverse, n = " + tailleList);

        System.out.println("Fin Génération instance...");

        for (Map.Entry<int[], String> entry : instances.entrySet()) {
            int[] instance = entry.getKey();
            String nom = entry.getValue();

            System.out.println("\nInstance : " + nom);


            if (DISPLAY_ARRAYS) {
                //Affiche l'instance si voulu
                System.out.println("Original array :\n");
                System.out.println(Arrays.toString(instance));
                //System.out.println(copyAsList(instance));
            }

            //Prépare la liste d'algorithmes de merge à tester

            //Créez des copies du tableau original. Sinon lors du second algorithme le tableau sera déjà trié.
            ArrayList<IMergeAlgorithm> algorithms = new ArrayList<>();
            algorithms.add(new MergeSort(Arrays.copyOf(instance, instance.length)));
            algorithms.add(new ParallelMergeSort(Arrays.copyOf(instance, instance.length)));
            algorithms.add(new MergeSortList(copyAsList(instance)));



            //Pour chaque algorithme de Merge
            for (IMergeAlgorithm algorithm : algorithms) {

                System.out.println(algorithm.toString());
                //Top Chrono
                long start = System.currentTimeMillis();

                //Tri!
                algorithm.sort();


                System.out.println("\tDone in: " + (System.currentTimeMillis()-start) + " ms");

                if (DISPLAY_ARRAYS) {
                    algorithm.displayResult();
                }

            }
        }

    }

    private static ArrayList<Integer> copyAsList(int[] numbers) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }

        return list;
    }

    private static boolean isArraySorted(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i+1])
                return false;
        }
        return true;
    }




}
