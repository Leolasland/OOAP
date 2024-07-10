package queue1;

import java.util.LinkedList;

public class Queue<T> {

    public final int ENQUEUE_NIL = 0;    // enqueue() еще не вызывалась - эквивалент size() == 0
    public final int ENQUEUE_OK = 1;     // последняя команда отработала нормально

    public final int DEQUEUE_NIL = 0;    // dequeue() еще не вызывалась
    public final int DEQUEUE_OK = 1;     // последняя команда отработала нормально
    public final int DEQUEUE_ERR = 2;    // очередь пуста

    public final int PEEK_NIL = 0;      // peek() ещё не вызывалась
    public final int PEEK_OK = 1;       // последняя peek() вернула корректное значение
    public final int PEEK_ERR = 2;      // очередь пуста

    private final LinkedList<T> queue;

    private int enqueueStatus;
    private int dequeueStatus;
    private int peekStatus;

    // конструктор
    // постусловие: создана новая пустая очередь
    public Queue() {
        queue = new LinkedList<>();
        enqueueStatus = ENQUEUE_NIL;
        dequeueStatus = DEQUEUE_NIL;
        peekStatus = PEEK_NIL;
    }

    // команды
    // постусловие: значение добавлено в хвост очереди
    public void enqueue(T item) {
        queue.addLast(item);
        enqueueStatus = ENQUEUE_OK;
    }

    // предусловие: очередь не пуста
    // постусловие: элемент удален из головы очереди
    public void dequeue() {
        if (size() == 0) {
            dequeueStatus = DEQUEUE_ERR;
        } else {
            queue.removeFirst();
            dequeueStatus = DEQUEUE_OK;
        }
    }

    // запросы
    // получить элемент из головы очереди
    // предусловие: очередь не пуста
    public T peek() {
        T result;
        if (size() == 0) {
            result = null;
            peekStatus = PEEK_ERR;
        } else {
            result = queue.getFirst();
            peekStatus = PEEK_OK;
        }
        return result;
    }

    // получить количество элементов в очереди
    public int size() {
        return queue.size();
    }

    // возвращает значение enqueue
    public int get_enqueue_status() {
        return enqueueStatus;
    }

    // возвращает значение dequeue
    public int get_dequeue_status() {
        return dequeueStatus;
    }

    // возвращает значение peek
    public int get_peek_status() {
        return peekStatus;
    }
}
