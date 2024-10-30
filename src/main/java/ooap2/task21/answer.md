1. Наследование реализации (implementation inheritance)
```java
import java.util.HashMap;
import java.util.SequencedMap;

public class LinkedHashMap<K,V>
        extends HashMap<K,V>
        implements SequencedMap<K,V> {
        //...
        }
```
LinkedHashMap наследует реализацию HashMap.

2. Льготное наследование (facility inheritance)
```java
public interface ReactiveCrudRepository<T, ID> extends Repository<T, ID> {
    
}
```

ReactiveCrudRepository - частый случай Repository.