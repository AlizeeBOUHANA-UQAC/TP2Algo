import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle taille de tableau voulez vous ?");
        Integer tailleList = Integer.parseInt(scan.nextLine());

        ArrayList<Integer> list = InstanceGenerator.generator(tailleList);
        System.out.println(list.toString());
        long start = System.currentTimeMillis();
        System.out.println(mergeSort(list));
        System.out.println("Merge Sort done in: " + (System.currentTimeMillis()-start) + " ms");
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
