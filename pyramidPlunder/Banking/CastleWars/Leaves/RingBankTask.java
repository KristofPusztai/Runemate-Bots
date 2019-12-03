package com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves;

import com.RegalAPI.utility.Banking;
import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.LocatableEntity;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Banks;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class RingBankTask extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        LocatableEntity theBank = Banks.newQuery().results().nearest();
        bot.setIgnoreAdditions(true);
        if (theBank != null && theBank.getVisibility() < 55) {
            getLogger().info("Turning camera to bank chest");
            Camera.turnTo(theBank);
            Delays.delay(200, 400, 300);
        }
        if (!Bank.isOpen()) {
            getLogger().info("Opening bank");
            Bank.open();
            Delays.delay(200, 400, 300);
        }
        if (Bank.isOpen()) {
            if (Inventory.isFull()) {
                getLogger().info("Deposting Inventory to create space for ROD");
                if (Bank.depositInventory())
                    Delays.delay(300, 600, 450);
            }
            if (!Equipment.contains(Variables.RING_DUELING) && !Inventory.contains(Variables.RING_DUELING)) {
                if (Banking.contains(Variables.RING_DUELING)) {
                    getLogger().info("Withdrawing a " + Variables.RING_DUELING);
                    if (Bank.withdraw(Variables.RING_DUELING, 1))
                        Delays.delayUntil(() -> Inventory.contains(Variables.RING_DUELING), 300, 600);
                } else {
                    ClientUI.showAlert("We do not have a " + Variables.RING_DUELING);
                    bot.stopBot("We do not have a " + Variables.RING_DUELING);
                }
            } else {
                getLogger().info("We have a " + Variables.RING_DUELING);
                if (Bank.close())
                    Delays.delay(300, 600, 450);
                bot.setIgnoreAdditions(false);
            }
        }
    }
}
