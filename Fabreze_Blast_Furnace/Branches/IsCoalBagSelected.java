package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalBagSelected extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private HasCoalBag hasCoalBag = new HasCoalBag();
    private IsIceGloveSelected isIceGloveSelected = new IsIceGloveSelected();

    @Override
    public boolean validate(){
        return bot.coalbag;
    }
    @Override
    public TreeTask successTask() { return hasCoalBag; }

    @Override
    public TreeTask failureTask() { return isIceGloveSelected; }

}
