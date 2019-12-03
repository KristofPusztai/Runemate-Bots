package com.darksage.bots.pyramidPlunder.OutPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class StartPlunder extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        Npc mummy = Npcs.newQuery().names(Variables.MUMMY).actions(Variables.START).results().nearest();
        if (mummy != null) {
            if (mummy.getVisibility() > 60) {
                getLogger().info("Starting Plunder session");
                if (mummy.interact(Variables.START))
                    Delays.delayUntil(() -> !mummy.isValid(), () -> bot.isAnimating() || bot.isMoving(), 500, 1000, 1500);
                Delays.delay(400, 800, 600);
            } else {
                getLogger().info("Turning camera to Mummy");
                int fail = Camera.getYaw();
                if (Camera.turnTo(mummy))
                    Delays.delay(200, 400, 300);
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
