package Fabreze.bots.Fabreze_Agility.MarksOfGrace;

import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatFood extends LeafTask {

    private FoodValidator getfood =  new FoodValidator();

    @Override
    public void execute(){
       SpriteItem food = getfood.getFood();
       food.interact("Eat");
        Execution.delayUntil(() -> !food.isValid(), 2000);
    }
}
