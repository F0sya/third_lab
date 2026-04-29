package org.example;

import org.example.SkeletonWarrior;

import java.rmi.server.Skeleton;
import java.sql.SQLOutput;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;


        List<SkeletonWarrior> enemies = new ArrayList<>();

        enemies.add(new SkeletonWarrior());
        enemies.add(new SkeletonArcher());
        enemies.add(new SkeletonMage());



        while(running){
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
            System.out.println("0. Вихід");
            System.out.println("Ваш вибір: ");

            String choice = scanner.nextLine();
            int user_choice;

            switch(choice) {
                case "1":
                    printList(enemies);
                    break;
                case "2":
                    System.out.println("Введіть значення і:");
                    user_choice = scanner.nextInt();
                    scanner.nextLine();

                    if(user_choice < 0 || user_choice > enemies.size()-1) {
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
                    removeObject(enemies,scanner);
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
                    binarySearch(enemies,scanner);
                    scanner.nextLine();
                    break;
                case "8":
                    filterAndRemove(enemies,scanner);
                    scanner.nextLine();
                    break;
                case "9":
                    Competition(enemies);
                    scanner.nextLine();
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
    static void printList(List<SkeletonWarrior> enemies){
        if(enemies.isEmpty()){
            System.out.println("Список пустий!");
            return;
        }
        System.out.println("Поточний масив: ");

        for (SkeletonWarrior enemy: enemies){
            System.out.println(enemy);
        }
    }

    static void appendList(List<SkeletonWarrior> enemies, Scanner scanner){
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos < 0 || pos > enemies.size()){
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

        switch(type) {
            case 1:
                newEnemy = new SkeletonWarrior(hp,dmg,name);
                break;
            case 2:
                System.out.println("Введіть к-сть стріл:");
                arrows = Integer.parseInt(scanner.nextLine());
                newEnemy = new SkeletonArcher(hp,dmg,name, arrows);
                break;
            case 3:
                System.out.println("Введіть к-сть стріл:");
                arrows = Integer.parseInt(scanner.nextLine());
                System.out.print("Введіть кількість мани: ");
                int mana = Integer.parseInt(scanner.nextLine());
                newEnemy = new SkeletonMage(hp, dmg, name,arrows, mana);
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
        }
        else if (sortChoice.equals("2")) {
            enemies.sort(new Comparator<SkeletonWarrior>() {
                @Override
                public int compare(SkeletonWarrior s1, SkeletonWarrior s2) {
                    return Integer.compare(s1.getHP(), s2.getHP());
                }
            });
            System.out.println("Відсортовано за рівнем HP.");
        }
        else {
            System.out.println("Неправильний вибір!");
        }
    }
    private static void makeCopy(List<SkeletonWarrior> enemies, Scanner scanner){
        if (enemies.isEmpty()) {
            System.out.println("Список порожній!");
            return;
        }

        System.out.println("Введіть індекс об'єкта для копіювання:");

        int copyChoice = scanner.nextInt();

        if(copyChoice < 0 || copyChoice >= enemies.size()) {
            System.out.println("Некоректний індекс!");
            return;
        }

        SkeletonWarrior original = enemies.get(copyChoice);
        SkeletonWarrior copy = original.clone();

        enemies.add(copy);

        System.out.println("Зміна оригіналу: ");
        original.setHP(0);
        if(original.toString().equals(copy.toString())){
            System.out.println("Копіювання некоректне!");
        }
        else {
            System.out.println("Копіювання успішне");
        }

    }

    private static void binarySearch(List<SkeletonWarrior> enemies, Scanner scanner){
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

        SkeletonWarrior target = new SkeletonWarrior(searchHP, 1,"Template");

        int index = Collections.binarySearch(enemies, target, hpComparator);

        if(index >= 0 ) {
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
        }
        else {
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
}
