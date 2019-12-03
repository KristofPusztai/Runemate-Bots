package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabBars;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsDispenserCool extends BranchTask {

    private GrabBars grabBars = new GrabBars();
    private IsBucketofWaterForDispenser isBucketofWaterForDispenser = new IsBucketofWaterForDispenser();

    private Varbit dispensercool = Varbits.load(936);

    @Override
    public boolean validate(){
        return dispensercool.getValue() == 3;
    }
    @Override
    public TreeTask successTask() { return grabBars; }

    @Override
    public TreeTask failureTask() { return isBucketofWaterForDispenser; }

}
