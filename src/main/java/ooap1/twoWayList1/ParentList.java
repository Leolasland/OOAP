package ooap1.twoWayList1;

public abstract class ParentList<T> {

    int HEAD_NIL = 0;      // head() ещё не вызывалась
    int HEAD_OK = 1;       // последняя команда установки курсора отработала нормально
    int HEAD_ERR = 2;      // список пуст

    int TAIL_NIL = 0;      // tail() ещё не вызывалась
    int TAIL_OK = 1;       // последняя команда установки курсора отработала нормально
    int TAIL_ERR = 2;      // список пуст

    int RIGHT_NIL = 0;     // right() ещё не вызывалась
    int RIGHT_OK = 1;      // последняя команда установки курсора отработала нормально
    int RIGHT_ERR = 2;     // список пуст (курсор не задан) или узел, на который указывает курсор, не имеет соседа справа

    int PUT_RIGHT_NIL = 0; // put_right() ещё не вызывалась
    int PUT_RIGHT_OK = 1;  // последняя команда добавления узла отработала нормально
    int PUT_RIGHT_ERR = 2; // список пуст (курсор не задан)

    int PUT_LEFT_NIL = 0;  // put_left() ещё не вызывалась
    int PUT_LEFT_OK = 1;   // последняя команда добавления узла отработала нормально
    int PUT_LEFT_ERR = 2;  // список пуст (курсор не задан)

    int REMOVE_NIL = 0;    // remove() ещё не вызывалась
    int REMOVE_OK = 1;     // последняя команда удаления узла отработала нормально
    int REMOVE_ERR = 2;    // список пуст (курсор не задан)

    int REPLACE_NIL = 0;   // replace() ещё не вызывалась
    int REPLACE_OK = 1;    // последняя команда замены значения узла отработала нормально
    int REPLACE_ERR = 2;   // список пуст (курсор не задан)

    int FIND_NIL = 0;      // find() ещё не вызывалась
    int FIND_OK = 1;       // последняя команда поиска искомого значения отработала нормально
    int FIND_ERR = 2;      // следующий узел не найден или список пуст (курсор не задан)

    int GET_NIL = 0;       // get() ещё не вызывалась
    int GET_OK = 1;        // последняя команда получения значения текущего узла отработала нормально
    int GET_ERR = 2;       // список пуст (курсор не задан)

    protected Node<T> cursor;
    private Node<T> head;
    private Node<T> tail;
    private int size;                   // посчитать количество узлов в списке

    private int HEAD_STATUS = HEAD_NIL;
    private int TAIL_STATUS = TAIL_NIL;
    private int RIGHT_STATUS = RIGHT_NIL;
    private int PUT_RIGHT_STATUS = PUT_RIGHT_NIL;
    private int PUT_LEFT_STATUS = PUT_LEFT_NIL;
    private int REMOVE_STATUS = REMOVE_NIL;
    private int REPLACE_STATUS = REPLACE_NIL;
    private int FIND_STATUS = FIND_NIL;
    private int GET_STATUS = GET_NIL;

    // конструктор
    // постусловие: создан новый пустой список
    public ParentList() {
        clear();
    }

