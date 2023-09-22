import java.awt.*;
import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

abstract public class Creature implements CreatureInterface {
    private String name;
    private int attackPoint;
    private int protectPoint;
    protected int healthPoint;
    protected int healthPointMax;
    private int damagePointMin;
    private int damagePointMax;
    private final Random rand = new Random();
    protected Date dateNow = new Date();
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss:SS");


    public String getName() {
        return name;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getProtectPoint() {
        return protectPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getHealthPointMax() {
        return healthPointMax;
    }

    public int getDamagePointMin() {
        return damagePointMin;
    }

    public int getDamagePointMax() {
        return damagePointMax;
    }

    public void setName(String name) {
        if(name != "") this.name = name;
        else {
            System.out.println("Ошибка. Поле имени не должно быть пустым");
            name = "Creature_" + formatForDateNow.format(dateNow);
        }
    }

    public void setAttackPoint(int attackPoint) {
        if (attackPoint > 0 & attackPoint <= 30) this.attackPoint = attackPoint;
        else {
            System.out.println("Ошибка. attackPoint не может быть меньше 1 и больше 30");
        }
    }

    public void setProtectPoint(int protectPoint) {
        if (protectPoint > 0 & protectPoint <= 30) this.protectPoint = protectPoint;
        else {
            System.out.println("Ошибка. protectPoint не может быть меньше 1 и больше 30");
        }
    }

    public void setHealthPoint(int healthPoint) {
        if (healthPoint >= 0) {
            this.healthPoint = healthPoint;
        } else {
            System.out.println("Ошибка. healthPoint не может быть меньше 0");
        }
    }

    public void setHealthPointMax(int healthPointMax) {
        if (healthPointMax > 0) {
            this.healthPointMax = healthPointMax;
        } else {
            System.out.println("Ошибка. healthPointMax не может быть меньше 1");
        }
    }

    public void setDamagePointRange(int damagePointMin, int damagePointMax) {
        if (damagePointMin >= 0 & damagePointMin <= damagePointMax) {
            this.damagePointMin = damagePointMin;
            this.damagePointMax = damagePointMax;
        } else {
            System.out.println("Ошибка. Некорректные входные данные");
        }
    }

    public Creature() {
        name = "Creature_" + formatForDateNow.format(dateNow);
        attackPoint = rand.nextInt(1, 31);
        protectPoint = rand.nextInt(1, 31);
        healthPoint = 100;
        healthPointMax = healthPoint;
        damagePointMin = 1;
        damagePointMax = 40;
    }

    public Creature(String name, int attackPoint, int protectPoint, int healthPoint, int damagePointMin, int damagePointMax) {
        this.name = name;
        if (attackPoint > 0 & attackPoint <= 30) this.attackPoint = attackPoint;
        else {
            System.out.println("Ошибка. attackPoint не может быть меньше 1 или больше 30. Значение будет установленно случайно в диапазоне от 1 до 30.");
            this.attackPoint = rand.nextInt(1, 31);
        }
        if (protectPoint > 0 & protectPoint <= 30) this.protectPoint = protectPoint;
        else {
            System.out.println("Ошибка. protectPoint не может быть меньше 1 или больше 30. Значение будет установленно случайно в диапазоне от 1 до 30.");
            this.protectPoint = rand.nextInt(1, 31);
        }
        if (healthPoint >= 0) this.healthPoint = healthPoint;
        else {
            System.out.println("Ошибка. healthPoint не может быть меньше 0. Значение будет установленно на 100.");
            this.healthPoint = 100;
        }
        healthPointMax = healthPoint;
        if (damagePointMin >= 0 & damagePointMin <= damagePointMax) {
            this.damagePointMin = damagePointMin;
            this.damagePointMax = damagePointMax;
        } else {
            System.out.println("Ошибка. Некорректные входные данные. Значение минимума будет установлено на 1, а максимума на 40");
        }
    }

    private int modifierCount(Creature enemy) {
        if (enemy.isAlive() & isAlive()) {
            int difference = attackPoint - (enemy.protectPoint + 1);
            if (difference <= 0) return 1;
            return difference;
        }
        return 0;
    }

    private boolean isAttackSuccess(Creature enemy) {
        int N = modifierCount(enemy);
        if (N > 0) {
            for (int i = 1; i <= N; i++) {
                int diceValue = rand.nextInt(1, 7);
                if (diceValue == 5 || diceValue == 6) {
                    return true;
                }
            }
        }
        return false;
    }

    public void hit(Creature enemy) {
        if (isAttackSuccess(enemy)) {
            int damage = rand.nextInt(damagePointMin, damagePointMax+1);
            enemy.healthPoint = enemy.healthPoint - damage;
            System.out.println("Существо " + enemy.name + " атаковано существом " + name + " и получило " + damage + " урона.");
            if (!enemy.isAlive()) {
                System.out.println("Существо " + enemy.name + " погибло");
            }
        } else {
            System.out.println("Атака существом "+ name + " по существу " + enemy.name + " не удалась");
        }
    }

    public boolean isAlive() {
        return healthPoint > 0;
    }


}
