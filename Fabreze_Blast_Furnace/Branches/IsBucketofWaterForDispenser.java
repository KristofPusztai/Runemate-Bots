package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBucketofWaterForDispenser extends BranchTask {

    private HasBucketForDispenser hasBucketForDispenser = new HasBucketForDispenser();
    private IsIceGlovesForDispenser isIceGlovesForDispenser = new IsIceGlovesForDispenser();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).bucketofwater;
    }
    @Override
    public TreeTask successTask() { return hasBucketForDispenser; }

    @Override
    public TreeTask failureTask() { return isIceGlovesForDispenser; }

}
