public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;

    private class TNode {
        private TNode prev;
        private T item;
        private TNode next;

        private TNode(T i) {
            item = i;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * public LinkedListDeque(LinkedListDeque other) {
     * size = 0;
     * sentinel = new TNode(null);
     * sentinel.next = sentinel;
     * sentinel.prev = sentinel;
     * for (int i = 0; i < other.size(); i++) {
     * addLast((T) other.get(i));
     * }
     * }
     */

    public void addFirst(T x) {
        TNode p = new TNode(x);
        p.next = sentinel.next;
        sentinel.next.prev = p;
        sentinel.next = p;
        p.prev = sentinel;
        size += 1;
    }

    public void addLast(T x) {
        TNode p = new TNode(x);
        p.prev = sentinel.prev;
        sentinel.prev.next = p;
        sentinel.prev = p;
        p.next = sentinel;
        size += 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        TNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.println(p.item + " ");
            p = p.next;
        }
        System.out.println("/n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T p = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return p;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T p = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return p;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        TNode p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursivehelper(int index, TNode start) {
        if (index == 0) {
            return start.item;
        }
        return getRecursivehelper(index - 1, start.next);
    }

    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return getRecursivehelper(index, sentinel.next);
    }
}
