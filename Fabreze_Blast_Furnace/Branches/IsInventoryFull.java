package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.BankItems;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInventoryFull extends BranchTask {

    private BankItems bankItems = new BankItems();
    private IsDispenserFull isDispenserFull = new IsDispenserFull();

    private Varbit adamantitedispenser = Varbits.load(945);
    private Varbit mithrildispenser = Varbits.load(944);
    private Varbit golddispenser = Varbits.load(947);
    private Varbit irondispenser = Varbits.load(942);
    private Varbit steeldispenser = Varbits.load(943);

    @Override
    public boolean validate(){
        return ( adamantitedispenser.getValue() == 0 && irondispenser.getValue() == 0 && steeldispenser.getValue() == 0 && mithrildispenser.getValue() == 0 && golddispenser.getValue() == 0) && Inventory.containsAnyOf("Iron bar", "Steel bar", "Mithril bar", "Gold bar", "Adamantite bar", "Runite bar", "Bucket");
    }
    @Override
    public TreeTask successTask() { return bankItems; }

    @Override
    public TreeTask failureTask() { return isDispenserFull; }

}
