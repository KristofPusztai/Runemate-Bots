package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabStaminaPot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasStaminaInInvy extends BranchTask {

    private IsRunEnergy0 isRunEnergy0 = new IsRunEnergy0();
    private GrabStaminaPot grabStaminaPot = new GrabStaminaPot();

    @Override
    public boolean validate(){
        return Inventory.contains(Regex.getPatternForContainsString("Stamina pot"));
    }
    @Override
    public TreeTask successTask() { return isRunEnergy0; }

    @Override
    public TreeTask failureTask() { return grabStaminaPot; }
}
