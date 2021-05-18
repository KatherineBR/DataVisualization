import processing.core.PApplet;

public class DataVisualizationApp extends PApplet {
    private static DataVisualizationApp app;
    private Dataset dataset;
    private int foundAt;

    public static void main(String[] args){
        app = new DataVisualizationApp();
        app.runSketch();
    }

    public DataVisualizationApp(){
        foundAt = -1;
    }

    public void settings(){
        size(1000, 1000);
    }

    public void setup(){
        dataset = new Dataset();
        fill(0);
    }

    public void draw(){
        background(255);
        displayRecords();
    }

    public void keyPressed(){
        if (key == 'e'){
            foundAt = dataset.find(200401); //returns the row
        } else if (key == 'o'){
            dataset.sort();
        }
    }

    public static DataVisualizationApp getApp(){
        return app;
    }

    private void displayRecords(){
        Record[] records = dataset.getRecords();
        //headers
        text("DATE", 125, 25);
        text("TEDDY BEAR", 275, 25);
        text("RUBBER DUCK", 425, 25);
        text("DOLLHOUSE", 575, 25);
        text("LEGOS", 725, 25);
        text("DRONE", 875, 25);

        //data
        int y = 75;
        for (int i = 0; i < records.length; i++){
            Record record = records[i];
            if (foundAt == i){
                fill(255, 0, 0); //red
            }
            text(record.getDate(), 125, y);
            fill(0); //black
            text(record.getTeddyBear(), 275, y);
            text(record.getRubberDucks(), 425, y);
            text(record.getDollHouse(), 575, y);
            text(record.getLegos(), 725, y);
            text(record.getDrone(), 875, y);
            y += 25;
        }
    }
}
