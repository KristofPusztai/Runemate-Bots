package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.DisplayCoolingError;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsIceGlovesForDispenser extends BranchTask {
    private DisplayCoolingError displayCoolingError = new DisplayCoolingError();
    private HasGlovesForDispenser hasGlovesForDispenser = new HasGlovesForDispenser();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).icegloves;
    }
    @Override
    public TreeTask successTask() { return hasGlovesForDispenser; }

    @Override
    public TreeTask failureTask() { return displayCoolingError; }

}
