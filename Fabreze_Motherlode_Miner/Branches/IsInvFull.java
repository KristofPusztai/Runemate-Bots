package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks inventory full
 */
public class IsInvFull extends BranchTask {

    private IsPayDirt isPayDirt = new IsPayDirt();
    private IsSackFull isSackFull = new IsSackFull();

    @Override
    public boolean validate() {
        if(Inventory.isFull()){
            return true;
        }
        else
            return Inventory.containsAnyExcept("Pay-dirt", "Uncut ruby","Uncut emerald", "Uncut sapphire","Uncut diamond", "Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe", "Hammer");
    }

    @Override
    public TreeTask failureTask() {
        return isSackFull;
    }

    @Override
    public TreeTask successTask() { return isPayDirt; }
}
