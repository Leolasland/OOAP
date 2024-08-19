Например, нам нужно реализовать передвижение для юнитов в игре. Юнит должен иметь возможность передвигаться своим способом - 
летать, плавать, ходить, ехать, ползти и т.д. Но т.к. возможностей может быть еще много, то хотелось бы не переписывать 
каждый раз метод передвижения, а расширять его. Одно из решений - это делегировать каждую операцию в соотвествующий класс.
```java
public interface UnitMovement {
    void move();
}
```
```java
public class Fly implements UnitMovement {
    @Override
    public void move() {
        // реализаци полета
    }
}
```
```java
public class Swim implements UnitMovement {
    @Override
    public void move() {
        // реализаци плавания
    }
}
```
```java
public class Walk implements UnitMovement {
    @Override
    public void move() {
        // реализаци ходьбы
    }
}
```
И в итоге классу Unit не нужно реализовывать логику выбора метода передвижения, если мы добавим новый вид:
```java
public class Unit {
    public void makeMove(UnitMovement movement) {
        if (movement == null) {
            throw new UnsupportedOperationException("This operation doesn't supported yet.");
        }
        movement.move();
    }
}
```
Таким образом, класс закрыт для изменения, но открыт для расширения - нужно просто добавить новый класс и не нужно вносить
изменения в UnitMovement.