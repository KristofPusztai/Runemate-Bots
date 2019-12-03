package com.darksage.bots.pyramidPlunder.InPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class PassSpeartrap extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        GameObject trap = GameObjects.newQuery().names(NamesActions.SPEARTRAP.getName()).actions(NamesActions.SPEARTRAP.getAction()).results().nearest();
        if (trap != null) {
            if (trap.getVisibility() > 50) {
                getLogger().info("Passing Speartraps");
                if (trap.interact(NamesActions.SPEARTRAP.getAction()))
                    Delays.delayUntil(() -> Varbits.load(2365).getValue() == 0, () -> bot.isMoving() || bot.isAnimating(), 600, 800, 1200);
            } else {
                getLogger().info("Turning camera to Speartraps");
                if (Camera.turnTo(trap))
                    Delays.delay(300, 600, 450);
            }
        }
    }
}
