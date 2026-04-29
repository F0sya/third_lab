package org.example;

import org.example.Weapon;

import java.util.Objects;

import java.awt.*;

public class SkeletonWarrior implements Cloneable, Comparable<SkeletonWarrior> {

    private int hp;
    private int maxHP;
    private double damageMultiplier;
    private String name;
    private boolean isActive;

    private Weapon weapon;

    private int x;
    private int y;


    public SkeletonWarrior(){
        this(100,1.2,"Скелет-воїн");
    }

    public SkeletonWarrior(int hp, double damageMultiplier, String name){
        this.hp = hp;
        this.maxHP = hp;
        this.damageMultiplier = damageMultiplier;
        this.name = name;
        this.isActive = false;
        this.weapon = new Weapon(100);    }

    public void takeDamage(int amount){
        this.hp -= amount;
        if (this.hp < 0){ this.hp = 0;}
    }

    public void heal(int amount){
        this.hp += amount;
    }

    public void battleCry(){
        System.out.println(name + " клацає кістками!");
    }

    public void toggleActive() {
        this.isActive = !this.isActive;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }




    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getHP() {return hp;}
    public void setHP(int hp) {this.hp = hp;}

    public double getDamageMultiplier() {return damageMultiplier;}
    public void setDamageMultiplier(double damageMultiplier) {this.damageMultiplier = damageMultiplier;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public boolean isActive() {return isActive;}
    public void setActive(boolean active) {isActive = active;}

    public Weapon getWeapon() { return weapon; }

    public int getMaxHP() {return maxHP;}
    public void setMaxHP(int maxHP) { this.maxHP = maxHP;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkeletonWarrior that = (SkeletonWarrior) o;
        return hp == that.hp && Double.compare(that.damageMultiplier, damageMultiplier) == 0 && isActive == that.isActive && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "SkeletonWarrior{name='" + name + "', hp=" + hp + ", dmg=" + damageMultiplier + ", active=" + isActive + '}';
    }

    @Override
    public SkeletonWarrior clone() {
        try {
            SkeletonWarrior cloned = (SkeletonWarrior) super.clone();
            cloned.weapon = this.weapon.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(SkeletonWarrior other) {
        return this.name.compareTo(other.name);
    }
}
