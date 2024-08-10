package ooap1.deque1;

public class Deque<T> extends ParentQueue<T> {
    public final int REMOVE_TAIL_NIL = 0;
    public final int REMOVE_TAIL_OK = 1;
    public final int REMOVE_TAIL_ERR = 2;

    public final int GET_TAIL_NIL = 0;
    public final int GET_TAIL_OK = 1;
    public final int GET_TAIL_ERR = 2;

    private int removeTailStatus;
    private int getTailStatus;

    // конструктор
    // постусловие: создана пустая очередь
    public Deque() {
        super();
        removeTailStatus = REMOVE_TAIL_NIL;
        getTailStatus = GET_TAIL_NIL;
    }

    // команды
    // постусловие: в голову очереди добавлен новый элемент
    public void addFront(T item) {
        queue.addFirst(item);
    }

    // предусловие: очередь не пуста;
    // постусловие: из хвоста очереди удалён элемент
    public void removeTail() {
        if (size() == 0) {
            removeTailStatus = REMOVE_TAIL_ERR;
        } else {
            queue.removeLast();
            removeTailStatus = REMOVE_TAIL_OK;
        }
    }

    // запросы
    // получить элемент из хвоста очереди;
    // предусловие: очередь не пуста
    public T getTail() {
        T result;
        if (size() == 0) {
            result = null;
            getTailStatus = GET_TAIL_ERR;
        } else {
            result = queue.getLast();
            getTailStatus = GET_TAIL_OK;
        }
        return result;
    }

    public int get_remove_tail_status() {
        return removeTailStatus;
    }

    public int get_get_tail_status() {
        return getTailStatus;
    }
}
