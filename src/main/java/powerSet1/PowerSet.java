package powerSet1;

public class PowerSet<T> extends HashTable<T> {

    // конструктор
    // постусловие: создано новое пустое множество
    public PowerSet(int capacity) {
        super(capacity);
    }

    // запросы
    // возвращает пересечение текущего множества с переданным множеством set
    public PowerSet<T> intersection(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<>(capacity);
        if (set != null) {
            for (int i = 0; i < capacity; i++) {
                if (set.slots[i] != null && contains(set.slots[i])) {
                    result.put(set.slots[i]);
                }
            }
        }
        return result;
    }

    // возвращает объединение текущего множества с переданным множеством set
    public PowerSet<T> union(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            result.put(set.slots[i]);
            result.put(slots[i]);
        }
        return result;
    }

    // возвращает разницу текущего множества с переданным множеством set
    public PowerSet<T> difference(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            if (set == null || set.contains(slots[i])) {
                result.put(slots[i]);
            }
        }
        return result;
    }

    // является ли переданное множество set подмножеством текущего множества
    public boolean isSubset(PowerSet<T> set) {
        if (set != null) {
            for (int i = 0; i < capacity; i++) {
                if (!contains(set.slots[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
