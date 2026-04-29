package rpg.model.EnemyDir;

public class SkeletonArcher extends SkeletonWarrior {
    private int arrowsCount;

    public SkeletonArcher() {
        this(80, 1.5, "Скелет-лучник", 10);
    }

    public SkeletonArcher(int hp, double damageMultiplier, String name, int arrowsCount) {
        super(hp, damageMultiplier, name);
        this.arrowsCount = arrowsCount;
    }

    public void shootArrow() {
        if (arrowsCount > 0) {
            arrowsCount--;
            System.out.println(getName() + " стріляє! Стріл: " + arrowsCount);
        }
    }

    public int getArrowsCount(){
        return arrowsCount;
    }
    public void setArrowsCount(int arrowsCount){
        this.arrowsCount = arrowsCount;
    }

    @Override
    public String toString() {
        return "SkeletonArcher{" +
                "name='" + getName() + '\'' +
                ", hp=" + getHP() +
                ", weaponDurability=" + getWeapon().getDurability() +
                ", arrowsCount=" + arrowsCount +
                '}';
    }
}
