package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {

    @Test
    public void nearestTest() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(1, 5);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(4, 4); // constructs a Point with x = 1.1, y = 2.2
        KDTree kdt = new KDTree(List.of(p1, p2,p3,p4,p5,p6));
        Point actual = kdt.nearest(0, 0);
        assertEquals(p1, actual);
    }

    public static List<Point> generatePoints(){
        List<Point> lp = new ArrayList<Point>();
        Random random = new Random(1000);
        for(int i = 0; i < 999; i++){
            Double x = (random.nextDouble() * 2 - 1) * 1000;
            Double y = (random.nextDouble() * 2 - 1) * 1000;
            lp.add(new Point(x,y));
        }
        return lp;
    }

    public static void main(String[] args) {
        List<Point> points = generatePoints();
        KDTree kdt = new KDTree(points);
        long start1 = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            kdt.nearest(0,0);
        }
        long end1 = System.currentTimeMillis();
        System.out.println( end1 - start1);

        NaivePointSet naivePointSet = new NaivePointSet(points);
        long start2 = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            naivePointSet.nearest(0,0);
        }
        long end2 = System.currentTimeMillis();
        System.out.println( end2 - start2);
  }
}
