package Fabreze.bots.Fabreze_Blast_Furnace.Coal1;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal1.NonCoalBag.Is1CoalDeposited;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalBag extends BranchTask {

    private Fabreze.bots.Fabreze_Blast_Furnace.Coal1.CoalBag.Is1CoalDeposited coalDeposited = new Fabreze.bots.Fabreze_Blast_Furnace.Coal1.CoalBag.Is1CoalDeposited();
    private Is1CoalDeposited is1CoalDeposited = new Is1CoalDeposited();

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public boolean validate(){
        return bot.coalbag;
    }
    @Override
    public TreeTask successTask() {
        if (bot.coalcost == 1){
            bot.coalcost = 2;
        }
        return coalDeposited;
    }

    @Override
    public TreeTask failureTask() { return is1CoalDeposited; }

}
