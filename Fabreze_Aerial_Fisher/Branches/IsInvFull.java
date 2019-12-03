package Fabreze.bots.Fabreze_Aerial_Fisher.Branches;

import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.DropFish;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInvFull extends BranchTask {

    private IsOnEdge isOnEdge = new IsOnEdge();
    private DropFish dropFish = new DropFish(this);
    private IsCuttingFishEnabled isCuttingFishEnabled = new IsCuttingFishEnabled(dropFish);
    public boolean inventorytrig = false;

    @Override
    public boolean validate(){
            if (Inventory.isFull() || inventorytrig){
                inventorytrig = true;
                return true;
            }
            else{ return false; }
    }

    @Override
    public TreeTask failureTask() {
        return isOnEdge;
    }

    @Override
    public TreeTask successTask() {
        return isCuttingFishEnabled;
    }
}
