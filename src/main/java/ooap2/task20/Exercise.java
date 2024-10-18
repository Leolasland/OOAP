package ooap2.task20;

//Наследование с функциональной вариацией (functional variation inheritance)
class Unit {
    void cast() {
        System.out.println("cast unit");
    }
}

class Monk extends Unit {
    @Override
    void cast() {
        System.out.println("cast monk");
    }
}

//Наследование с вариацией типа (type variation inheritance)
class Druid extends Unit {
    void cast(String spell) {
        System.out.println("cast druid " + spell);
    }
}

//Наследование с конкретизацией (reification inheritance)
abstract class Warrior {
    abstract void hit();
}

class Knight extends Warrior {
    @Override
    void hit() {
        System.out.println("hit knight");
    }
}

//Структурное наследование (structure inheritance)
interface CastingSpell {
    void cast();
}

class FireballMechanics implements CastingSpell {
    private String message;

    public FireballMechanics(String message) {
        this.message = message;
    }

    @Override
    public void cast() {
        System.out.println(message);
    }
}