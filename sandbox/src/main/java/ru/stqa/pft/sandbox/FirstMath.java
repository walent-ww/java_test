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
        System.out.println(p1.distance(p1, p2));
        System.out.println(p1.distance(new Point(-4,7), new Point(6,8.3)));
        System.out.println(p2.distance(new Point(4,-0.9), new Point(6,8.3)));

    }
}
