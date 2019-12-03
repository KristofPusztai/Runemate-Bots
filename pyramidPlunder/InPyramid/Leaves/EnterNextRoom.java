package com.darksage.bots.pyramidPlunder.InPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.listeners.ChatboxListener;
import com.runemate.game.api.script.framework.listeners.events.MessageEvent;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class EnterNextRoom extends LeafTask implements ChatboxListener {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private boolean failed;

    @Override
    public void execute() {
        GameObject door = GameObjects.newQuery().names(NamesActions.TOMB_DOOR.getName()).actions(NamesActions.TOMB_DOOR.getAction()).results().nearest();
        int currentRoom = Varbits.load(2376).getValue();
        if (door != null) {
            if (door.getVisibility() > 50) {
                getLogger().info("picking lock, to enter next room");
                if (door.interact(NamesActions.TOMB_DOOR.getAction()))
                    Delays.delayUntil(() -> Varbits.load(2376).getValue() == currentRoom + 10 || bot.getPlayer().getTarget() != null || failed, () -> bot.isAnimating() || bot.isMoving(), 250, 400, 600);
            } else {
                getLogger().info("Turning camera to door");
                int fail = Camera.getYaw();
                if (Camera.turnTo(door))
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

    @Override
    public void onMessageReceived(MessageEvent m) {
        String message = m.getMessage();
        failed = message.contains("Your attempt fails.");
    }
}
