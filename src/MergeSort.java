import java.util.Arrays;

public class MergeSort implements IMergeAlgorithm {


    private int[] numbers;

    public MergeSort(int[] numbers) {
        this.numbers = numbers;

    }
    public void sort() {
        mergeSort(numbers, 0, numbers.length-1);
    }

    void mergeSort(int[] numbers, int from, int to) {
        if (from < to) {
            int mid = from + Math.floorDiv(to-from, 2);
            mergeSort(numbers, from, mid);
            mergeSort(numbers, mid+1, to);
            merge(numbers, from, mid, to);
        }

    }

    void merge(int[] numbers, int from, int mid, int to) {
        int[] left = Arrays.copyOfRange(numbers, from, mid+1);
        int[] right = Arrays.copyOfRange(numbers, mid+1, to+1);

        int li = 0, ri = 0;
        while (li < left.length && ri < right.length) {
            if (left[li] < right[ri]) {
                numbers[from++] = left[li++];
            } else {
                numbers[from++] = right[ri++];
            }
        }

        while (li < left.length) {
            numbers[from++] = left[li++];
        }

        while (ri < right.length) {
            numbers[from++] = right[ri++];
        }
    }

    @Override
    public String toString() {
        return "Merge Sort";
    }
}
