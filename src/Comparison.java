

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Comparison {


    public static void main(String args[]){
        int[] serial = new Random().ints(100_000_000).toArray();
        int[] parallel = Arrays.copyOf(serial, serial.length);

        MergeSort mergeSort = new MergeSort(serial);
        long start = System.currentTimeMillis();
        mergeSort.sort();
        System.out.println("Merge Sort done in: " + (System.currentTimeMillis()-start) + " ms");

        ParallelMergeSort sorter = new ParallelMergeSort(parallel);
        start = System.currentTimeMillis();
        sorter.sort();
        System.out.println("Parallel Merge Sort done in: " + (System.currentTimeMillis()-start) + " ms");

        assertArrayEquals(parallel, serial);
    }
}
