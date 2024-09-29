package ooap2.task15;

abstract class Unit {
}

class Monk extends Unit {
    public boolean canCast;
    public Monk(boolean canCast) {
        this.canCast = canCast;
    }
}

class Wizard extends Unit {
    public boolean canCast = true;
}

class Warrior extends Unit {
}

class Druid extends Unit {
    public Wizard canCast;
    public Druid(Wizard canCast) {
        this.canCast = canCast;
    }
}

