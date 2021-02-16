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

        public BST(k key, v value){
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void clear() {
        bMap = null;
        size = 0;
    }

    @Override
    public boolean containsKey(k key) {
        return containskey(key,bMap);
    }

    private boolean containskey(k key,BST bst){
        if (bst == null){
            return false;
        }
        if (bst.key == key){
            return true;
        }

        int index = bst.key.compareTo(key);
        if (index < 0){
            return containskey(key,bst.right);
        }

        return containskey(key,bst.left);
    }

    @Override
    public v get(k key) {
        return get(key, bMap);
    }

    private v get(k key, BST bst){
        if(key == bst.key){
            return bst.value;
        }

        int index = bst.key.compareTo(key);
        if(index < 0){
            return get(key,bst.right);
        }

        return get(key,bst.left);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(k key, v value) {
        bMap = put(key, value,bMap);
        size++;
    }

    private BST put(k key, v value, BST bst){
        if (bst == null){
            return new BST(key, value);
        }

        int index = bst.key.compareTo(key);
        if (index < 0){
            bst.right = put(key, value, bst.right);
            return bst;
        }

        bst.left = put(key, value, bst.left);
        return bst;
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
