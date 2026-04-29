package rpg.model.EnvironmentDir;

import rpg.model.EnemyDir.SkeletonWarrior;
import rpg.model.EnemyDir.SkeletonMage;

import java.util.ArrayList;
import java.util.List;

public class MagicDungeonRoom {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private List<SkeletonWarrior> enemies;
    private List<String> magicalRiddles;

    public MagicDungeonRoom(int x, int y, int width, int height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.enemies = new ArrayList<>();
        this.magicalRiddles = new ArrayList<>();
    }

    public void addEnemy(SkeletonWarrior enemy) {
        this.enemies.add(enemy);
    }

    public void removeEnemy(SkeletonWarrior enemy) {
        this.enemies.remove(enemy);
    }

    public List<SkeletonWarrior> getEnemies() {
        return enemies;
    }

    public void addRiddle(String riddle) {
        this.magicalRiddles.add(riddle);
    }

    public List<String> getMagicalRiddles() {
        return magicalRiddles;
    }

    public void performMagicRitual() {
        int d20Roll = (int) (Math.random() * 20) + 1;
        System.out.println("Магічна кімната проводить ритуал! На d20 випало: " + d20Roll);

        for (SkeletonWarrior enemy : enemies) {
            int newHp = enemy.getHP() + d20Roll;
            if (newHp > enemy.getMaxHP()) {
                newHp = enemy.getMaxHP();
            }
            enemy.setHP(newHp);

            if (enemy instanceof SkeletonMage) {
                SkeletonMage mage = (SkeletonMage) enemy;
                int newMana = mage.getMana() + d20Roll;
                mage.setMana(newMana);

                if (d20Roll == 20) {
                    System.out.println("Критичний успіх! Мана " + mage.getName() + " повністю відновлена!");
                    mage.setMana(100);
                }
            }
        }
    }

    public int getX() { return x; }
    public void setX(int x) {this.x = x; }

    public int getY() { return y; }
    public void setY(int y) {this.y = y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    @Override
    public String toString() {
        return name + " (Magic Room) [" + x + "," + y + "]";
    }
}
