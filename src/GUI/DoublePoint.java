package GUI;

/**
 * Created by Oly on 27.02.2017.
 */
public class DoublePoint {
    private double x;
    private double y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}

    public String toString(){
        return x + ":" + y;
    }
}