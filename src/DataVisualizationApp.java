import processing.core.PApplet;
import processing.core.PImage;

public class DataVisualizationApp extends PApplet {
    private static DataVisualizationApp app;
    private Dataset dataset;
    private int foundAt;
    private State curState;
    private int year;
    private String month;
    PImage droneImg;
    PImage duckImg;
    PImage legoImg;
    PImage bearImg;
    PImage houseImg;
    PImage x;
    double droneAspect;
    double duckAspect;
    double legoAspect;
    double bearAspect;
    double houseAspect;
    String header;
    String instructions;
    String specialInstructions;

    public static void main(String[] args){
        app = new DataVisualizationApp();
        app.runSketch();
    }

    public DataVisualizationApp(){
        year = 0;
        month = "";
        foundAt = -1;
        header = "";
        instructions = "";
        specialInstructions = "";
    }

    public void settings(){
        size(900, 700);
    }

    public void setup(){
        dataset = new Dataset();
        fill(0);
        curState = State.PRESORT;
        droneImg = loadImage("droneImg2.png");
        duckImg = loadImage("RubberDuck.png");
        legoImg = loadImage("lego.png");
        bearImg = loadImage("teddyBear.png");
        houseImg = loadImage("Dollhouse.png");
        x = loadImage("greyX.png");
        //new height = new width*aspect
        droneAspect = (double)droneImg.height/(double)droneImg.width;
        duckAspect = (double)duckImg.height/(double)duckImg.width;
        legoAspect = (double)legoImg.height/(double)legoImg.width;
        bearAspect = (double)bearImg.height/(double)bearImg.width;
        houseAspect = (double)houseImg.height/(double)houseImg.width;
        droneImg.resize(width/5, (int)(width/5*droneAspect));
        legoImg.resize(width/5, (int)(width/5*legoAspect));
        houseImg.resize(width/5, (int)(width/5*houseAspect));
        duckImg.resize(width/5, (int)(width/5*duckAspect));
        bearImg.resize(width/5, (int)(width/5*bearAspect));
    }

    public void draw() {
        background(255);
        Record[] records = dataset.getRecords();
        strokeWeight(3);
        line(20, height / 2 + 20, width - 20, height / 2 + 20);
        textAlign(CENTER);
        textSize(20);
        text(header, width/2, height/6);
        textSize(12);
        text(instructions, width/2, height/6 + 40);
        fill(255, 0, 0);
        text(specialInstructions, width/2, height/4);
        fill(0);
        periodic();
    }

    public enum State{
        PRESORT, //after press move on
        SORTED, //after click move on
        SEARCHING, //after enter and found move on
        SEARCHED; //click upper left to return to sorted
    }

    public void periodic(){
        //presort
        if (curState == State.PRESORT){
            header = "How Frequently Googled is Each?";
            instructions = "Click space to sort by which search term was most frequently searched each year 2004-2020";
            textAlign(CENTER);
            textSize(10);
            text("2004", 20, height/2 + 40);
            text("2020", width-20, height/2 + 40);
            image(bearImg, width/7, height/4, width/8, (int)(width/8*bearAspect));
            image(duckImg, 2*width/7, height/4, width/8, (int)(width/8*duckAspect));
            image(houseImg, 3*width/7, height/4, width/8, (int)(width/8*houseAspect));
            image(legoImg, 4*width/7, height/4, width/8, (int)(width/8*legoAspect));
            image(droneImg, 5*width/7, height/4, width/8, (int)(width/8*droneAspect));

        // sorted
        } else if (curState == State.SORTED) {
            //x is x location of image
            //y is y location of image
            //ye iterates through each year
            header = "Top Searched Term for 2004-2020";
            instructions = "Click a year to search by date and see the specific breakdown";
            int x = 0;
            for (int ye = 200401; ye < 202101; ye += 100){
                int y = height/2 - 120;
                if ((ye/100) % 2 == 0){ //if even
                     y = height/2 + 30;
                }
                if (dataset.findMostFrequentYear(dataset.getRecords(), ye) == 0){
                    image(bearImg, x, y, width/8, (int)(width/8*bearAspect));
                } else if(dataset.findMostFrequentYear(dataset.getRecords(), ye) == 1){
                    image(duckImg, x, y, width/8, (int)(width/8*duckAspect));
                } else if (dataset.findMostFrequentYear(dataset.getRecords(), ye) == 2){
                    image(houseImg, x, y, width/8, (int)(width/8*houseAspect));
                } else if (dataset.findMostFrequentYear(dataset.getRecords(), ye) == 3){
                    image(legoImg, x, y,width/8, (int)(width/8*legoAspect));
                } else {
                    //System.out.println(width/8 + " " + width/8*droneAspect);
                    image(droneImg, x, y, width/8, (int)(width/8*droneAspect));
                }
                textSize(10);
                text(ye/100, x + 70, y + 10);
                x += width/(dataset.getRecords().length/12 + 2);
            }

        //searching
        } else if (curState == State.SEARCHING){
            header = "Searched For " + year;
            instructions = "Enter the month you'd like to see";
            noFill();
            rect(width/2 - 125, 3*height/4, 250, 40);
            textSize(20);
            textAlign(LEFT);
            text(month, width/2 - 125 + 10, 3*height/4 + 25);
            image(x, 5,5,25,20);

        //searched
        } else if (curState == State.SEARCHED) {
            header = month + " / " + year + " Searched";
            instructions = "Here's the breakdown for this month!";
            Record[] recordstwo = dataset.getRecords();
            float b = map(recordstwo[foundAt].getTeddyBear(), 0, 75, 0, 100);
            float du = map(recordstwo[foundAt].getRubberDucks(), 0, 75, 0, 100);
            float h = map(recordstwo[foundAt].getDollHouse(), 0, 75, 0, 100);
            float l = map(recordstwo[foundAt].getLegos(), 0, 75, 0, 100);
            float dr = map(recordstwo[foundAt].getDrone(), 0, 75, 0, 100);
            imageMode(CENTER);
            image(bearImg, width/6, height/2 + 20, b+100, (int)((b+100)*bearAspect));
            image(duckImg, 2*width/6, height/2 + 20, du+100, (int)((du+100)*duckAspect));
            image(houseImg, 3*width/6, height/2 + 20, h+100, (int)((h+100)*houseAspect));
            image(legoImg, 4*width/6, height/2 + 20, l+100, (int)((l+100)*legoAspect));
            image(droneImg, 5*width/6, height/2 + 20, dr+100, (int)((dr+100)*droneAspect));
            imageMode(CORNER);
            text(recordstwo[foundAt].getTeddyBear(), width/6, 2*height/3);
            text(recordstwo[foundAt].getRubberDucks(), 2*width/6, 2*height/3);
            text(recordstwo[foundAt].getDollHouse(), 3*width/6, 2*height/3);
            text(recordstwo[foundAt].getLegos(), 4*width/6, 2*height/3);
            text(recordstwo[foundAt].getDrone(), 5*width/6, 2*height/3);
            image(x, 5,5,25,20);
        }
    }

