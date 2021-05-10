public class SearchingMethods {

    public SearchingMethods(){
    }

    public static int search(Record[] records, String country){
        return SearchingMethods.linearSearch(records, country);
        //return SearchingMethods.binarySearch(records, country);
    }

    private static int linearSearch(Record[] records, String country){
        int i = 0;
        int position = -1;
        while (i < records.length && position == -1) {
            if (records[i].getCountry().compareToIgnoreCase(country) == 0) {
                position = i;
            }
            i++;
        }
        return position;
    }

    private static int binarySearch(Record[] records, String country) {
        int low = 0;
        int high = records.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;

            Record cur = records[mid];

            int compareResult = cur.getCountry().compareToIgnoreCase(country);
            if(compareResult == 0) {
                return mid;
            } else if(compareResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
