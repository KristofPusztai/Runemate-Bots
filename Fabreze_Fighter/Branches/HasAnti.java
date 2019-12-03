package Fabreze.bots.Fabreze_Fighter.Branches;

import Fabreze.bots.Fabreze_Fighter.Leaves.DrinkAnti;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class HasAnti extends BranchTask {

    private DrinkAnti drinkAnti = new DrinkAnti();
    private IsHealthLow isHealthLow = new IsHealthLow();

    @Override
    public boolean validate(){


        return Inventory.contains(Regex.getPatternContainingOneOf("poison", "dote")) && Inventory.contains(Regex.getPatternForContainsString("Anti"));
    }
    @Override
    public TreeTask successTask() { return drinkAnti; }

    @Override
    public TreeTask failureTask() { return isHealthLow; }

}
