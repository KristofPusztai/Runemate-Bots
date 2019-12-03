package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.CoolDispenser;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabBucketOfWater;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasBucketForDispenser extends BranchTask {

    private CoolDispenser coolDispenser = new CoolDispenser();
    private GrabBucketOfWater grabBucketOfWater = new GrabBucketOfWater();

    @Override
    public boolean validate(){
        return Inventory.contains("Bucket of water");
    }
    @Override
    public TreeTask successTask() { return coolDispenser; }

    @Override
    public TreeTask failureTask() { return grabBucketOfWater; }
}
