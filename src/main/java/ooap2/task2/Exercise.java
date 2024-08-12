package ooap2.task2;

// Базовый класс Юнитов
abstract class Unit {

    public String castStoneSkin() {
        return "Stone skin on me!";
    }

}

// Подкласс Monk, наследующийся от Персонажа
class Monk extends Unit { // наследование

    public Monk() {
        super();
    }

    // наследник специализирует (изменяет поведение) класса-родителя
    @Override
    public String castStoneSkin() { // переопределение
        return super.castStoneSkin() + " And Monk casts Stone skin for all units!";
    }
}

// Подкласс Druid, наследующийся от Персонажа
class Druid extends Unit { // наследование

    // наследник расширяет класс-родитель, т.к. у него есть дополнительные методы, которых нет у класса-родителя
    public String castLightning() {
        return "Lightning!";
    }
}


public class Exercise {
    public static void main(String[] args) {
        Unit unit1 = new Monk();
        Unit unit2 = new Druid();

        Unit[] units = new Unit[]{unit1, unit2};
        for (Unit unit : units) {
            System.out.println(unit.castStoneSkin());
            if (unit instanceof Druid druid) {
                System.out.println("Druid have spell: " + druid.castLightning());
            }
        }
    }
}
