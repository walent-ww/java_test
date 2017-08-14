package ru.stqa.pft.sandbox;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void printPoint() {
        System.out.println("x=" + this.x + " y=" + this.y);
    }

    /* double distance(Point p1, Point p2) {
        double temp = Math.sqrt(Math.pow((p2.y - p1.y), 2) + Math.pow((p2.x - p1.x), 2));
        double newDouble = new BigDecimal(temp).setScale(3, RoundingMode.UP).doubleValue();
        return newDouble;
    }*/

    public double distance(Point p){
        double temp = Math.sqrt(Math.pow((this.y - p.y), 2) + Math.pow((this.x - p.x), 2));
        double newDouble = new BigDecimal(temp).setScale(3, RoundingMode.UP).doubleValue();
        return newDouble;
    }

}
