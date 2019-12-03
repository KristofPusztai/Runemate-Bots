package com.darksage.bots.pyramidPlunder.OutPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.Data.PyramidDoors;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class ExitPyramid extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        GameObject door = GameObjects.newQuery().names(NamesActions.TOMB_DOOR.getName()).actions("Leave Tomb").results().nearest();
        if (door != null) {
            getLogger().info("Exiting pyramid");
            if (door.interact("Leave Tomb"))
                Delays.delayUntil(() -> PyramidDoors.NORTH_DOOR.getDoorArea().contains(bot.getPlayer()), () -> bot.isAnimating() || bot.isMoving(), 500, 800, 1200);
        }
    }
}
