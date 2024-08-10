package ooap1.deque1;

import java.util.LinkedList;

public abstract class ParentQueue<T> {

    public final int REMOVE_FRONT_NIL = 0;
    public final int REMOVE_FRONT_OK = 1;
    public final int REMOVE_FRONT_ERR = 2;

    public final int GET_FRONT_NIL = 0;
    public final int GET_FRONT_OK = 1;
    public final int GET_FRONT_ERR = 2;

    protected final LinkedList<T> queue;

    private int removeFrontStatus;
    private int getFrontStatus;

    // конструктор
    // постусловие: создана пустая очередь
    public ParentQueue() {
        queue = new LinkedList<>();
        removeFrontStatus = REMOVE_FRONT_NIL;
        getFrontStatus = GET_FRONT_NIL;
    }

    // команды
    // постусловие: в хвост очереди добавлен новый элемент
    public void addTail(T item) {
        queue.addLast(item);
    }

    // предусловие: очередь не пуста;
    // постусловие: из головы очереди удалён элемент
    public void removeFront() {
        if (size() == 0) {
            removeFrontStatus = REMOVE_FRONT_ERR;
        } else {
            queue.removeFirst();
            removeFrontStatus = REMOVE_FRONT_OK;
        }
    }

    // запросы
    // получить элемент из головы очереди;
    // предусловие: очередь не пуста
    public T getFront() {
        T result;
        if (size() == 0) {
            result = null;
            getFrontStatus = GET_FRONT_ERR;
        } else {
            result = queue.getFirst();
            getFrontStatus = GET_FRONT_OK;
        }
        return result;
    }

    // текущий размер очереди
    public int size() {
        return queue.size();
    }

    public int get_remove_front_status() {
        return removeFrontStatus;
    }

    public int get_get_front_status() {
        return getFrontStatus;
    }
}
