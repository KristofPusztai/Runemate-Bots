package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;



/**
 * NOTES: Moves to Location, mines rockfall, and begins mining if ore vein is reachable from current position
 * 
 */
public class MoveToLocation extends LeafTask {

    private MotherlodeMain bot = (MotherlodeMain)Environment.getBot();

    private Player player = Players.getLocal();

    @Override
    public void execute() {

        WebPath path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(bot.coordinate);

        if (path != null && path.step()){
            Execution.delayUntil(()-> !player.isMoving(), () -> player.isMoving(), 1200, 2000);}
    }
}
