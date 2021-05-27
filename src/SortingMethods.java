public class SortingMethods {

    public SortingMethods() {
    }

    public static void sort(Record[] records){
        //SortingMethods.selectionSort(records);
        //SortingMethods.insertionSort(records);
        SortingMethods.mergeSort(records);
    }

    private static void selectionSort(Record[] records) { //sorting by date
        Record temp;
        int min = 0;
        int low;
        for (int t = 0; t < records.length; t++) {
            low = t;
            for (int i = min; i < records.length; i++) {
                if (records[i].getDate() < records[low].getDate()) {
                    low = i;
                }
            }
            //now last has the new lowest index
            temp = records[t]; //saving t
            records[t] = records[low]; //inserting low into t
            records[low] = temp; //inserting t into low's initial spot
            min++; //start one later on next search
        }
    }

    private static void insertionSort(Record[] records) {
        for (int i = 1; i < records.length; i++){
            Record temp = records[i];
            int place = i - 1;
            while (place >= 0 && records[place].getDate() > temp.getDate()){ //while the index is smaller than before it
                records[place+1] = records[place];
                place--;
            }
            records[place + 1] = temp;
        }
    }

    private static void mergeSort(Record[] records) {
            if (records.length <= 1)
            {
                return;
            }

            // Split the array in half
            Record[] firstHalf = new Record[records.length / 2];
            Record[] secondHalf = new Record[records.length - firstHalf.length];
            System.arraycopy(records, 0, firstHalf, 0, firstHalf.length);
            System.arraycopy(records, firstHalf.length, secondHalf, 0, secondHalf.length);

            // Sort each half
            mergeSort(firstHalf);
            mergeSort(secondHalf);

            // Merge the halves together
            merge(firstHalf, secondHalf, records);
        }

        /*
         * merge takes in three arrays. The first two are the two halves of an array
         * to be merged. The result is the resulting array that consists of the elements
         * in the two half arrays, sorted.
         */
        private static void merge(Record[] firstHalf, Record[] secondHalf, Record[] result)
        {

            // set up indices for iteration through arrays
            int firstIndex = 0;
            int secondIndex = 0;
            int resultIndex = 0;

            // while there are still elements in both halves, find which is smaller
            // and add it to the result array first. Then, add the larger.
            while (firstIndex < firstHalf.length && secondIndex < secondHalf.length)
            {
                if (firstHalf[firstIndex].getDate() < secondHalf[secondIndex].getDate()) //checking which date is later
                {
                    result[resultIndex] = firstHalf[firstIndex]; //assigning the lowest into the final array
                    firstIndex++; //move along the not inserted half
                }
                else
                {
                    result[resultIndex] = secondHalf[secondIndex];
                    secondIndex++;
                }
                resultIndex++;
            }

            // There might be left over elements in one of the halves.
            // Copy it over as well.
            System.arraycopy(firstHalf, firstIndex, result, resultIndex, firstHalf.length - firstIndex);
            System.arraycopy(secondHalf, secondIndex, result, resultIndex, secondHalf.length - secondIndex);
    }
}
