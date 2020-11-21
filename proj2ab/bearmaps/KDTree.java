package bearmaps;
import java.util.List;

public class KDTree implements PointSet{

    private Node root;
    private class Node{
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
    private void add(Node root, Point p){
        compareX(root, p);
    }
    /* if less then go left,else go to right.*/
    private void compareX(Node node,Point p){
        if(p.getX() < node.p.getX()){
            if(node.left == null){
                node.left = new Node(null,null,p,0);
            }else {
                compareY(node.left,p);
            }
        }else {
            if (node.right == null) {
                node.right = new Node(null, null, p,0);
            } else {
                compareY(node.right, p);
            }
        }
    }
    /* if less then go left,else go to right.*/
    private void compareY(Node node, Point p){
        if(p.getY() < node.p.getY()){
            if(node.left == null){
                node.left = new Node(null,null,p,1);
            }else {
                compareX(node.left,p);
            }
        }else {
            if (node.right == null) {
                node.right = new Node(null, null, p,1);
            } else {
                compareX(node.right, p);
            }
        }
    }
    @Override
    public Point nearest(double x, double y) {
        Node nearnode = nearest(this.root,new Point(x,y),this.root);
        return nearnode.p;
    }

    /* return a point whose distance from goal is less than best. */
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
        if(mayBetterPoint(bad,goal,best)){
            best = nearest(bad,goal,best);
        }
        return best;
    }

    /* tell which side is true or not.*/
    private Node goodSide(Node n,Point goal){
        if(n.right == null || n.left == null){
            return null;
        }
        Double leftdistance = Point.distance(n.left.p,goal);
        Double rightdistance = Point.distance(n.right.p,goal);
        if(leftdistance < rightdistance){
            return n.left;
        }else {
            return n.right;
        }
    }
    /* pruning rules:is there has possible better point in bad side.*/
    private boolean mayBetterPoint(Node n,Point goal,Node best) {
        if(n == null){
            return false;
        }
        Point BestPoint = n.p;
        Double BestDistance = Math.sqrt(Point.distance(goal, best.p));
        if (n. horizontal == 1) {
            return Math.abs(n.p.getX() - goal.getX()) < BestDistance;

        }
        return Math.abs(n.p.getY() - goal.getY()) < BestDistance;
    }
}
