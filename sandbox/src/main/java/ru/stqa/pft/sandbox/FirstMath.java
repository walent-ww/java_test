package ru.stqa.pft.sandbox;

/**
 * Created by user on 13.08.17.
 */
public class FirstMath {

    public static void main(String[] args){

        //create 2 points
        Point p1 = new Point(-1,2.9);
        Point p2 = new Point(3,5);

        //prit points
        p1.printPoint();
        p2.printPoint();

        //distance
        System.out.println("Расстояние между точками А(" + p1.x + ";"+p1.y + ") и В("
                + p2.x + ";" + p2.y + ") = "
                +distance(p1, p2));

        //distance from Point class
        System.out.println(p1.distance(p1, p2));
        System.out.println(p1.distance(new Point(-4,7), new Point(6,8.3)));
        System.out.println(p2.distance(new Point(4,-0.9), new Point(6,8.3)));

    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.y - p1.y), 2) + Math.pow((p2.x - p1.x), 2));
    }
}
