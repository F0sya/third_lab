package org.example;


import java.util.Objects;


public class SkeletonMage extends SkeletonArcher {
    private int mana;

    public SkeletonMage() {

        super(60, 2.5, "Скелет-маг", 10 );
        this.mana = 50;
    }

    public SkeletonMage(int hp, double damageMultiplier, String name,int arrowsCount, int mana) {
        // Передаємо параметри в конструктор SkeletonArcher
        super(hp, damageMultiplier, name, arrowsCount);
        // Встановлюємо значення для мага
        this.mana = mana;
    }

    public void castSpell() {
        if (mana >= 10) {
            mana -= 10;
            System.out.println(getName() + " кастує магію! Мана: " + mana);
        }
    }

    @Override
    public String toString() {
        return "SkeletonMage{" +
                "name='" + getName() + '\'' +
                ", hp=" + getHP() +
                ", arrowsCount=" + getArrowsCount() +
                ", mana=" + mana + // Ось твоє нове поле!
                '}';
    }


}