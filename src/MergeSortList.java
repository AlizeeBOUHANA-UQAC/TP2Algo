import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortList implements IMergeAlgorithm {

    private ArrayList<Integer> numbers;

    private boolean countChoices = true;
    private int[] counts = new int[3];

    public MergeSortList(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void sort() {
        numbers = mergeSort(numbers);

        /*
        if (!isListSorted()) {
            System.out.println("Liste mal triée!");
            System.out.println(numbers);
        } */

        if (countChoices) {
            System.out.println("\tInsertion Début : " + counts[0]);
            System.out.println("\tInsertion Milieu : " + counts[2]);
            System.out.println("\tInsertion Fin : " + counts[1]);
        }

    }

    @Override
    public void displayResult() {
        System.out.println(numbers);
    }

    private ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        if ( list.isEmpty() || list.size() == 1 ) {
            return list;
        }
        else {
            //récursivité
            ListDivided ld = divide(list);

            return merge(
                    mergeSort(ld.getList1()),
                    mergeSort(ld.getList2())
            );
        }
    }

    private ListDivided divide(ArrayList<Integer> list){
        int index = (Math.round(list.size()/2)); // valeur de l'index de la moitié de la liste
        return new ListDivided(new ArrayList<Integer>(list.subList(0, index)),
                new ArrayList<Integer>(list.subList(index, list.size())));
    }

    private ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2){

        if( list1.isEmpty() ){
            return list2;
        }
        else if (list2.isEmpty() ) {
            return list1;
        }
        else {

            if (list2.size() > list1.size()) {
                //On inverse l'autre des deux listes pour toujours insérer dans la plus grande
                ArrayList<Integer> temp = list1;
                list1 = list2;
                list2 = temp;
            }

            //On inverse la liste pour prendre les éléments depuis la fin.
            Collections.reverse(list2);
            int i = 0; //index list
            while (!list2.isEmpty()) {

                Integer elm = list2.remove(list2.size()-1);
                //Si bien placé en début de liste
                if (i == 0 && list1.get(0) > elm) {
                    list1.add(i, elm);
                    if (countChoices)
                        counts[0]++;
                }
                //Si on est au bout de la liste
                else if (i == list1.size()) {
                    list1.add(elm);
                    if (countChoices)
                        counts[1]++;
                }
                //Sinon on avance dans les index jusqu'à être à une bonne place.
                else {
                    if (i == 0)
                        i++;
                    if (countChoices)
                        counts[2]++;

                    while (i < list1.size() && !(list1.get(i-1) <= elm && list1.get(i) >= elm )) {
                        i++;
                    }
                    list1.add(i, elm);
                }
                i++;
            }

            return list1;
        }
    }

    private boolean isListSorted() {
        for (int i = 0; i < numbers.size() - 1; i++ ) {

            //If not correctly sorted
            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "MergeSortList";
    }
}
