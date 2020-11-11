package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> ListOfPoint;

    public NaivePointSet(List<Point> Points){
        ListOfPoint = Points;
    }
    @Override
    public Point nearest(double x, double y) {
        Point goalpoint = new Point(x,y);
        Point nnp = ListOfPoint.get(0);
        double nndistance = Point.distance(goalpoint,nnp);
        for(Point p : this.ListOfPoint){
            double distance = Point.distance(p,goalpoint);
            if(distance < nndistance){
                nndistance = distance;
                nnp = p;
            }
        }
        return nnp;
    }
}
