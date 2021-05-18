import processing.data.Table;
import processing.data.TableRow;

public class Dataset implements Sortable {
    private final Record[] records;

    public Dataset() {
        DataVisualizationApp app = DataVisualizationApp.getApp();
        Table table = app.loadTable("data/javaDataTest.csv", "header");
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

    @Override
    public void sort() {
        SortingMethods.sort(records);
    }

    public int find(int date) {
        return SearchingMethods.search(records, date);
    }
}
