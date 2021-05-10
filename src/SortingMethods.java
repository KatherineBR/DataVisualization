public class SortingMethods {

    public SortingMethods() {
    }

    public static void sort(Record[] records){
        //SortingMethods.selectionSort(records);
        //SortingMethods.insertionSort(records);
        SortingMethods.mergeSort(records);
    }

    private static void selectionSort(Record[] records) {
        for (int curIndex = 0; curIndex < records.length - 1; curIndex++) {
            int minIndex = findMin(records, curIndex);
            swap(records, curIndex, minIndex);
        }
    }

    private static int findMin(Record[] records, int startingIndex) {
        int minIndex = startingIndex;

        for (int i = minIndex; i < records.length; i++) {
            if (records[i].getCountry().compareToIgnoreCase(records[minIndex].getCountry()) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void swap(Record[] records, int x, int y) {
        Record temp = records[x];
        records[x] = records[y];
        records[y] = temp;
    }

    private static void insertionSort(Record[] records) {
        for (int i = 1; i < records.length; i++) {
            Record cur = records[i];
            int curIndex = i - 1;
            while (curIndex >= 0 && records[curIndex].getCountry().compareToIgnoreCase(cur.getCountry()) > 0) {
                // Shift the value at curIndex to the right one place
                records[curIndex + 1] = records[curIndex];
                curIndex--;
            }

            // Put this string in the proper location
            records[curIndex + 1] = cur;
        }
    }

    private static void mergeSort(Record[] records) {
        if (records.length <= 1) {
            return;
        }

        Record[] firstHalf = new Record[records.length / 2];
        Record[] secondHalf = new Record[records.length - firstHalf.length];
        System.arraycopy(records, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(records, firstHalf.length, secondHalf, 0, secondHalf.length);

        SortingMethods.mergeSort(firstHalf);
        SortingMethods.mergeSort(secondHalf);
        SortingMethods.merge(firstHalf, secondHalf, records);
    }

    private static void merge(Record[] firstHalf, Record[] secondHalf, Record[] result) {
        int firstIndex = 0;
        int secondIndex = 0;
        int resultIndex = 0;
        while (firstIndex < firstHalf.length && secondIndex < secondHalf.length) {
            if (firstHalf[firstIndex].getCountry().compareToIgnoreCase(secondHalf[secondIndex].getCountry()) < 0) {
                result[resultIndex] = firstHalf[firstIndex];
                firstIndex++;
            } else {
                result[resultIndex] = secondHalf[secondIndex];
                secondIndex++;
            }
            resultIndex++;
        }
        System.arraycopy(firstHalf, firstIndex, result, resultIndex, firstHalf.length - firstIndex);
        System.arraycopy(secondHalf, secondIndex, result, resultIndex, secondHalf.length - secondIndex);
    }
}
