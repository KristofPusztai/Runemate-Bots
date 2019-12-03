package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.FillCannon;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class IsFiring extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();


    private FillCannon fillCannon = new FillCannon();
    private IsBuryBones isBuryBones = new IsBuryBones();

    @Override
    public boolean validate(){
        return bot.firing;
    }

    @Override
    public TreeTask successTask() { return isBuryBones; }

    @Override
    public TreeTask failureTask() { return fillCannon; }

}
