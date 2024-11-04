У юнитов могут быть разные классы - воины, маги и т.д. У каждого типа могут быть свои основные атрибуты - сила, интеллект,  
ловкость. При этом существуют классы которые совмещают сразу два основных атрибута - например рыцарь-маг с основными атрибутами 
сила и интеллект.

```java
abstract class PowerType {
    private String name;

    public PowerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Strength extends PowerType {
    public Strength() {
        super("сила");
    }
    // Специфические методы для юнита с основным типом - сила
}

class Intelligence extends PowerType {
    public Intelligence() {
        super("интеллект");
    }

    // Специфические методы для юнита с основным типом - интеллект
}

class Agility extends PowerType {
    public Agility() {
        super("ловкость");
    }

    // Специфические методы для юнита с основным типом - ловкость
}


interface Unit {
    PowerType[] powerType;
}

class Wizard implements Unit {
    private final PowerType[] powerType;
    
    public Wizard(PowerType[] powerType) {
        this.powerType = powerType;
    }
    
    public PowerType[] getPowerType() {
        return powerType;
    }
    // ... Специфические методы
}

class Warrior implements Unit {
    private final PowerType[] powerType;
    
    public Warrior(PowerType[] powerType) {
        this.powerType = powerType;
    }

    public PowerType[] getPowerType() {
        return powerType;
    }
    // ... Специфические методы
}

class MysticKnight implements Unit {
    private final PowerType[] powerType;
    
    public Warrior(PowerType[] powerType) {
        this.powerType = powerType;
    }

    public PowerType[] getPowerType() {
        return powerType;
    }
    // ... Специфические методы
}
```
