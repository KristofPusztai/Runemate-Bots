package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.DepositCoal;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabCoal;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalInInventory1 extends BranchTask {

    private DepositCoal depositCoal = new DepositCoal();
    private GrabCoal grabCoal = new GrabCoal();

    @Override
    public boolean validate(){
        return Inventory.contains("Coal");
    }
    @Override
    public TreeTask successTask() { return depositCoal; }

    @Override
    public TreeTask failureTask() { return grabCoal; }

}
