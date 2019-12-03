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
public class OpenGoldChest extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        GameObject chest = GameObjects.newQuery().names(NamesActions.CHEST.getName()).actions(NamesActions.CHEST.getAction()).results().nearest();
        if (chest != null) {
            if (chest.getVisibility() > 50) {
                getLogger().info("Looting Gold chest");
                if (chest.interact(NamesActions.CHEST.getAction()))
                    Delays.delayUntil(() -> Varbits.load(2363).getValue() == 1, () -> bot.isAnimating() || bot.isMoving(), 400, 600, 1000);
            } else {
                getLogger().info("Turning camera to Chest");
                if (Camera.turnTo(chest))
                    Delays.delay(300, 600, 450);
            }
        }
    }
}
