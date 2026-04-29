package rpg.model.EnvironmentDir;

import rpg.model.EnemyDir.SkeletonWarrior;

import java.util.ArrayList;
import java.util.List;

public class DungeonRoom {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private List<SkeletonWarrior> enemies;

    public DungeonRoom(int x, int y, int width, int height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.enemies = new ArrayList<>();
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

    public void performDarkRitual() {
        int d20Roll = (int) (Math.random() * 20) + 1;
        System.out.println("Кімната проводить Темний ритуал! На d20 випало: " + d20Roll);

        for (SkeletonWarrior enemy : enemies) {
            int newHp = enemy.getHP() + d20Roll;
            if (newHp > enemy.getMaxHP()) {
                newHp = enemy.getMaxHP();
            }
            enemy.setHP(newHp);

            if (d20Roll == 20) {
                System.out.println("Критичний успіх! Зброя " + enemy.getName() + " повністю відновлена!");
                enemy.getWeapon().setDurability(100);
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
        return name + " (Dungeon Room) [" + x + "," + y + "]";
    }
}
