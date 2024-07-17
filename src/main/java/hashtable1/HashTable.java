package hashtable1;

import java.util.Objects;

public class HashTable<T> {

    public final int PUT_NIL = 0;       // put() ещё не вызывалась
    public final int PUT_OK = 1;        // последняя команда добавления нового значения отработала нормально
    public final int PUT_ERR = 2;       // хэш-таблица полна

    public final int REMOVE_NIL = 0;    // remove() ещё не вызывалась
    public final int REMOVE_OK = 1;     // последняя команда удаления отработала нормально
    public final int REMOVE_ERR = 2;    // хэш-таблица не содержит искомого значения

    public int capacity;
    public int size;
    public T[] slots;

    private int putStatus;
    private int removeStatus;

    // конструктор
    // постусловие: создана новая пустая хэш-таблица
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.slots = (T[]) new Object[capacity];
        for (int i = 0; i < this.capacity; i++) {
            slots[i] = null;
        }
        this.putStatus = PUT_NIL;
        this.removeStatus = REMOVE_NIL;
    }

    // команды
    // предусловие: хэш-таблица не полна
    // постусловие: в хэш-таблицу добавлено новое значение value
    public void put(T value) {
        int slot = seekSlot(value);
        if (slot != -1) {
            slots[slot] = value;
            size++;
            putStatus = PUT_OK;
        } else {
            putStatus = PUT_ERR;
        }
    }

    // предусловие: хэш-таблица содержит значение value
    // постусловие: из хэш-таблицы удалено значение value
    public void remove(T value) {
        int indexToRemove = find(value);
        if (indexToRemove != -1) {
            slots[indexToRemove] = null;
            size--;
            removeStatus = REMOVE_OK;
        } else {
            removeStatus = REMOVE_ERR;
        }
    }

    // запросы
    // есть ли значение value в хэш-таблице
    public boolean contains(T value) {
        return find(value) != -1;
    }

    // возвращает количество элементов содержащихся в хэш-таблице
    public int size() {
        return size;
    }

    // запросы
    // возвращает значение put
    public int get_put_status() {
        return putStatus;
    }

    // возвращает значение remove
    public int get_remove_status() {
        return removeStatus;
    }


    private int hashFun(T value) {
        return value == null ? 0 : Math.abs(value.hashCode()) % capacity;
    }

    private int seekSlot(T value) {
        int hash = hashFun(value);
        int slot = hash;
        while (slots[slot] != null) {
            if (slots[slot].equals(value)) {
                return slot;
            }
            int DEFAULT_STEP_SIZE = 1;
            slot = (slot + DEFAULT_STEP_SIZE) % capacity;
            if (slot == hash) {
                return -1;
            }
        }
        return slot;
    }

    private int find(T value) {
        int slot = seekSlot(value);
        if (slot == -1 || !Objects.equals(slots[slot], value)) {
            return -1;
        } else {
            return slot;
        }
    }
}