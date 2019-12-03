package com.darksage.bots.pyramidPlunder.OutPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.regex.Pattern;

/**
 * Created by Team Regal
 */
public class TeleportToPyramid extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        SpriteItem sceptre = Equipment.newQuery().names(Variables.SCEPTRE).results().first();
        if (Bank.isOpen()) {
            getLogger().info("closing bank");
            Bank.close();
            Delays.delay(200, 400, 300);
        }
        Pattern sceptreEquip = Pattern.compile("Pharaoh's sceptre");
        if (Equipment.contains(sceptreEquip)) {
            if (!InterfaceWindows.getEquipment().isOpen()) {
                getLogger().info("Opening equipment");
                InterfaceWindows.getEquipment().open();
                Delays.delay(200, 400, 300);
            }
            if (sceptre != null && sceptre.isVisible()) {
                getLogger().info("teleporting to Pyramid via sceptre");
                if (sceptre.interact(Variables.SCEPTRE_SPELL))
                    Delays.delayUntil(() -> !Npcs.newQuery().names(Variables.MUMMY).results().isEmpty(), () -> bot.isAnimating(), 800, 1600, 2000);
            }
        } else if (Inventory.contains(sceptreEquip)) {
            SpriteItem sceptreInv = Inventory.newQuery().names(sceptreEquip).results().first();
            if (!InterfaceWindows.getInventory().isOpen()) {
                getLogger().info("Opening inventory");
                InterfaceWindows.getInventory().open();
                Delays.delay(200, 400, 300);
            }
            if (sceptreInv != null && sceptreInv.isVisible()) {
                getLogger().info("Wielding Pharaoh's sceptre");
                if (Inventory.equip(sceptreInv))
                    Delays.delay(300, 600, 450);
            }
        }
    }
}
