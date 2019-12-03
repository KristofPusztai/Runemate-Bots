package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBucketOfWaterSelected extends BranchTask{

    private HasBucketOfWater hasBucketOfWater = new HasBucketOfWater();
    private IsCoalBagSelected isCoalBagSelected = new IsCoalBagSelected();
    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();


    @Override
    public boolean validate(){
        return bot.bucketofwater;
    }
    @Override
    public TreeTask successTask() { return hasBucketOfWater; }

    @Override
    public TreeTask failureTask() { return isCoalBagSelected; }

}