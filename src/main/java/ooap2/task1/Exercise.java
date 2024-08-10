package ooap2.task1;

// Класс Расы для демонстрации композиции
class Race {

    private final String name;

    private final String home;

    private final int power;
    private final int agility;
    private final int intelligence;

    public Race(String name, String home, int power, int agility, int intelligence) {
        this.name = name;
        this.home = home;
        this.power = power;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getHome() {
        return home;
    }
}

// Базовый класс Юнитов, каждый Юнит имеет Расу
abstract class Unit {

    private String name;

    private Race race; // композиция

    private int basePower;
    private int baseAgility;
    private int baseIntelligence;

    public Unit(Race race, int basePower, int baseAgility, int baseIntelligence, String name) {
        this.race = race;
        this.basePower = basePower;
        this.baseAgility = baseAgility;
        this.baseIntelligence = baseIntelligence;
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getPower() {
        return basePower * race.getPower();
    }

    public int getAgility() {
        return baseAgility * race.getAgility();
    }

    public int getIntelligence() {
        return baseIntelligence * race.getIntelligence();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public void setBaseAgility(int baseAgility) {
        this.baseAgility = baseAgility;
    }

    public void setBaseIntelligence(int baseIntelligence) {
        this.baseIntelligence = baseIntelligence;
    }

    public String greeting() {
        return "I'm " + name + "! I belong to the " + race.getName() + " race.";
    }

    void backHome() {
        System.out.println("Back to " + getRace().getHome());
    }
}

// Подкласс Monk, наследующийся от Персонажа
class Monk extends Unit { // наследование

    public Monk(Race race, int basePower, int baseAgility, int baseIntelligence, String name) {
        super(race, basePower, baseAgility, baseIntelligence, name);
    }

    @Override
    public String greeting() { // переопределение
        return super.greeting() + " I'm monk.";
    }
}

// Подкласс Druid, наследующийся от Персонажа
class Druid extends Unit { // наследование

    private String spell;

    public Druid(Race race, int basePower, int baseAgility, int baseIntelligence, String name, String spell) {
        super(race, basePower, baseAgility, baseIntelligence, name);
        this.spell = spell;
    }


    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    @Override
    public String greeting() { // переопределение
        return super.greeting() + " I'm druid.";
    }
}


public class Exercise {
    public static void main(String[] args) {
        Race human = new Race("Humans", "Castle", 2, 1, 1);
        Unit unit1 = new Monk(human, 1, 1, 2, "Inquisitor");

        Race troll = new Race("Elves", "Stronghold", 1, 2, 2);
        Unit unit2 = new Druid(troll, 1, 1, 2, "High Druid", "Stone Skin");

        Unit[] units = new Unit[]{unit1, unit2};
        for (Unit unit : units) {
            // Полиморфизм: использование объектов Monk и Druid как Персонажей
            System.out.println(unit.greeting());
            //При этом у Druid есть spell, которого нет у Monk
            if (unit instanceof Druid druid) {
                System.out.println("Druid have spell: " + druid.getSpell());
            }
        }

        // Юнит имеет Расу, которая параметризует поведение
        unit1.backHome();
        unit2.backHome();
    }
}
