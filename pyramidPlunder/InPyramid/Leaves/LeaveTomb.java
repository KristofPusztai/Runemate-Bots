package com.darksage.bots.pyramidPlunder.InPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.Data.PyramidDoors;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class LeaveTomb extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        GameObject exit = GameObjects.newQuery().names(NamesActions.TOMB_DOOR.getName()).actions(Variables.LEAVE).results().nearest();
        ChatDialog.Option yes = ChatDialog.getOption("Yes, I'm out of here.");
        Npc swarm = Npcs.newQuery().targeting(bot.getPlayer()).results().nearest();
        if (swarm != null) {
            if (swarm.isValid() && swarm.isVisible()) {
                Actor target;
                if ((target = bot.getPlayer().getTarget()) != null && target.equals(swarm)) {
                    getLogger().info("We are fighting " + swarm.getName());
                    Execution.delay(100);
                } else {
                    swarm.interact("Attack");
                    getLogger().info("We are attacking " + swarm.getName());
                    Execution.delayWhile(() -> bot.isMoving(), 200, 400);
                }
            } else {
                if (swarm.isValid()) {
                    Camera.turnTo(swarm);
                }
            }
        } else if (exit != null) {
            if (exit.getVisibility() > 60) {
                if (yes != null) {
                    getLogger().info("Selecting Yes to leave");
                    if (yes.select())
                        Delays.delayUntil(() -> PyramidDoors.NORTH_DOOR.getDoorArea().contains(bot.getPlayer()), 1200, 1600);
                } else if (ChatDialog.getContinue() != null) {
                    getLogger().info("Continueing dialog");
                    if (ChatDialog.getContinue().select())
                        Delays.delayUntil(() -> yes != null, 500, 800);
                } else {
                    getLogger().info("Interacting with door to leave");
                    if (exit.interact(Variables.LEAVE))
                        Delays.delayUntil(() -> ChatDialog.getContinue() != null, () -> bot.isMoving(), 500, 800, 1200);
                }
            } else {
                getLogger().info("Turning camera to exit");
                if (Camera.turnTo(exit))
                    Delays.delay(300, 600, 450);
            }
        }
    }
}
