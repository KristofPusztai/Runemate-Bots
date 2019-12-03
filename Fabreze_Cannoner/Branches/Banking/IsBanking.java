package Fabreze.bots.Fabreze_Cannoner.Branches.Banking;

import Fabreze.bots.Fabreze_Cannoner.Branches.HasFood;
import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBanking extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();

    private HasFood hasFood = new HasFood();
    private IsInventoryFull isInventoryFull = new IsInventoryFull();

    @Override
    public boolean validate(){
        return bot.bankcball || bot.bankfood || bot.bankinventory || bot.bankpot;
    }
    @Override
    public TreeTask successTask() { return isInventoryFull; }

    @Override
    public TreeTask failureTask() { return hasFood; }
}
