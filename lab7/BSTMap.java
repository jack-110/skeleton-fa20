import java.util.Iterator;
import java.util.Set;

public class BSTMap<k extends Comparable<k>, v> implements Map61B<k, v> {

    private BST bMap;
    int size;

    private class BST{
        private k key;
        private v value;
        private BST right;
        private BST left;
        public BST(k key, v value, BST right, BST left){
            this.key = key;
            this.value = value;
            this.right = right;
            this.left = left;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(k key) {
        return false;
    }

    @Override
    public v get(k key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(k key, v value) {
        
    }

    public void printOrder(){

    }

    @Override
    public Set<k> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public v remove(k key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public v remove(k key, v value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<k> iterator() {
        throw new UnsupportedOperationException();
    }
}
