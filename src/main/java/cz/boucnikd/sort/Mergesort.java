package cz.boucnikd.sort;

public class Mergesort {
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int start, int end) {

        if (start < end) {

            var mid = (start + end) / 2;

            sort(arr, start, mid);
            sort(arr, mid + 1, end);

            merge(arr, start, mid, end);
        }
    }

    private void merge(int[] array, int firstPartitionStart, int mid, int end) {
        int secondPartitionStart = mid + 1;

        // If the direct merge is already sorted
        if (array[mid] <= array[secondPartitionStart]) {
            return;
        }

        // Two pointers to maintain firstPartitionStart
        // of both arrays to merge
        while (firstPartitionStart <= mid && secondPartitionStart <= end) {

            // If element 1 is in right place
            if (array[firstPartitionStart] <= array[secondPartitionStart]) {
                firstPartitionStart++;
            } else {
                inertElementToRight(array, firstPartitionStart, secondPartitionStart);

                // Update all the pointers
                firstPartitionStart++;
                mid++;
                secondPartitionStart++;
            }
        }
    }

    private void inertElementToRight(int[] arr, int firstPartitionStart, int secondPartitionStart) {
        int value = arr[secondPartitionStart];

        // Shift all the elements between element 1
        // element 2, right by 1.
        shiftElementsToRight(arr, firstPartitionStart, secondPartitionStart);
        arr[firstPartitionStart] = value;
    }

    private static void shiftElementsToRight(int[] arr, int firstPartitionStart, int index) {
        while (index != firstPartitionStart) {
            arr[index] = arr[index - 1];
            index--;
        }
    }
}
