package Game;

import java.io.IOException;

public class BattleScene {
    public void fight(FantasyCharacter hero, FantasyCharacter monster, FightCallback fightCallback) {

        Runnable runnable = () -> {

            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    private Boolean makeHit(FantasyCharacter defender, FantasyCharacter attacker, FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - hit;

        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Извините, вы пали в бою...");
            System.out.println("Нажмите 'НЕТ', чтоб вернуться в главное меню");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота и %d силу противника", defender.getXp(), defender.getGold() , defender.getStrength()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            attacker.setStrength(attacker.getStrength() + defender.getStrength());
            fightCallback.fightWin();
            return true;
        } else {

            defender.setHealthPoints(defenderHealth);

            return false;
        }

    }
}