package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsDispenserFull extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsDispenserCool isDispenserCool = new IsDispenserCool();
    private IsStaminaPotion isStaminaPotion = new IsStaminaPotion();

    private Varbit adamantitedispenser = Varbits.load(945);
    private Varbit mithrildispenser = Varbits.load(944);
    private Varbit golddispenser = Varbits.load(947);
    private Varbit irondispenser = Varbits.load(942);
    private Varbit steeldispenser = Varbits.load(943);

    @Override
    public boolean validate(){

        return (adamantitedispenser.getValue() > 0 || irondispenser.getValue() > 0 || steeldispenser.getValue() > 0 || golddispenser.getValue() > 0 || mithrildispenser.getValue() > 0)
                && bot.coalcost <= bot.depositedcoalamt;
    }
    @Override
    public TreeTask successTask() { return isDispenserCool; }

    @Override
    public TreeTask failureTask() { return isStaminaPotion; }

}
