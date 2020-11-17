package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {

    @Test
    public void nearestTest() {

        Point p1 = new Point(2, 3.1); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(2, 3);
        Point p5 = new Point(1, 3);
        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5));
        Point actual = kdt.nearest(0, 0);
        assertEquals(p5, actual);
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3.1); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(2, 3);
        Point p5 = new Point(1, 3);
        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5));
        long start1 = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++){
            kdt.nearest(0,0);
        }
        long end1 = System.currentTimeMillis();
        System.out.println( end1 - start1);

        NaivePointSet naivePointSet = new NaivePointSet(List.of(p1, p2, p3, p4, p5));
        long start2 = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++){
            naivePointSet.nearest(0,0);
        }
        long end2 = System.currentTimeMillis();
        System.out.println( end2 - start2);
    }
}