    //press space to sort
    //type month (two-digit) and press enter to search
    public void keyPressed(){
        if (curState == State.PRESORT){
            if (key == ' ') {
                dataset.sort();
                curState = State.SORTED;
            }
        } else if(curState == State.SEARCHING){
            if (month.length() < 3) {
                if (Character.isDigit(key) && month.length() < 2) {
                    month += key;
                } else if (keyCode == ENTER) {
                    //concatenating the numbers of year and month
                    String syear = String.valueOf(year);
                    String sdate = syear + month;
                    int date = Integer.parseInt(sdate);
                    foundAt = dataset.find(date); //returns the row
                    if (foundAt != -1) { //if found
                        specialInstructions = "";
                        curState = State.SEARCHED;
                    } else {
                        specialInstructions = "not a valid input, please put a two-digit month between 01 and 12";
                    }
                } else if (keyCode == BACKSPACE) {
                    month = month.substring(0, month.length() - 1); //take off of month
                } else {
                    specialInstructions = "not a valid input, please put a two-digit month between 01 and 12";
                }
            }
        }
    }

    public void mouseClicked(){
        //System.out.println("mouse clicked: " + mouseX + "," + mouseY);
        if (mouseX > 0 && mouseX < 30 && mouseY > 0 && mouseY < 25 && curState != State.PRESORT){ //if press upper left, it will return you to unsearched
            curState = State.SORTED;
            month = "";
        } else if (curState == State.SORTED) { //if one of the years is clicked
            //set year to the correlating clicked year
            int years = (dataset.getRecords().length/12 + 2);
            if (mouseY < height/2 + 30 && mouseY > height/4 + 30){ //if above line and below instructions by at least 20
                int z = 5;
                for (int i = 1; i < (dataset.getRecords().length/12); i ++){
                    if (mouseX + 25 > (i*2*width/years + 20) && mouseX - 25 < (i*2*width/years + 20)){
                        year = 2000 + z;
                        curState = State.SEARCHING; //set state to searching
                        return;
                    }
                    z += 2;
                }
            } else if (mouseY > (height/2 + 20) && mouseY < (height/2 + 150)){ //if below line and above further down
                int z = 4;
                for (int i = 1; i < (dataset.getRecords().length/12); i ++){
                    int x = i*2*(width/years) - (int)width/years;
                    if (mouseX + 25 > (i*2*(width/years) - (int)width/years) && mouseX - 25 < (i*2*(width/years) - (int)width/years)){
                        year = 2000 + z;
                        curState = State.SEARCHING; //set state to searching
                        return;
                    }
                    z += 2;
                }
            }
        }
    }

    public static DataVisualizationApp getApp(){
        return app;
    }

    //just displaying records as table
//    private void displayRecords(){
//        Record[] records = dataset.getRecords();
//        //headers
//        text("DATE", 125, 25);
//        text("TEDDY BEAR", 275, 25);
//        text("RUBBER DUCK", 425, 25);
//        text("DOLLHOUSE", 575, 25);
//        text("LEGOS", 725, 25);
//        text("DRONE", 875, 25);
//
//        //data
//        int y = 75;
//        for (int i = 0; i < records.length; i++){
//            Record record = records[i];
//            if (foundAt == i){
//                fill(255, 0, 0); //red
//            }
//            text(record.getDate(), 125, y);
//            fill(0); //black
//            text(record.getTeddyBear(), 275, y);
//            text(record.getRubberDucks(), 425, y);
//            text(record.getDollHouse(), 575, y);
//            text(record.getLegos(), 725, y);
//            text(record.getDrone(), 875, y);
//            y += 25;
//        }
//    }
}
