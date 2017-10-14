package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.sandbox.FirstMath;
import ru.stqa.pft.rest.sandbox.Point;

/**
 * Created by user on 13.08.17.
 */
public class PointTest {

    //method Point class
    @Test
    public void testDistance1(){
        Point p1 = new Point(-1,2.9);
        Point p2 = new Point(3,5);
        Assert.assertEquals(p1.distance(p2),4.518);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(11.9,3);
        Point p2 = new Point(5,0.9);
        Assert.assertEquals(p1.distance(p2),7.213);
    }

    @Test
    public void testDistance3(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2),0.0);
    }

    //def FirstMath
    @Test
    public void testDistance4(){
        Point p1 = new Point(11.9,3);
        Point p2 = new Point(5,0.9);
        Assert.assertEquals(FirstMath.distance(p1,p2),7.212489168102786);
    }
}
