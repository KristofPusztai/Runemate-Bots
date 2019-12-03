package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.queries.GameObjectQueryBuilder;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


/**
 * NOTES:
 * Mines pay-dirt vein
 */
public class MineVein extends LeafTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    @Override
    public void execute() {
        Player player = Players.getLocal();

        GameObject oreviein = GameObjects.newQuery().names("Ore vein").actions("Mine").types(GameObject.Type.BOUNDARY).surroundingsReachable().within(bot.finalsetlocation).results().nearest(Distance.Algorithm.EUCLIDEAN_SQUARED);
        GameObjectQueryBuilder depletedveinquery = GameObjects.newQuery().names("Depleted vein").types(GameObject.Type.BOUNDARY);

        if (player != null && !player.isMoving() && oreviein != null){
                if (oreviein.isVisible() && oreviein.interact("Mine")){
                    Execution.delayUntil(() -> depletedveinquery.on(oreviein.getPosition()).results().first() != null, ()-> player.getAnimationId() != -1 || player.isMoving() , 2000, 3000);
                }
                else {
                    Camera.concurrentlyTurnTo(oreviein);
                }
        }

    }
}
