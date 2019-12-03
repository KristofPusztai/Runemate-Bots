package com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class EquipRing extends LeafTask {

    @Override
    public void execute() {
        SpriteItem ring = Inventory.newQuery().names(Variables.RING_DUELING).results().first();

        if (Bank.isOpen()) {
            getLogger().info("Closing bank");
            if (Bank.close())
                Delays.delay(300, 600, 450);
        }

        if (!InterfaceWindows.getInventory().isOpen()) {
            getLogger().info("Opening Inventory");
            InterfaceWindows.getInventory().open();
            Delays.delay(200, 400, 300);
        }

        if (ring != null) {
            getLogger().info("Equiping " + Variables.RING_DUELING);
            if (Inventory.equip(ring))
                Delays.delayUntil(() -> Equipment.contains(Variables.RING_DUELING), 300, 600);
        }
    }
}
