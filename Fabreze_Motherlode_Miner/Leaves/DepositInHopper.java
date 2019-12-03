package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
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
 * Deposits pay-dirt into bag
 */
public class DepositInHopper extends LeafTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    private Player player = Players.getLocal();
    private Area hopperinview = new Area.Rectangular(new Coordinate(3750, 5671, 0), new Coordinate(3747, 5674, 0));
    private Varbit sackvarbit = Varbits.load(5558);

    @Override
    public void execute() {

        if (bot.sack < sackvarbit.getValue()){//Keeps track of sack size if any paydirt was left over from previous deposit cycle
            bot.sack = sackvarbit.getValue();
        }

        WebPath path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(hopperinview.getRandomCoordinate());
        int orenumber = Inventory.getQuantity("Pay-dirt");
        int sackcache = bot.sack;

        GameObject hopper = GameObjects.newQuery().names("Hopper").actions("Deposit").types(GameObject.Type.PRIMARY).surroundingsReachable().results().first();

        if (hopper != null && hopper.isVisible()){
            if (hopper.interact("Deposit")){
                Execution.delayUntil(() -> !Inventory.contains("Pay-dirt"), () -> player.isMoving(), 5000);
                if(!Inventory.contains("Pay-dirt")){
                    if (bot.sacksize == 0){
                        bot.depositstatus = true;
                    }
                    else if ((orenumber + sackcache) >= bot.sacksize){
                        bot.depositstatus = true;
                    }
                    bot.sack += orenumber;
                }
            }
        }
        else if (path != null){
            path.step();
            Execution.delay(900);
            Execution.delayUntil(()-> !player.isMoving());}
    }
}
