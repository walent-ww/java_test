package ru.stqa.pft.sandbox;

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

    public double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.y - p1.y), 2) + Math.pow((p2.x - p1.x), 2));
    }

}