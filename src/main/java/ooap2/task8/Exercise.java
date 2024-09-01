package ooap2.task8;

import java.util.ArrayList;
import java.util.List;

class Unit {
}

class Monk extends Unit {
}

class Druid extends Monk {
}

public class Exercise {
    public static void main(String[] args) {
        List<Unit> units = new ArrayList<>();
        List<Monk> monks = new ArrayList<>();
        List<Druid> druids = new ArrayList<>();

        //инвариантность - жесткая привязка требуемых данных к конкретным типам
        castInvariance(units);// ошибка компиляции "incompatible types"
        castInvariance(monks);// код будет правильно компилироваться и работать
        castInvariance(druids);// ошибка компиляции "incompatible types"

        //ковариантность - возможность использовать в качестве данных коллекции содержащие экземпляры объектов наследников
        // или самого указанного класса.
        castCovariance(units);// ошибка компиляции "incompatible types"
        castCovariance(monks);// код будет правильно компилироваться и работать
        castCovariance(druids);// код будет правильно компилироваться и работать

        //контравариантность - возможность использовать в качестве данных коллекции содержащие экземпляры объектов предков
        // или самого указанного класса.
        castContravariance(units);// код будет правильно компилироваться и работать
        castContravariance(monks);// код будет правильно компилироваться и работать
        castContravariance(druids);// ошибка компиляции "incompatible types"
    }

    // тип Monk
    private static String castInvariance(List<Monk> units) {
        return "Invariant cast!";
    }

    // неуказанный тип расширяющий класс Monk
    private static String castCovariance(List<? extends Monk> units) {
        return "Covariance cast!";
    }

    // неуказанный тип являющийся предком класс Monk
    private static String castContravariance(List<? super Monk> units) {
        return "Contravariance cast!";
    }
}
