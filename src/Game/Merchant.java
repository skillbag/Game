package Game;

public class Merchant {
    public String sell(Merchant.Goods goods){
        String result = "";
        if(goods == Goods.POTION){
            result = "potion";
        }
        return result;
    }
    public enum Goods{
        POTION;
    }
}
