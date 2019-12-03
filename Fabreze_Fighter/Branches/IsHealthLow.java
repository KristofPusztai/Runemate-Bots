package Fabreze.bots.Fabreze_Fighter.Branches;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.EatFood;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsHealthLow extends BranchTask {
    private FighterMain bot = (FighterMain) Environment.getBot();

    private EatFood eatFood = new EatFood();
    private IsPotion isPotion = new IsPotion();

    @Override
    public boolean validate(){

        return Health.getCurrentPercent() <= bot.healthval;
    }


    @Override
    public TreeTask successTask() { return eatFood; }

    @Override
    public TreeTask failureTask() { return isPotion; }

}
