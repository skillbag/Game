package Game;

public abstract class FantasyCharacter implements Fighter {
 String name;
 int healthPoints;
 int strength;
 int dexterity;
 int xp;
 int gold;
public FantasyCharacter(String name, int healthPoints, int strength, int dexterity, int xp, int gold){
    this.name = name;
    this.healthPoints = healthPoints;
    this.dexterity = dexterity;
    this.strength = strength;
    this.xp = xp;
    this.gold = gold;
}


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getStrength() {
        return strength;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getGold() {
        return gold;
    }
     int getRandomValue(){
    return (int) (Math.random() * 100);
    }
    @Override
    public String toString(){
    return String.format("%s здоровье:%d", name, healthPoints);
    }
    @Override
    public int attack() {
        if (dexterity * 3 > getRandomValue()) {
            return strength;}
            else return 0;



    }
}
