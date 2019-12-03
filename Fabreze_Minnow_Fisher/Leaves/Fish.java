package Fabreze.bots.Fabreze_Minnow_Fisher.Leaves;


import Fabreze.bots.Fabreze_Minnow_Fisher.MinnowFisherMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Checks fishing spot within distance 1 and clicks on nearest spot if it is not
 */
public class Fish extends LeafTask {

    @Override
    public void execute() {

        Player player = Players.getLocal();
        Npc fishingspot = Npcs.newQuery().names("Fishing spot").actions("Small Net").results().nearest(Distance.Algorithm.EUCLIDEAN_SQUARED);

        if (player != null && !player.isMoving() && fishingspot != null) {

            if (((MinnowFisherMain) Environment.getBot()).chattrig){

                ((MinnowFisherMain) Environment.getBot()).chattrig = false;
                Npc fishingspot2 = Npcs.newQuery().names("Fishing spot").actions("Small Net").results().sortByDistance().get(1);

                if (fishingspot2.isVisible() && fishingspot2.interact("Small Net")){
                    Execution.delayUntil(() -> fishingspot2.distanceTo(player) == 1, 5000);
                    Execution.delayUntil(() -> fishingspot2.distanceTo(player) != 1 || ((MinnowFisherMain) Environment.getBot()).chattrig, () -> player.getAnimationId() != -1, 1500);
                }
                else if (fishingspot2.getPosition() != null && fishingspot2.getPosition().minimap().click()){
                    Execution.delayUntil(() -> !player.isMoving());
                }
            }
            else if (player.getAnimationId() == -1 && !player.isMoving() || fishingspot.distanceTo(player) != 1) {
                fishingspot.interact("Small Net");
                Execution.delayUntil(() -> fishingspot.distanceTo(player) == 1, 5000);
                Execution.delayUntil(() -> fishingspot.distanceTo(player) != 1 || ((MinnowFisherMain) Environment.getBot()).chattrig, () -> player.getAnimationId() != -1, 1500);
            }
        }
    }
}