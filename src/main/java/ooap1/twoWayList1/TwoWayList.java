package ooap1.twoWayList1;

public class TwoWayList<T> extends ParentList<T> {

    public final int LEFT_NIL = 0;  // left() ещё не вызывалась
    public final int LEFT_OK = 1;   // последняя команда установки курсора отработала нормально
    public final int LEFT_ERR = 2;  // список пуст (курсор не задан) или узел, на который указывает курсор, не имеет соседа слева

    private int LEFT_STATUS = LEFT_NIL;

    public TwoWayList() {
        clear();
    }

    // предусловие: список не пуст и текущий узел имеет соседа слева
    // постусловие: курсор сдвинут на один узел влева
    public void left() {
        if (is_value() && !is_head()) {
            cursor = cursor.prev;
            LEFT_STATUS = LEFT_OK;
        } else {
            LEFT_STATUS = LEFT_ERR;
        }
    }

    @Override
    public void clear() {
        super.clear();
        LEFT_STATUS = LEFT_NIL;
    }

    // успешно; левее нету элемента
    public int get_left_status() {
        return LEFT_STATUS;
    }
}
