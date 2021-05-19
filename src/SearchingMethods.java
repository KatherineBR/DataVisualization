public class SearchingMethods {

    public SearchingMethods(){
    }

    public static int search(Record[] records, int date){
        //return SearchingMethods.linearSearch(records, date);
        return SearchingMethods.binarySearch(records, date);
    }

    private static int linearSearch(Record[] records, int date){
        for (int r = 0; r < records.length; r++){
            if (records[r].getDate() == date){
                return r;
            }
        }
        return -1;
    }

    private static int binarySearch(Record[] records, int date) {
        int low = 0;
        int high = records.length;
        while (low <= high){
            int middle = (low + high)/2;
            if (records[middle].getDate() < date){ //too small
                low = middle + 1;
            } else if (records[middle].getDate() > date){
                high = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
