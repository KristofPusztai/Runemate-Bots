package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


/**
 * NOTES:
 * Withdraws ores from mining bag and deposits them in the bank.
 */
public class GrabfromSack extends LeafTask {

    private MotherlodeMain bot = (MotherlodeMain)Environment.getBot();

    private Area nearsack = Area.rectangular(new Coordinate(3750, 5662,0), new Coordinate(3747, 5657, 0));
    private Player player = Players.getLocal();
    private Varbit sackvarbit = Varbits.load(5558);

    @Override
    public void execute() {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(new Coordinate(3749, 5659, 0));

        GameObject sack = GameObjects.newQuery().names("Sack").actions("Search").results().first();
        if (sack != null && sack.isVisible()){

            if (Bank.isOpen()){
                Bank.close();
                Execution.delayUntil(() -> !Bank.isOpen(), 2000);
            }
            else if (sack.interact("Search")){
                Execution.delayUntil(() -> Inventory.containsAnyExcept( "Uncut ruby","Uncut emerald", "Uncut sapphire","Uncut diamond", "Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe"),() -> player.isMoving(), 2000);
                int oresininventory = Inventory.getQuantity("Golden nugget") + Inventory.getQuantity("Coal") + Inventory.getQuantity("Iron ore") + Inventory.getQuantity("Gold ore") + Inventory.getQuantity("Silver ore") + Inventory.getQuantity("Mithril ore") + Inventory.getQuantity("Runite ore");
                bot.sack -= oresininventory;
                if(sackvarbit.getValue() == 0){
                    bot.depositstatus = false;
                    bot.sack = 0;
                }
            }
        }
        else if (!nearsack.contains(player) && path != null){
            if (path.step()){
                Execution.delayUntil(() -> !player.isMoving(), () -> player.isMoving(),1200, 2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(sack);
        }
    }
}
