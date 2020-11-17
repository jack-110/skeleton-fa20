package bearmaps;
import java.util.List;
public class KDTree implements PointSet{


    private Node root;

    private class Node{
        private Node right;
        private Node left;
        private Point p;
        public Node(Node r,Node l,Point point){
            right = r;
            left = l;
            p = point;
        }
    }

    /* the size of the list of points >= 1.*/
    public KDTree(List<Point> points){
        root = new Node(null,null,points.get(0));
        this.add(root,points);
    }
    /* add points into KDTree.*/
    private void add(Node root,List<Point> points){
        int i = 1;
        while (i < points.size()){
            compareX(root, points.get(i));
            i++;
        }
    }
    /* if less then go left,else go to right.*/
    private void compareX(Node node,Point p){
        if(p.getX() < node.p.getX()){
            if(node.left == null){
                node.left = new Node(null,null,p);
            }else {
                compareY(node.left,p);
            }
        }else {
            if (node.right == null) {
                node.right = new Node(null, null, p);
            } else {
                compareY(node.right, p);
            }
        }
    }
    /* if less then go left,else go to right.*/
    private void compareY(Node node, Point p){
        if(p.getY() < node.p.getY()){
            if(node.left == null){
                node.left = new Node(null,null,p);
            }else {
                compareX(node.left,p);
            }
        }else {
            if (node.right == null) {
                node.right = new Node(null, null, p);
            } else {
                compareX(node.right, p);
            }
        }
    }
    @Override
    public Point nearest(double x, double y) {
        Node best = nearest(this.root,new Point(x,y),this.root);
        return best.p;
    }

    /* return a point whose distance from goal is less than best. */
    private Node nearest(Node n, Point goal, Node best){
        if(n == null){
            return best;
        }else if (Point.distance(n.p,goal) < Point.distance(goal,best.p)){
            best = n;
        }
        best = nearest(n.left,goal,best);
        best = nearest(n.right,goal,best);
        return best;
    }
}
