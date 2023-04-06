package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public abstract class Realm implements FightCallback {
    private static BufferedReader br;
    static FantasyCharacter player = null;
    private static BattleScene battleScene = null;
    Merchant merchant;

    public static void main(String[] args) {

        br = new BufferedReader(new InputStreamReader(System.in));

        battleScene = new BattleScene();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(string, 100, 20, 20, 0, 0);

            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! о славный воин", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Еще не пришел");
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3": {
                System.exit(1);
            }
            break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }

        }
        command(br.readLine());
    }
    

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }
    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {


            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта, %d золота и %d силы, а также осталось %d едениц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints(), player.getStrength()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }
    private static FantasyCharacter createMonster() {
        int monster = 0 ;
        int random = (int) (Math.random() * 10);
        if (random >= 0 && random < 4) {
            monster = 1;
        } else if (random >= 4 && random < 7) {
            monster = 2;
        } else if (random >= 7 && random < 8) {
            monster = 3;
        } else {
            monster = 4;
        }
        switch (monster) {
                case 1:
                return new Skeleton("Скелет", 25, 20, 20, 100, 10);
                case 2:
                return new Goblin("Гоблин", 25, 20, 20, 100, 10);
                case 3:
                return new Orc("Орк", 80, 80, 30, 100, 15);
                case 4:
                return new BigBoss("Главный в этом царстве", 150, 150, 50, 150, 100);
        }
        return createMonster();
    }

}



