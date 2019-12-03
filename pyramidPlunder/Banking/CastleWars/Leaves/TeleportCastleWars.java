package com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class TeleportCastleWars extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        if (Inventory.contains(Variables.RING_DUELING)) {
            if (!InterfaceWindows.getInventory().isOpen()) {
                InterfaceWindows.getInventory().open();
                Delays.delay(300, 600, 450);
            }
            SpriteItem rings = Inventory.newQuery().names(Variables.RING_DUELING).results().first();
            if (rings != null) {
                getLogger().info("equipping ring of dueling");
                rings.click();
                Delays.delayUntil(() -> Equipment.contains(Variables.RING_DUELING), 400, 800);
            }
        }
        SpriteItem ring = Equipment.newQuery().names(Variables.RING_DUELING).results().first();
        if (!InterfaceWindows.getEquipment().isOpen()) {
            getLogger().info("Opening equipment");
            InterfaceWindows.getEquipment().open();
            Delays.delay(200, 400, 300);
        }
        if (ring != null && ring.isVisible()) {
            getLogger().info("Teleporting to Castle Wars");
            if (ring.interact("Castle Wars"))
                Delays.delayUntil(() -> Variables.CASTLE_WARS.contains(Players.getLocal()), () -> bot.isAnimating(), 800, 1600, 2000);
        }
    }
}
