package ooap2.task14;

import java.io.Serializable;

class General implements Serializable {
    //..
}

class Any extends General {
    //..
}

class Adder<T> extends Any {

    public T sum(T first, T second) {
        T result = null;

        switch (first) {
            case String s -> result = sumString(first, second);
            case Integer i -> result = sumInteger(first, second);
            case Double d -> result = sumDouble(first, second);
            default -> result = null;
        }
        return result;
    }

    private T sumString(T first, T second) {
        return (T) ((String) first + (String) second);
    }

    private T sumInteger(T first, T second) {
        return (T) ((Integer) ((Integer) (first) + (Integer) second));
    }

    private T sumDouble(T first, T second) {
        return (T) ((Double) ((Double) (first) + (Double) second));
    }
}

class Vector<T> extends Adder {

    public static final int ADD_NIL = 0;
    public static final int ADD_OK = 1;
    public static final int ADD_ERR = 2;

    private int length;
    private T[] arr;
    private int addStatus;

    public Vector(T[] arr) {
        this.arr = arr;
        length = arr.length;
        addStatus = ADD_NIL;
    }

    public Vector(int length) {
        arr = (T[]) new Object[length];
        this.length = length;
        addStatus = ADD_NIL;
    }

    public void add(Vector<? extends T> v) {
        Vector<String> temp = new Vector<String>(1);
        if (v.getLength() != length) {
            addStatus = ADD_ERR;
            return;
        }
        T[] arr2 = v.getArr();

        for (int i = 0; i < length; i++) {
            if (!arr2[i].getClass().isInstance(temp)) {
                addVector((Vector<T>) v);
                addStatus = ADD_OK;
                break;
            }
            ((Vector<T>) arr[i]).add(((Vector<T>) arr2[i]));
        }

        addStatus = ADD_OK;
    }

    private void addVector(Vector<T> v) {
        T[] arr2 = v.getArr();
        for (int i = 0; i < length; i++) {
            arr[i] = (T) sum(arr[i], arr2[i]);
        }
    }

    public int getLength() {
        return length;
    }

    public int getAddStatus() {
        return addStatus;
    }

    public T[] getArr() {
        return arr;
    }
}
