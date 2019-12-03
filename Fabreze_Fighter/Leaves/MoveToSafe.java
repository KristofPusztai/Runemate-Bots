package Fabreze.bots.Fabreze_Fighter.Leaves;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class MoveToSafe extends LeafTask {

    private FighterMain bot = (FighterMain) Environment.getBot();

    @Override
    public void execute(){

        Player player = Players.getLocal();

        if (player != null && bot.safespotloc != null){

            WebPath path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(bot.safespotloc);

            RegionPath path1 = RegionPath.buildTo(bot.safespotloc);

            if (path != null){
                path.step(Path.TraversalOption.PREFER_VIEWPORT);
                Execution.delayUntil(() -> !player.isMoving());
                }
            else if (path1 != null){
                path1.step(Path.TraversalOption.PREFER_VIEWPORT);
                Execution.delayUntil(() -> !player.isMoving());
                }

        }
    }

}
