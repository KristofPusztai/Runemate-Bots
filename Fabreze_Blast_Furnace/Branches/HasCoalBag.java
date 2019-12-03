package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabCoalBag;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasCoalBag extends BranchTask {

    private GrabCoalBag grabCoalBag = new GrabCoalBag();
    private IsCoalBagFull isCoalBagFull = new IsCoalBagFull();

    @Override
    public boolean validate(){
        return Inventory.contains("Coal bag");
    }
    @Override
    public TreeTask successTask() { return isCoalBagFull; }

    @Override
    public TreeTask failureTask() { return grabCoalBag; }

}
