import processing.core.PImage;

public class Record {
    private final int date; //note to self: made these all final
    private final int teddyBear;
    private final int rubberDucks;
    private final int dollHouse;
    private final int legos;
    private final int drone;

    public Record(int date, int teddyBear, int rubberDucks, int dollHouse, int legos, int drone){
        this.date = date;
        this.teddyBear = teddyBear;
        this.rubberDucks = rubberDucks;
        this.dollHouse = dollHouse;
        this.legos = legos;
        this.drone = drone;
    }

    public void display(PImage img){
//        image(droneImg, 0, 0);
//        image(legoImg, width/5, 0);
//        image(houseImg, 2*(width/5), 0);
//        image(duckImg, 3*(width/5), 0);
//        image(bearImg, 4*(width/5), 0);
    }

    public int getDate(){ return date; }

    public int getTeddyBear() {
        return teddyBear;
    }

    public int getRubberDucks() {return rubberDucks;}

    public int getDollHouse() {
        return dollHouse;
    }

    public int getLegos() {
        return legos;
    }

    public int getDrone() {return drone;}


}