package ooap2.task7;

// Базовый класс Юнитов
class Unit {

    public String castStoneSkin() {
        return "Stone skin on me!";
    }

}

// Подкласс Monk, наследующийся от Персонажа
class Monk extends Unit { // наследование

    public Monk() {
        super();
    }

    // переопределение
    @Override
    public String castStoneSkin() { // переопределение
        return "Stone skin on Monk!";
    }
}

// Подкласс Druid, наследующийся от Персонажа
class Druid extends Unit { // наследование

    // переопределение
    @Override
    public String castStoneSkin() { // переопределение
        return "Stone skin on Druid!";
    }
}


public class Exercise {
    public static void main(String[] args) {
        Unit unit = new Unit();
        Unit unit1 = new Monk();
        Unit unit2 = new Druid();

        Unit[] units = new Unit[]{unit, unit1, unit2};
        for (Unit u : units) {
            System.out.println(u.castStoneSkin());
        }
    }
}
