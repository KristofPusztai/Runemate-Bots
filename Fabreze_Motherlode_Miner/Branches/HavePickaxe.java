package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.GetPickaxe;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks pickaxe, either equipped or in inventory
 */
public class HavePickaxe extends BranchTask {

    private IsDeposited isDeposited = new IsDeposited();
    private GetPickaxe getpickaxe = new GetPickaxe();

    @Override
    public boolean validate() {
        return (Equipment.containsAnyOf("Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe") ||
                Inventory.containsAnyOf("Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe"));
    }

    @Override
    public TreeTask failureTask() {
        return getpickaxe;
    }

    @Override
    public TreeTask successTask() { return isDeposited; }
}
