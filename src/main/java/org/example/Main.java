package org.example;

import rpg.model.EnemyDir.SkeletonArcher;
import rpg.model.EnemyDir.SkeletonMage;
import rpg.model.EnemyDir.SkeletonWarrior;
import rpg.model.EnvironmentDir.DungeonRoom;
import rpg.model.EnvironmentDir.MagicDungeonRoom;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static DungeonRoom dungeonRoom = new DungeonRoom(0, 0, 500, 500, "Dark Abyss");
    private static MagicDungeonRoom magicRoom = new MagicDungeonRoom(600, 0, 500, 500, "Crystal Sanctum");

    static void main() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        List<SkeletonWarrior> enemies = new ArrayList<>();

        System.out.print("Введіть початкову кількість об'єктів для створення: ");
        int initialCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < initialCount; i++) {
            System.out.println("Створення об'єкта " + (i + 1) + ":");
            System.out.println("1. SkeletonWarrior, 2. SkeletonArcher, 3. SkeletonMage");
            int type = Integer.parseInt(scanner.nextLine());
            System.out.print("Ім'я: ");
            String name = scanner.nextLine();
            System.out.print("HP: ");
            int hp = Integer.parseInt(scanner.nextLine());

            SkeletonWarrior e;
            if (type == 2)
                e = new SkeletonArcher(hp, 1.5, name, 10);
            else if (type == 3)
                e = new SkeletonMage(hp, 2.0, name, 5, 20);
            else
                e = new SkeletonWarrior(hp, 1.2, name);
            enemies.add(e);
        }

        while (running) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Вивести всі об'єкти (4.1)");
            System.out.println("2. Передивитись і-й об'єкт (4.2)");
            System.out.println("3. Додати на i-y позицію (4.3)");
            System.out.println("4. Видалити i-y позицію (4.4)");
            System.out.println("5. Сортування(двома способами) (4.5)");
            System.out.println("6. Глибоке копіювання зі зміною (4.6)");
            System.out.println("7. Бінарний пошук (4.7)");
            System.out.println("8. Поділ на категорії (4.8)");
            System.out.println("9. Змагання (4.8)");
            System.out.println("10. Додати мікрооб'єкт до макрооб'єкта (6.1)");
            System.out.println("11. Вивести вміст макрооб'єктів та універсального об'єкта (6.2)");
            System.out.println("12. Взаємодія мікрооб'єктів (6.3)");
            System.out.println("13. Взаємодія макрооб'єктів (6.4)");
            System.out.println("14. Підрахунок мікрооб'єктів (6.5)");
            System.out.println("15. Видалити мікрооб'єкт за критерієм (6.6)");
            System.out.println("0. Вихід");
            System.out.println("Ваш вибір: ");

            String choice = scanner.nextLine();
            int user_choice;

            switch (choice) {
                case "1":
                    printList(enemies);
                    break;
                case "2":
                    System.out.println("Введіть значення і:");
                    user_choice = scanner.nextInt();
                    scanner.nextLine();

                    if (user_choice < 0 || user_choice > enemies.size() - 1) {
                        System.out.println("Ви вийшли за рамки!");
                        break;
                    }

                    SkeletonWarrior selected = enemies.get(user_choice);

                    System.out.println("Вибраний елемент:");
                    System.out.println(selected);

                    break;
                case "3":
                    appendList(enemies, scanner);

                    break;
                case "4":
                    removeObject(enemies, scanner);
                    break;
                case "5":
                    sortEnemies(enemies, scanner);
                    break;
                case "6":
                    makeCopy(enemies, scanner);
                    printList(enemies);
                    scanner.nextLine();
                    break;
                case "7":
                    binarySearch(enemies, scanner);
                    scanner.nextLine();
                    break;
                case "8":
                    filterAndRemove(enemies, scanner);
                    scanner.nextLine();
                    break;
                case "9":
                    Competition(enemies);
                    scanner.nextLine();
                    break;
                case "10":
                    addToSpecificList(enemies, scanner);
                    break;
                case "11":
                    displayAllContent(enemies);
                    break;
                case "12":
                    interactMicroObjects(enemies, scanner);
                    break;
                case "13":
                    interactMacroObjects(enemies);
                    break;
                case "14":
                    countByCriteria(enemies, scanner);
                    break;
                case "15":
                    removeFromSpecificList(enemies, scanner);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Неправильне введення!");
                    break;
            }

        }

    }

    static void printList(List<SkeletonWarrior> enemies) {
        if (enemies.isEmpty()) {
            System.out.println("Список пустий!");
            return;
        }
        System.out.println("Поточний масив: ");

        for (SkeletonWarrior enemy : enemies) {
            System.out.println(enemy);
        }
    }

    static void appendList(List<SkeletonWarrior> enemies, Scanner scanner) {
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos < 0 || pos > enemies.size()) {
            System.out.println("Помилка");
            return;
        }

        System.out.println("Який тип об'єкта створити?");
        System.out.println("1. SkeletonWarrior");
        System.out.println("2. SkeletonArcher");
        System.out.println("3. SkeletonMage");
        System.out.print("Ваш вибір: ");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Введіть ім'я: ");
        String name = scanner.nextLine();
        System.out.print("Введіть HP: ");
        int hp = Integer.parseInt(scanner.nextLine());
        System.out.print("Введіть множник шкоди (напр. 1.5): ");
        double dmg = Double.parseDouble(scanner.nextLine());

        int arrows;

        SkeletonWarrior newEnemy = null;

        switch (type) {
            case 1:
                newEnemy = new SkeletonWarrior(hp, dmg, name);
                break;
            case 2:
                System.out.println("Введіть к-сть стріл:");
                arrows = Integer.parseInt(scanner.nextLine());
                newEnemy = new SkeletonArcher(hp, dmg, name, arrows);
                break;
            case 3:
                System.out.println("Введіть к-сть стріл:");
                arrows = Integer.parseInt(scanner.nextLine());
                System.out.print("Введіть кількість мани: ");
                int mana = Integer.parseInt(scanner.nextLine());
                newEnemy = new SkeletonMage(hp, dmg, name, arrows, mana);
                break;
            default:
                System.out.println("Помилка");
                return;
        }

        if (newEnemy != null) {
            enemies.add(pos, newEnemy);
            System.out.println("Об'єкт додано на позицію!");
        }
    }

    private static void removeObject(List<SkeletonWarrior> enemies, Scanner scanner) {
        if (enemies.isEmpty()) {
            System.out.println("Помилка: список порожній, нічого видаляти.");
            return;
        }

        System.out.print("Введіть номер (індекс) об'єкта для видалення: ");

        try {
            int index = Integer.parseInt(scanner.nextLine());

            if (index >= 0 && index < enemies.size()) {
                SkeletonWarrior removed = enemies.remove(index);
                System.out.println("Об'єкт [" + removed.getName() + "] успішно видалено!");
            } else {
                System.out.println("Помилка: об'єкта з таким індексом не існує.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть ціле число!");
        }
    }

    private static void sortEnemies(List<SkeletonWarrior> enemies, Scanner scanner) {
        if (enemies.isEmpty()) {
            System.out.println("Список порожній!");
            return;
        }

        System.out.println("Оберіть тип сортування:");
        System.out.println("1. За іменем (Comparable)");
        System.out.println("2. За HP (Анонімний клас)");
        System.out.print("Ваш вибір: ");

        String sortChoice = scanner.nextLine();

        if (sortChoice.equals("1")) {
            Collections.sort(enemies);
            System.out.println("Відсортовано за іменем.");
        } else if (sortChoice.equals("2")) {
            enemies.sort(new Comparator<SkeletonWarrior>() {
                @Override
                public int compare(SkeletonWarrior s1, SkeletonWarrior s2) {
                    return Integer.compare(s1.getHP(), s2.getHP());
                }
            });
            System.out.println("Відсортовано за рівнем HP.");
        } else {
            System.out.println("Неправильний вибір!");
        }
    }

    private static void makeCopy(List<SkeletonWarrior> enemies, Scanner scanner) {
        if (enemies.isEmpty()) {
            System.out.println("Список порожній!");
            return;
        }

        System.out.println("Введіть індекс об'єкта для копіювання:");

        int copyChoice = scanner.nextInt();

        if (copyChoice < 0 || copyChoice >= enemies.size()) {
            System.out.println("Некоректний індекс!");
            return;
        }

        SkeletonWarrior original = enemies.get(copyChoice);
        SkeletonWarrior copy = original.clone();

        enemies.add(copy);

        System.out.println("Демонстрація глибокого копіювання:");
        System.out.println("Оригінал перед зміною зброї: " + original.getWeapon().getDurability());
        System.out.println("Копія перед зміною зброї: " + copy.getWeapon().getDurability());

        original.getWeapon().setDurability(50);
        System.out.println("\nЗмінено міцність зброї оригіналу на 50.");
        System.out.println("Оригінал: " + original.getWeapon().getDurability());
        System.out.println("Копія (має залишитись 100): " + copy.getWeapon().getDurability());

        if (original.getWeapon().getDurability() != copy.getWeapon().getDurability()) {
            System.out.println("\nГлибоке копіювання підтверджено!");
        } else {
            System.out.println("\nПомилка: копіювання поверхневе!");
        }

    }

    private static void binarySearch(List<SkeletonWarrior> enemies, Scanner scanner) {
        Comparator<SkeletonWarrior> hpComparator = new Comparator<SkeletonWarrior>() {
            @Override
            public int compare(SkeletonWarrior s1, SkeletonWarrior s2) {
                return Integer.compare(s1.getHP(), s2.getHP());
            }
        };

        enemies.sort(hpComparator);

        System.out.println("Масив після сортування: ");
        printList(enemies);

        System.out.println("\nВведіть к-сть HP для пошуку:");

        int searchHP = scanner.nextInt();

        SkeletonWarrior target = new SkeletonWarrior(searchHP, 1, "Template");

        int index = Collections.binarySearch(enemies, target, hpComparator);

        if (index >= 0) {
            System.out.println("Усі позиції з таким HP:");

            int left = index;
            while (left >= 0 && hpComparator.compare(enemies.get(left), target) == 0) {
                System.out.print(left + " ");
                left--;
            }

            int right = index + 1;
            while (right < enemies.size() && hpComparator.compare(enemies.get(right), target) == 0) {
                System.out.print(right + " ");
                right++;
            }

            System.out.println();
        } else {
            System.out.println("Об'єкт з таким HP не знайдено");
        }
    }

    private static void filterAndRemove(List<SkeletonWarrior> enemies, Scanner scanner) {
        if (enemies.isEmpty()) {
            System.out.println("Масив порожній.");
            return;
        }

        System.out.println("\nВведіть порогове значення HP для поділу на категорії:");
        int threshold = scanner.nextInt();

        System.out.println("Яку категорію об'єктів ВИДАЛИТИ?");
        System.out.println("1 - Слабкі (HP < " + threshold + ")");
        System.out.println("2 - Сильні (HP >= " + threshold + ")");
        System.out.print("Ваш вибір: ");
        int choice = scanner.nextInt();

        int initialSize = enemies.size();

        Comparator<SkeletonWarrior> hpComparator = new Comparator<SkeletonWarrior>() {
            @Override
            public int compare(SkeletonWarrior s1, SkeletonWarrior s2) {
                return Integer.compare(s1.getHP(), s2.getHP());
            }
        };

        if (choice == 1) {
            enemies.removeIf(enemy -> enemy.getHP() < threshold);
            System.out.println("Категорію 'Слабкі' видалено.");
        } else if (choice == 2) {
            enemies.removeIf(enemy -> enemy.getHP() >= threshold);
            System.out.println("Категорію 'Сильні' видалено.");
        } else {
            System.out.println("Невірний вибір.");
            return;
        }

        System.out.println("Видалено об'єктів: " + (initialSize - enemies.size()));
        System.out.println("Поточний масив після очищення:");
        printList(enemies);
    }

    private static void Competition(List<SkeletonWarrior> enemies) {
        if (enemies.size() < 2) {
            System.out.println("Недостатньо об'єктів для змагання (мінімум 2).");
            return;
        }

        if (enemies.size() % 2 != 0) {
            System.out.println("Увага: кількість об'єктів непарна (" + enemies.size() + ").");
            System.out.println("Для чесного поділу на команди потрібна парна кількість.");
            return;
        }

        int half = enemies.size() / 2;

        List<SkeletonWarrior> team1 = new ArrayList<>(enemies.subList(0, half));
        List<SkeletonWarrior> team2 = new ArrayList<>(enemies.subList(half, enemies.size()));

        System.out.println("\n=== СКЛАД ПЕРШОЇ КОМАНДИ ===");
        printList(team1);

        System.out.println("\n=== СКЛАД ДРУГОЇ КОМАНДИ ===");
        printList(team2);

        int power1 = 0;
        for (SkeletonWarrior s : team1) {
            power1 += s.getHP();
        }

        int power2 = 0;
        for (SkeletonWarrior s : team2) {
            power2 += s.getHP();
        }

        System.out.println("\n--- РЕЗУЛЬТАТ ЗМАГАННЯ ---");
        System.out.println("Сумарна сила Першої команди: " + power1);
        System.out.println("Сумарна сила Другої команди: " + power2);

        if (power1 > power2) {
            System.out.println("Результат: ПЕРША КОМАНДА ВИГРАЛА!");
        } else if (power2 > power1) {
            System.out.println("Результат: ДРУГА КОМАНДА ВИГРАЛА!");
        } else {
            System.out.println("Результат: НІЧИЯ!");
        }

        System.out.println("--------------------------");
    }

    private static void addToSpecificList(List<SkeletonWarrior> universal, Scanner scanner) {
        System.out.println("Оберіть куди додати:");
        System.out.println("1. Універсальний об'єкт (enemies)");
        System.out.println("2. DungeonRoom (" + dungeonRoom + ")");
        System.out.println("3. MagicDungeonRoom (" + magicRoom + ")");
        int dest = Integer.parseInt(scanner.nextLine());

        System.out.println("Який тип об'єкта створити?");
        System.out.println("1. SkeletonWarrior");
        System.out.println("2. SkeletonArcher");
        System.out.println("3. SkeletonMage");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Введіть ім'я: ");
        String name = scanner.nextLine();
        System.out.print("Введіть HP: ");
        int hp = Integer.parseInt(scanner.nextLine());
        System.out.print("Введіть множник шкоди: ");
        double dmg = Double.parseDouble(scanner.nextLine());

        SkeletonWarrior newEnemy;
        switch (type) {
            case 2:
                System.out.print("Стріли: ");
                int arrows = Integer.parseInt(scanner.nextLine());
                newEnemy = new SkeletonArcher(hp, dmg, name, arrows);
                break;
            case 3:
                System.out.print("Стріли: ");
                int ar = Integer.parseInt(scanner.nextLine());
                System.out.print("Мана: ");
                int mana = Integer.parseInt(scanner.nextLine());
                newEnemy = new SkeletonMage(hp, dmg, name, ar, mana);
                break;
            default:
                newEnemy = new SkeletonWarrior(hp, dmg, name);
                break;
        }

        if (dest == 2)
            dungeonRoom.addEnemy(newEnemy);
        else if (dest == 3)
            magicRoom.addEnemy(newEnemy);
        else
            universal.add(newEnemy);

        System.out.println("Об'єкт додано!");
    }

    private static void displayAllContent(List<SkeletonWarrior> universal) {
        System.out.println("\n=== ВМІСТ УСІХ ОБ'ЄКТІВ ===");
        System.out.println("--- Універсальний список ---");
        for (SkeletonWarrior s : universal)
            System.out.println(s);
        System.out.println("--- " + dungeonRoom + " ---");
        for (SkeletonWarrior s : dungeonRoom.getEnemies())
            System.out.println(s);
        System.out.println("--- " + magicRoom + " ---");
        for (SkeletonWarrior s : magicRoom.getEnemies())
            System.out.println(s);
        System.out.println("============================\n");
    }

    private static void interactMicroObjects(List<SkeletonWarrior> universal, Scanner scanner) {
        List<SkeletonWarrior> all = getAllEnemies(universal);
        if (all.size() < 2) {
            System.out.println("Недостатньо об'єктів для взаємодії!");
            return;
        }

        System.out.println("Оберіть двох ворогів за індексом (0-" + (all.size() - 1) + "):");
        for (int i = 0; i < all.size(); i++)
            System.out.println(i + ": " + all.get(i));

        int i1 = Integer.parseInt(scanner.nextLine());
        int i2 = Integer.parseInt(scanner.nextLine());

        SkeletonWarrior s1 = all.get(i1);
        SkeletonWarrior s2 = all.get(i2);

        System.out.println("Взаємодія: " + s1.getName() + " атакує " + s2.getName());
        int damage = (int) (10 * s1.getDamageMultiplier());
        s2.takeDamage(damage);
        System.out.println(s2.getName() + " отримав " + damage + " шкоди. Поточне HP: " + s2.getHP());

        if (s2.getHP() <= 0) {
            System.out.println(s2.getName() + " знищений!");
            removeFromAllSources(universal, s2);
        }
    }

    private static void interactMacroObjects(List<SkeletonWarrior> universal) {
        System.out.println("Взаємодія макрооб'єктів: " + dungeonRoom.toString() + " та " + magicRoom.toString());
        System.out.println("Ритуал обміну: найсильніші воїни переходять між кімнатами.");

        SkeletonWarrior strongestInDungeon = findStrongest(dungeonRoom.getEnemies());
        SkeletonWarrior strongestInMagic = findStrongest(magicRoom.getEnemies());

        if (strongestInDungeon != null) {
            dungeonRoom.removeEnemy(strongestInDungeon);
            magicRoom.addEnemy(strongestInDungeon);
            System.out.println(strongestInDungeon.getName() + " перейшов до Magic Room");
        }
        if (strongestInMagic != null) {
            magicRoom.removeEnemy(strongestInMagic);
            dungeonRoom.addEnemy(strongestInMagic);
            System.out.println(strongestInMagic.getName() + " перейшов до Dungeon Room");
        }
    }

    private static void countByCriteria(List<SkeletonWarrior> universal, Scanner scanner) {
        System.out.println("Критерій підрахунку:");
        System.out.println("1. HP більше ніж X");
        System.out.println("2. Певний тип (Warrior, Archer, Mage)");
        System.out.println("3. Довжина імені більше ніж Y");
        int crit = Integer.parseInt(scanner.nextLine());

        int countUniv = 0, countDung = 0, countMag = 0;

        if (crit == 1) {
            System.out.print("Введіть X: ");
            int x = Integer.parseInt(scanner.nextLine());
            countUniv = (int) universal.stream().filter(s -> s.getHP() > x).count();
            countDung = (int) dungeonRoom.getEnemies().stream().filter(s -> s.getHP() > x).count();
            countMag = (int) magicRoom.getEnemies().stream().filter(s -> s.getHP() > x).count();
        } else if (crit == 2) {
            System.out.print("Тип (1-Warrior, 2-Archer, 3-Mage): ");
            int t = Integer.parseInt(scanner.nextLine());
            Class<?> targetClass = (t == 2) ? SkeletonArcher.class
                    : (t == 3 ? SkeletonMage.class : SkeletonWarrior.class);
            countUniv = (int) universal.stream().filter(s -> targetClass.isInstance(s)).count();
            countDung = (int) dungeonRoom.getEnemies().stream().filter(s -> targetClass.isInstance(s)).count();
            countMag = (int) magicRoom.getEnemies().stream().filter(s -> targetClass.isInstance(s)).count();
        } else {
            System.out.print("Введіть Y: ");
            int y = Integer.parseInt(scanner.nextLine());
            countUniv = (int) universal.stream().filter(s -> s.getName().length() > y).count();
            countDung = (int) dungeonRoom.getEnemies().stream().filter(s -> s.getName().length() > y).count();
            countMag = (int) magicRoom.getEnemies().stream().filter(s -> s.getName().length() > y).count();
        }

        System.out.println("Універсальний: " + countUniv);
        System.out.println("DungeonRoom: " + countDung);
        System.out.println("MagicDungeonRoom: " + countMag);
    }

    private static void removeFromSpecificList(List<SkeletonWarrior> universal, Scanner scanner) {
        System.out.println("Оберіть джерело для видалення:");
        System.out.println("1. Універсальний");
        System.out.println("2. DungeonRoom");
        System.out.println("3. MagicDungeonRoom");
        int src = Integer.parseInt(scanner.nextLine());

        List<SkeletonWarrior> targetList = (src == 2) ? dungeonRoom.getEnemies()
                : (src == 3 ? magicRoom.getEnemies() : universal);

        System.out.println("Введіть ім'я ворога для видалення:");
        String name = scanner.nextLine();
        boolean removed = targetList.removeIf(s -> s.getName().equalsIgnoreCase(name));

        if (removed)
            System.out.println("Успішно видалено!");
        else
            System.out.println("Ворога не знайдено!");
    }

    private static List<SkeletonWarrior> getAllEnemies(List<SkeletonWarrior> universal) {
        List<SkeletonWarrior> all = new ArrayList<>(universal);
        all.addAll(dungeonRoom.getEnemies());
        all.addAll(magicRoom.getEnemies());
        return all;
    }

    private static void removeFromAllSources(List<SkeletonWarrior> universal, SkeletonWarrior s) {
        universal.remove(s);
        dungeonRoom.removeEnemy(s);
        magicRoom.removeEnemy(s);
    }

    private static SkeletonWarrior findStrongest(List<SkeletonWarrior> list) {
        return list.stream().max(Comparator.comparingInt(SkeletonWarrior::getHP)).orElse(null);
    }
}
