package com.darksage.bots.pyramidPlunder.InPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class OpenSarcophagus extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        GameObject coffin = GameObjects.newQuery().names(NamesActions.SARCOPHAGUS.getName()).actions(NamesActions.SARCOPHAGUS.getAction()).results().nearest();
        int hp = Health.getCurrentPercent();
        if (coffin != null) {
            if (coffin.getVisibility() > 50) {
                getLogger().info("Opening/Searching Sarcophagus");
                if (coffin.interact(NamesActions.SARCOPHAGUS.getAction()))
                    Delays.delayWhile(() -> bot.isMoving() || bot.isAnimating(), () -> hp != Health.getCurrentPercent(), 1200, 1800);
            } else {
                getLogger().info("Turning camera to Sarcophagus");
                if (Camera.turnTo(coffin))
                    Delays.delay(300, 600, 450);
            }
        }
    }
}
