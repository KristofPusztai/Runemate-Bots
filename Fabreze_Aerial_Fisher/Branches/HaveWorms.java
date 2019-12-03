package Fabreze.bots.Fabreze_Aerial_Fisher.Branches;

import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.GetWorms;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveWorms extends BranchTask {
    private GetWorms getWorms = new GetWorms();
    private IsInvFull isInvFull = new IsInvFull();
    private IsCuttingFishEnabled isCuttingFishEnabled = new IsCuttingFishEnabled(getWorms);

    @Override
    public boolean validate(){return Inventory.contains("King worm") || Inventory.contains("Fish chunks");}

    @Override
    public TreeTask failureTask() { return isCuttingFishEnabled; }

    @Override
    public TreeTask successTask() {
        return isInvFull;
    }
}
