package Fabreze.bots.Fabreze_Agility.MarksOfGrace;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;

public class FoodValidator {

    private SpriteItem food;
    private boolean containseat;

    public boolean checkforfood(){
        if (!Inventory.isEmpty()){
            int x =  0;
            for (SpriteItem listOfItems = Inventory.getItems().asList().get(x); Inventory.getItems().last().getIndex()> x ; x++ ){
                if (listOfItems.getDefinition() != null && listOfItems.getDefinition().getInventoryActions().get(0).equals("Eat")){
                    containseat = true;
                    break;
                }
            }
            return containseat;

        }
        else {return false;}
    }

    public SpriteItem getFood(){
        int x = 0;
        for (SpriteItem listOfItems = Inventory.getItems().asList().get(x); Inventory.getItems().last().getIndex()> x ; x++ ){
            if (listOfItems.getDefinition() != null && listOfItems.getDefinition().getInventoryActions().get(0).equals("Eat")){
                food = listOfItems;
                break;
            }
        }
        return food;
    }
}
