package ooap1.nativedictionary1;

public class NativeDictionary<T> {

    public final int PUT_NIL = 0;       // put() ещё не вызывалась
    public final int PUT_OK = 1;        // последняя команда put() отработала нормально
    public final int PUT_ERR = 2;       // ассоциативный массив полон или (ключ или значение) == null

    public final int REMOVE_NIL = 0;    // remove() ещё не вызывалась
    public final int REMOVE_OK = 1;     // последняя команда remove() отработала нормально
    public final int REMOVE_ERR = 2;    // ассоциативный массив не содержит переданный ключ key или он равен null

    public final int GET_NIL = 0;       // get() ещё не вызывалась
    public final int GET_OK = 1;        // последняя команда получения значения по ключу key отработала нормально
    public final int GET_ERR = 2;       // ассоциативный массив не содержит переданный ключ key или он равен null

    public int capacity;
    public int size;

    public String[] slots;
    public T[] values;

    int putStatus;
    int removeStatus;
    int getStatus;

    // конструктор
    // постусловие: создан новый пустой ассоциативный массив
    public NativeDictionary(int capacity) {
        this.capacity = capacity;
        slots = new String[capacity];
        this.size = 0;
        values = (T[]) new Object[capacity];

        putStatus = PUT_NIL;
        removeStatus = REMOVE_NIL;
        getStatus = GET_NIL;
    }

    // команды
    // предусловие: ассоциативный массив не полон, ключ key и значение value не равны null
    // постусловие: если ассоциативный массив не содержит ключ key, то в него будет добавлено новое значение value по ключу key,
    // если ассоциативный массив содержит ключ key, то значение по ключу key обновляется на value
    public void put(String key, T value) {
        int slot = seekSlot(key);
        if (slot == -1 || value == null) {
            putStatus = PUT_ERR;
        } else {
            slots[slot] = key;
            values[slot] = value;
            size++;
            putStatus = PUT_OK;
        }
    }

    // предусловие: ключ key не равен null, ассоциативный массив содержит ключ key
    // постусловие: из ассоциативного массива удален ключ key и соответствующее ему значение
    public void remove(String key) {
        int slot = seekSlot(key);
        if (slot == -1) {
            removeStatus = REMOVE_ERR;
        } else {
            slots[slot] = null;
            values[slot] = null;
            size--;
            removeStatus = REMOVE_OK;
        }
    }

    // запросы
    // получить значение по ключу key
    // предусловие: ключ key не равен null, ассоциативный массив содержит ключ key
    public T get(String key) {
        T result;
        int slot = seekSlot(key);
        if (slot == -1 || values[slot] == null) {
            result = null;
            getStatus = GET_ERR;
        } else {
            result = values[slot];
            getStatus = GET_OK;
        }
        return result;
    }

    // содержит ли ассоциативный массив ключ key?
    public boolean containsKey(String key) {
        int slot = seekSlot(key);
        return slot != -1 && slots[slot] != null;
    }

    // получить количество элементов ассоциативного массива
    public int size() {
        return size;
    }

    // получить емкость ассоциативном массиве
    public int capacity() {
        return capacity;
    }

    // запросы
    // возвращает значение put
    public int get_put_status(){
        return putStatus;
    }

    // возвращает значение remove
    public int get_remove_status(){
        return removeStatus;
    }

    // возвращает значение get
    public int get_get_status(){
        return getStatus;
    }


    private int seekSlot(String key) {
        int hash = hashFun(key);
        if (hash == -1) {
            return -1;
        }
        int slot = hash;
        while (slots[slot] != null) {
            if (slots[slot].equals(key)) {
                return slot;
            }
            slot = (slot + 1) % capacity;
            if (slot == hash) {
                return -1;
            }
        }
        return slot;
    }

    private int hashFun(String key) {
        return key == null ? -1 : Math.abs(key.hashCode()) % capacity;
    }
}
