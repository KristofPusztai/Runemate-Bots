package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.FillCoalBag;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabCoal;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalInInventory extends BranchTask {

    private FillCoalBag fillCoalBag = new FillCoalBag();
    private GrabCoal grabCoal = new GrabCoal();

    @Override
    public boolean validate(){
        return Inventory.contains("Coal");
    }
    @Override
    public TreeTask successTask() { return fillCoalBag; }

    @Override
    public TreeTask failureTask() { return grabCoal; }

}
