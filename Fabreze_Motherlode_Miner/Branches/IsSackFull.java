package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.GrabfromSack;
import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks whether mining bag has any ores >0
 */
public class IsSackFull extends BranchTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    private GrabfromSack grabfromSack = new GrabfromSack();
    private HavePickaxe havepickaxe = new HavePickaxe();

    private Varbit sack = Varbits.load(5558);

    @Override
    public boolean validate() {
        if(sack.getValue() != 0 && bot.depositstatus){
            SpriteItem hammer = Inventory.newQuery().names("Hammer").actions("Drop").results().first();
            if (Inventory.contains("Hammer") && hammer != null && hammer.interact("Drop")){
                return true;
            }
            else {return true;}
        }
        else{ return false; }
    }

    @Override
    public TreeTask failureTask() {
        return havepickaxe;
    }

    @Override
    public TreeTask successTask() {
        return grabfromSack;
    }
}
