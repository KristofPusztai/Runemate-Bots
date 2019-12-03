package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.FillCannon;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasAmmo extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();


    private FillCannon fillCannon = new FillCannon();
    private IsFiring isFiring = new IsFiring();

    @Override
    public boolean validate(){


        return bot.firing && (bot.cannonammo > bot.mincannonammo);
    }
    @Override
    public TreeTask successTask() { return isFiring; }

    @Override
    public TreeTask failureTask() { return fillCannon; }

}
