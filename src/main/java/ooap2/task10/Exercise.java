package ooap2.task10;

class Unit {
    // метод не может быть переопределен в наследниках
    public final String castNoOverridable() {
        return "Cast No Overridable!";
    }

    // метод может быть переопределен в наследниках
    public String castOverridable() {
        return "Cast Overridable!";
    }
}

class Monk extends Unit {
    // Ошибка: castNoOverridable()' cannot override 'castNoOverridable()' in 'ooap2.task10.Unit'; overridden method is final
//     @Override
//     public String castNoOverridable() {
//         return "Cast No Overridable!";
//     }

    // метод успешно переопределяет метод из класса-предка
    @Override
    public String castOverridable() {
        return "Monk Cast Overridable!";
    }
}

public class Exercise {
    public static void main(String[] args) {
        Unit unit = new Unit();
        System.out.println(unit.castNoOverridable());
        System.out.println(unit.castOverridable());
    }
}
