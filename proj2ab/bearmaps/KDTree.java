package bearmaps;
import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet{

    private Node root;
    private class Node implements Comparator<Double> {
        private Node right;
        private Node left;
        private Point p;
        int horizontal; // 1 : horizontal; 0 : vertical.
        public Node(Node r,Node l,Point point, int orientation){
            right = r;
            left = l;
            horizontal = orientation;
            p = point;
        }
        @Override
        /* 1:i>j and 0: i<j.*/
        public int compare(Double i , Double j) {
            if(i > j){
                return 1;
            }
            return 0;
        }
    }

    /* the size of the list of points >= 1.*/
    public KDTree(List<Point> points){
        root = new Node(null,null,points.get(0),1);
        add(root,points);
    }
    /* add points into KDTree.*/
    private void add(Node root,List<Point> points){
        int i = 1;
        while (i < points.size()){
            add(root,points.get(i));
            i++;
        }
    }
    /* add a point into KdTree.*/
    private void add(Node node, Point p){
        switch (node.horizontal){
            case 1 :
                addByX(node,p);
                break;
            case 0 :
                addByY(node,p);
                break;
        }
    }
    /* if case 1 then go left,else case 0 go to right.*/
    private void addByX(Node node, Point p){
        int comparator = node.compare(node.p.getX(),p.getX());
        switch (comparator){
            case 1 :
                if(node.left == null){
                    node.left = new Node(null,null,p,0);
                }else {
                    add(node.left,p);
                }
                break;
            case 0 :
                if (node.right == null) {
                    node.right = new Node(null, null, p,0);
                } else {
                    add(node.right, p);
                }
                break;
        }

    }
    /* if case 1 then go left,else case 0 go to right.*/
    private void addByY(Node node, Point p){
        int comparator = node.compare(node.p.getY(),p.getY());
        switch (comparator){
            case 1 :
                if(node.left == null){
                    node.left = new Node(null,null,p,1);
                }else {
                    add(node.left,p);
                }
                break;
            case 0 :
                if (node.right == null) {
                    node.right = new Node(null, null, p,1);
                } else {
                    add(node.right, p);
                }
                break;
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(this.root,new Point(x,y),this.root).p;
    }

    /**
     * Return a point whose distance from goal is less than best.
     * @param n Node The root of a K-D tree.
     * @param goal Point The goal point that we want to find another
     *             point in a k-d tree that is nearest.
     * @param best Node The currently best node.
     */
    private Node nearest(Node n, Point goal, Node best){
        Node good,bad;
        if(n == null){
            return best;
        }else if (Point.distance(n.p,goal) < Point.distance(goal,best.p)){
            best = n;
        }
        if(goodSide(n,goal) == n.left){
            good = n.left;
            bad = n.right;
        }else {
            good = n.right;
            bad = n.left;
        }
        best = nearest(good,goal,best);
        if (mayBetterPoint(n,goal,best)) {
            best = nearest(bad, goal, best);
        }

        return best;
    }

    /**
     * tell which side right or left is better,according from
     * the sits of goal point. If it's at right, then the good
     * side is right.
     * @param n Node The root of a K-D tree.
     * @param goal Point The goal point.
     * @return node Good side.
     */
    private Node goodSide(Node n,Point goal){
        int direction = n.horizontal;
        switch (direction){
            case 1:
                if(n.p.getX() - goal.getX() < 0){
                    return n.right;
                }
                return n.left;
            case 0:
                if(n.p.getY() - goal.getY() < 0){
                    return n.right;
                }
                return n.left;
            default:
                return null;
        }
    }
    /**
     * Pruning rules:is there has possible better points in bad side,
     * according from the the distance of two points on the x or y.
     * @param n Node The node that needs to be compared with best node.
     * @param goal Point The goal point.
     * @param best Node The currently shortest from goal point.
     */
    private boolean mayBetterPoint(Node n,Point goal,Node best) {
        //todo: it tested to be wrong! change it and prove it again.
        if(n == null){
            return false;
        }
        Point BestPoint = n.p;
        double BestDistance = Math.sqrt(Point.distance(goal, best.p));
        if (n. horizontal == 1) {
            return Math.abs(n.p.getX() - goal.getX()) < BestDistance;
        }
        return Math.abs(n.p.getY() - goal.getY()) < BestDistance;
    }
}
