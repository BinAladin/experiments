package cz.boucnikd.algo;

public class BinarySearch {
    public int search(int[] array, int searchedValue) {
        var low = 0;
        var high = array.length;

        var resultIndex = -1;
        while (low <= high) {
            var currentIndex = (high + low) / 2;
            var currentValue = array[currentIndex];

            if (currentValue == searchedValue) {
                resultIndex = currentIndex;
                break;
            } else if (currentValue < searchedValue) {
                low = currentIndex + 1;
            } else if (currentValue > searchedValue) {
                high = currentIndex - 1;
            }
        }

        return resultIndex;
    }
}
