
public class Player extends Creature {
    private int healIndex = 0;

    public Player() {
        super();
        setName("Player_" + formatForDateNow.format(dateNow));
    }

    public Player(String name, int attackPoint, int protectPoint, int healthPoint, int damagePointMin, int damagePointMax) {
        super(name, attackPoint, protectPoint, healthPoint, damagePointMin, damagePointMax);
    }

    public void healing() {
        if (isAlive()) {
            if (healthPoint != healthPointMax) {
                if (healIndex < 4) {
                    int healthRestored = ((int) Math.round(healthPointMax * 0.3));
                    if (healthPoint + healthRestored > healthPointMax) {
                        System.out.println("Существо " + getName() + " пополнило здоровье на " + (healthPointMax - healthPoint) + " единиц. Здоровье максимально");
                        healthPoint = healthPointMax;
                    } else {
                        System.out.println("Существо " + getName() + " пополнило здоровье на " + healthRestored + " единиц. Здоровья осталось " + healthPoint);
                    }
                    healIndex++;
                } else {
                    System.out.println("У существа " + getName() + " закончились аптечки");
                }
            } else {
                System.out.println("Здоровье у существа " + getName()+" максимально. Аптечки не использованы");
            }
        } else {
            System.out.println("Не удалось применить аптечку, т.к. существо " + getName() + " мертво");
        }
    }
}
