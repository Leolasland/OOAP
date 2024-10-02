package ooap2.task16;

class Unit {
    public void cast() {
        System.out.println("cast unit");
    }
}

class Monk extends Unit {
    @Override
    public void cast() {
        System.out.println("cast monk");
    }
}

class Druid extends Monk {
    @Override
    public void cast() {
        System.out.println("cast druid");
    }
}

public class Exercise {
    public static void main(String[] args) {
        Unit unit = new Unit();
        Unit monk = new Monk();
        Unit druid = new Druid();
        covariant(unit);
        covariant(monk);
        covariant(druid);

        polymorphic(unit);
        polymorphic(monk);
        polymorphic(druid);
    }

    public static  <T extends Unit> void covariant(T value) {
        value.cast();
    }

    public static void polymorphic(Unit value) {
        value.cast();
    }
}