    // предусловие: список не пуст
    // постусловие: курсор установлен на первый узел в списке
    public void head() {
        if (is_value()) {
            cursor = head;
            HEAD_STATUS = HEAD_OK;
        } else {
            HEAD_STATUS = HEAD_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: курсор установлен на последний узел в списке
    public void tail() {
        if (is_value()) {
            cursor = tail;
            TAIL_STATUS = TAIL_OK;
        } else {
            TAIL_STATUS = TAIL_ERR;
        }
    }

    // предусловие: список не пуст и текущий узел имеет соседа справа
    // постусловие: курсор сдвинут на один узел вправо
    public void right() {
        if (is_value() && cursor.next != null) {
            cursor = cursor.next;
            RIGHT_STATUS = RIGHT_OK;
        } else {
            RIGHT_STATUS = RIGHT_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: после текущего узла вставлен новый узел с заданным значением
    public void put_right(T value) {
        if (is_value()) {
            Node<T> newNode = new Node<>(value);

            if (is_tail()) {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            } else {
                newNode.prev = cursor;
                newNode.next = cursor.next;

                cursor.next.prev = newNode;
                cursor.next = newNode;
            }

            size++;
            PUT_RIGHT_STATUS = PUT_RIGHT_OK;
        } else {
            PUT_RIGHT_STATUS = PUT_RIGHT_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: перед текущим узлом вставлен новый узел с заданным значением
    public void put_left(T value) {
        if (is_value()) {
            Node<T> newNode = new Node<>(value);

            newNode.next = cursor;
            if (is_head()) {
                cursor.prev = newNode;
                head = newNode;
            } else {
                newNode.prev = cursor.prev;

                cursor.prev.next = newNode;
                cursor.prev = newNode;
            }

            size++;
            PUT_LEFT_STATUS = PUT_LEFT_OK;
        } else {
            PUT_LEFT_STATUS = PUT_LEFT_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: текущий узел удален (курсор смещается к правому соседу, если он есть, в противном случае курсор
    //                                  смещается к левому соседу, если он есть)
    public void remove() {
        if (is_value()) {
            if (is_tail() && is_head()) {
                tail = null;
                head = null;
                cursor = null;
            } else if (is_tail()) {
                tail = cursor.prev;
                cursor.prev.next = null;
                cursor = tail;
            } else if (is_head()) {
                head = cursor.next;
                cursor.next.prev = null;
                cursor = head;
            } else {
                cursor.next.prev = cursor.prev;
                cursor.prev.next = cursor.next;
                cursor = cursor.next;
            }

            size--;
            REMOVE_STATUS = REMOVE_OK;
        } else {
            REMOVE_STATUS = REMOVE_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: новый узел добавлен в хвост списка
    public void add_tail(T value) {
        Node<T> newNode = new Node<>(value);

        if (is_value()) {
            newNode.prev = tail;
            tail.next = newNode;
        } else {
            head = newNode;
            cursor = head;
        }
        tail = newNode;

        size++;
    }

    // предусловие: список не пуст
    // постусловие: значение текущего узла заменено на заданное
    public void replace(T value) {
        if (is_value()) {
            cursor.value = value;
            REPLACE_STATUS = REPLACE_OK;
        } else {
            REPLACE_STATUS = REPLACE_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: курсор установлен на следующий узел с искомым значением (по отношению к текущему узлу),
    //              если таковой существует
    public void find(T value) {
        if (is_value()) {
            Node<T> start = cursor;

            while (cursor.value != value && cursor.next != null) {
                right();
            }

            if (cursor == tail && tail.value != value) {
                cursor = start;
                FIND_STATUS = FIND_ERR;
            } else {
                FIND_STATUS = FIND_OK;
            }
        } else {
            FIND_STATUS = FIND_ERR;
        }
    }

    // предусловие: список не пуст
    // постусловие: из списка удалятся все узлы с заданным значением
    public void remove_all(T value) {
        Node<T> start = cursor;

        cursor = head;
        find(value);
        while (FIND_STATUS == FIND_OK) {
            if (cursor == start) {
                remove();
                start = cursor;
            } else {
                remove();
            }
            cursor = head;
            find(value);
        }
        cursor = start;
    }

    // постусловие: из списка удалятся все значения, курсор сбрасывается, т.к. список пуст
    public void clear() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.size = 0;

        HEAD_STATUS = HEAD_NIL;
        TAIL_STATUS = TAIL_NIL;
        RIGHT_STATUS = RIGHT_NIL;
        PUT_RIGHT_STATUS = PUT_RIGHT_NIL;
        PUT_LEFT_STATUS = PUT_LEFT_NIL;
        REMOVE_STATUS = REMOVE_NIL;
        REPLACE_STATUS = REPLACE_NIL;
        FIND_STATUS = FIND_NIL;
        GET_STATUS = GET_NIL;
    }

    // запросы

    // предусловие: список не пуст, курсор установлен
    // получить значение текущего узла
    public T get() {
        T result;
        if (is_value()) {
            result = cursor.value;
            GET_STATUS = GET_OK;
        } else {
            result = null;
            GET_STATUS = GET_ERR;
        }
        return result;
    }

    // посчитать количество узлов в списке
    public int size() {
        return size;
    }

    // находится ли курсор в начале списка?
    public boolean is_head() {
        return cursor == head;
    }

    // находится ли курсор в конце списка?
    public boolean is_tail() {
        return cursor == tail;
    }

    // установлен ли курсор на какой-либо узел в списке (по сути, непустой ли список)
    public boolean is_value() {
        return cursor != null;
    }

    // запросы статусов (возможные значения статусов)

    // успешно; список пуст
    public int get_head_status() {
        return HEAD_STATUS;
    }

    // успешно; список пуст
    public int get_tail_status() {
        return TAIL_STATUS;
    }

    // успешно; правее нету элемента
    public int get_right_status() {
        return RIGHT_STATUS;
    }

    // успешно; список пуст
    public int get_put_right_status() {
        return PUT_RIGHT_STATUS;
    }

    // успешно; список пуст
    public int get_put_left_status() {
        return PUT_LEFT_STATUS;
    }

    // успешно; список пуст
    public int get_remove_status() {
        return REMOVE_STATUS;
    }

    // успешно; список пуст
    public int get_replace_status() {
        return REPLACE_STATUS;
    }

    // следующий найден; следующий не найден; список пуст
    public int get_find_status() {
        return FIND_STATUS;
    }

    // успешно; список пуст
    public int get_get_status() {
        return GET_STATUS;
    }
}
