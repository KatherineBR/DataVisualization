import processing.data.Table;
import processing.data.TableRow;
import java.util.Arrays;

public class Dataset implements Sortable {
    private final Record[] records;
    private Table table;

    public Dataset() {
        DataVisualizationApp app = DataVisualizationApp.getApp();
        table = app.loadTable("data/javaDataTest.csv", "header");
        records = new Record[table.getRowCount()]; //array of records
        for (int i = 0; i < records.length; i++) {
            TableRow row = table.getRow(i);
            int date = row.getInt(0);
            int teddyBear = row.getInt(1);
            int rubberDuck = row.getInt(2);
            int dollHouse = row.getInt(3);
            int legos = row.getInt(4);
            int drone = row.getInt(5);
            records[i] = new Record(date, teddyBear, rubberDuck, dollHouse, legos, drone);
        }
    }

    public Record[] getRecords() {
        return records;
    }

    public int getColCount(){return table.getColumnCount(); }

    @Override
    public void sort() {
        SortingMethods.sort(records);
    }

    public int find(int date) {
        return SearchingMethods.search(records, date);
    }

    public int findMostFrequentYear(Record[] records, int date){
        int[] findLargestY = new int[getColCount() - 1];
        Arrays.fill(findLargestY, 0);
        for (int d = date; d < date + 12; d++){
            findLargestY[findMostFrequentOfMonth(records, d)] += 1;
            //array like [1, 5, 2, 1, 3]
        }
        int lindex = 0;
        for (int i = 1; i < findLargestY.length; i++){
            if (findLargestY[lindex] < findLargestY[i]){
                lindex = i;
            }
        }
        return lindex; //will return index of most searched in year
    }

    public int findMostFrequentOfMonth(Record[] records, int date){
        int lindex = 0;
        int[] findLargestM = new int[getColCount() - 1];
        findLargestM[0] = records[find(date)].getTeddyBear();
        findLargestM[1] = records[find(date)].getRubberDucks();
        findLargestM[2] = records[find(date)].getDollHouse();
        findLargestM[3] = records[find(date)].getLegos();
        findLargestM[4] = records[find(date)].getDrone();
        int past = findLargestM[0];
        for (int r = 1; r < findLargestM.length; r++){
            if (findLargestM[r] > past) {
                lindex = r;
                past = findLargestM[r];
            }
        }
        return lindex;
    }
}
