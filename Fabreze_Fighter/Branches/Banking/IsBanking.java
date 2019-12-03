package Fabreze.bots.Fabreze_Fighter.Branches.Banking;

import Fabreze.bots.Fabreze_Fighter.Branches.HasFood;
import Fabreze.bots.Fabreze_Fighter.FighterMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBanking extends BranchTask {

    private FighterMain bot = (FighterMain) Environment.getBot();

    private HasFood hasFood = new HasFood();
    private IsInventoryFull isInventoryFull = new IsInventoryFull();

    @Override
    public boolean validate(){
        return bot.bankfood || bot.bankinventory || bot.bankpot;
    }
    @Override
    public TreeTask successTask() { return isInventoryFull; }

    @Override
    public TreeTask failureTask() { return hasFood; }
}
