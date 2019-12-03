package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalBagFull extends BranchTask {

    private IsCoalInInventory isCoalInInventory = new IsCoalInInventory();
    private IsIceGloveSelected isIceGloveSelected = new IsIceGloveSelected();

    private BlastFurnaceMain bot = (BlastFurnaceMain)Environment.getBot();

    @Override
    public boolean validate(){
        return bot.iscoalneeded || bot.coalbagamt >0;
    }
    @Override
    public TreeTask successTask() { return isIceGloveSelected; }

    @Override
    public TreeTask failureTask() { return isCoalInInventory; }

}
