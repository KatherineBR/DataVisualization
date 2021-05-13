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
            foundAt = dataset.find(200401);
        } else if (key == 'o'){
            dataset.sort();
        }
    }

    public static DataVisualizationApp getApp(){
        return app;
    }

    private void displayRecords(){
        Record[] records = dataset.getRecords();
        text("DATE", 125, 25);
        text("TEDDY BEAR", 275, 25);
        text("RUBBER DUCK", 425, 25);
        text("DOLLHOUSE", 575, 25);
        text("LEGOS", 725, 25);
        text("DRONE", 875, 25);
        int y = 75;
        for (int i = 0; i < records.length; i++){
            Record record = records[i];

            if (foundAt == i){
                fill(255, 0, 0); //red
            }
            text(record.getDate(), 150, y);

            fill(0); //black
            text(record.getTeddyBear(), 300, y);
            text(record.getRubberDucks(), 450, y);
            text(record.getDollHouse(), 600, y);
            text(record.getLegos(), 750, y);
            text(record.getDrone(), 900, y);
            y += 25;
        }
    }
}
