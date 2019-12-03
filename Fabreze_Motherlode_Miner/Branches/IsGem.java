package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.DropGem;
import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsGem extends BranchTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    private IsInvFull isInvFull = new IsInvFull();
    private DropGem dropGem = new DropGem();

    @Override
    public boolean validate(){
        return bot.dropgem && Inventory.containsAnyOf("Uncut ruby","Uncut emerald", "Uncut sapphire","Uncut diamond");
    }
    @Override
    public TreeTask successTask() { return dropGem; }

    @Override
    public TreeTask failureTask() { return isInvFull; }
}
