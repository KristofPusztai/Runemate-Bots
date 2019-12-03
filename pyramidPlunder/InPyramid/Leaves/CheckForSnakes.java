package com.darksage.bots.pyramidPlunder.InPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class CheckForSnakes extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        GameObject urn = GameObjects.newQuery().names(NamesActions.URN.getName()).actions(NamesActions.URN.getAction()).results().nearest();
        if (urn != null) {
            if (urn.getVisibility() > 50) {
                getLogger().info("Searching Urn");
                if (urn.interact("Search"))
                    Delays.delayUntil(() -> !urn.isValid(), () -> bot.isAnimating() || bot.isAnimating(), 400, 500, 700);
            } else {
                getLogger().info("turning camera to nearest urn");
                int fail = Camera.getYaw();
                if (Camera.turnTo(urn))
                    Delays.delay(300, 600, 450);
                if (Camera.getYaw() == fail) {
                    if (fail < 349) {
                        fail = fail + 10;
                    } else
                        fail = fail - 10;
                    getLogger().info("Failsafe camera turn");
                    Camera.turnTo(fail);
                }
            }
        }
    }
}
