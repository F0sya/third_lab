package rpg.model.EnemyDir;

public class SkeletonMage extends SkeletonArcher {
    private int mana;

    public SkeletonMage() {
        super(60, 2.5, "Скелет-маг", 10 );
        this.mana = 50;
    }

    public SkeletonMage(int hp, double damageMultiplier, String name, int arrowsCount, int mana) {
        super(hp, damageMultiplier, name, arrowsCount);
        this.mana = mana;
    }

    public void castSpell() {
        if (mana >= 10) {
            mana -= 10;
            System.out.println(getName() + " кастує магію! Мана: " + mana);
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "SkeletonMage{" +
                "name='" + getName() + '\'' +
                ", hp=" + getHP() +
                ", weaponDurability=" + getWeapon().getDurability() +
                ", arrowsCount=" + getArrowsCount() +
                ", mana=" + mana +
                '}';
    }
}
