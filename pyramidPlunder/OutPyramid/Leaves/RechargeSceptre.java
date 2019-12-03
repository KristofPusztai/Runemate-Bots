package com.darksage.bots.pyramidPlunder.OutPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class RechargeSceptre extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        Npc mummy = Npcs.newQuery().names(Variables.MUMMY).results().nearest();

        ChatDialog.Option charge = ChatDialog.getOption("I want to charge or remove charges from my sceptre.");
        ChatDialog.Option stone = ChatDialog.getOption("Stone artefacts?");

        if (!ChatDialog.getOptions().isEmpty() && charge != null) {
            getLogger().info("Starting recharge talks");
            if (charge.select())
                Delays.delay(400, 800, 600);
        } else if (!ChatDialog.getOptions().isEmpty() && stone != null) {
            getLogger().info("Sececting to use Stone artefacts");
            if (stone.select())
                Delays.delay(400, 800, 600);
        } else if (ChatDialog.getContinue() != null) {
            getLogger().info("continueing dialog");
            if (ChatDialog.getContinue().select())
                Delays.delay(400, 800, 600);
        } else {
            if (mummy != null) {
                if (mummy.getVisibility() < 55) {
                    getLogger().info("turning camera to mummy");
                    if (Camera.turnTo(mummy))
                        Delays.delay(400, 800, 600);
                } else {
                    getLogger().info("starting talks with mummy to recharge sceptre");
                    if (mummy.interact("Talk-to"))
                        Delays.delayUntil(() -> !ChatDialog.getOptions().isEmpty() || ChatDialog.getContinue() != null, () -> bot.isMoving(), 500, 700, 900);
                }
            }
        }
    }
}
