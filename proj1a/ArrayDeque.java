public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int first;
    private int length;
    private int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        first = 4;
        last = 4;
        length = 8;
    }

    private int minusone(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    private int plusone(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private void resizegrow() {
        /** resize a new array when old array is too big*/
        T[] newarray = (T[]) new Object[length * 2];
        int ptr1 = first;
        int ptr2 = length;
        while (ptr1 != last) {
            newarray[ptr2] = array[ptr1];
            ptr1 = plusone(ptr1, length);
            ptr2 = plusone(ptr2, length * 2);
        }
        first = length;
        last = ptr2;
        array = newarray;
        length *= 2;
    }

    private void resizeshrink() {
        T[] newarray = (T[]) new Object[length / 2];
        int ptr1 = first;
        int ptr2 = length / 4;
        while (ptr1 != last) {
            newarray[ptr2] = array[ptr1];
            ptr1 = plusone(ptr1, length);
            ptr2 = plusone(ptr2, length / 2);
        }
        first = length / 4;
        last = ptr2;
        array = newarray;
        length /= 2;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void addFirst(T item) {
        if (plusone(last,length)==first) {
            resizegrow();
        }
        first = minusone(first);
        array[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (plusone(last,length)==first) {
            resizegrow();
        }
        last = plusone(last, length);
        array[last] = item;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = first;
        while (i != last) {
            System.out.println(array[i] + " ");
            i = plusone(i, length);
        }
        System.out.println("/n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T i = array[first];
        first = plusone(first,length);
        size -= 1;
        if (size <= length / 2) {
            resizeshrink();
        }
        return i;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T i = array[last];
        last = minusone(last);
        size -= 1;
        if (size <= length / 2) {
            resizeshrink();
        }
        return i;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        int i = first;
        while (index >= 0) {
            i = plusone(i, length);
            index -= 1;
        }
        return array[i];
    }

    public ArrayDeque(ArrayDeque other) {
        T[] array = (T[]) new Object[other.length];
        length = other.length;
        size = other.size;
        first = other.first;
        last = other.last;
        while (other.size != 0) {
            addLast((T) other.removeFirst());
        }
    }
}
