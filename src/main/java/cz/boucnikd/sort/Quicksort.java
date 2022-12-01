package cz.boucnikd.sort;

import java.util.Comparator;

public class Quicksort {

    public static <T> void sort(T arr[], Comparator<T> comparator) {
        sort(arr, 0, arr.length - 1, comparator);
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, 0, arr.length - 1, Comparable::compareTo);
    }

    private static <T> void sort(T array[], int low, int high, Comparator<T> comparator) {
        if (low < high) {
            /* pi is partitioning index, array[pi] is
              now at right place */
            var pivot = partition(array, low, high, comparator);

            // Recursively sort elements before
            // partition and after partition
            sort(array, low, pivot - 1, comparator);
            sort(array, pivot + 1, high, comparator);
        }
    }

        /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */

    private static <T> int partition(T[] arr, int low, int high, Comparator<T> comparator) {
        T pivot = arr[high];
        int i = low; // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (comparator.compare(arr[j], pivot) <= 0) {
                swap(arr, i, j);
                i++;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        swap(arr, i, high);

        return i;
    }

    private static <T> void swap(T[] arr, int i, int j) {
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
