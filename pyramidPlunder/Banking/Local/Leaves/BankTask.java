package com.darksage.bots.pyramidPlunder.Banking.Local.Leaves;

import com.RegalAPI.utility.Banking;
import com.RegalAPI.utility.RegalCamera;
import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.LocatableEntity;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Banks;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.regex.Pattern;

/**
 * Created by Team Regal
 */
public class BankTask extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        LocatableEntity theBank = Banks.newQuery().results().nearest();
        Pattern sceptre = Pattern.compile("Pharaoh's sceptre");
        bot.setIgnoreAdditions(true);

        if (theBank != null && theBank.getVisibility() < 55) {
            getLogger().info("Turning camera to bank");
            RegalCamera.makeVisible(theBank);
            Delays.delay(300, 600, 450);
        } else if (theBank == null) {
            getLogger().warn("Bank is null, re-checking");
        }

        if (!Bank.isOpen()) {
            getLogger().info("opening bank");
            Bank.open();
            Delays.delay(300, 600, 450);
        }

        if (Bank.isOpen()) {
            String[] loot = bot.vars.getLoots();
            for (int i = 0; i < loot.length; i++) {
                if (Inventory.contains(loot[i])) {
                    getLogger().info("Depoisting Inventory");
                    if (Bank.depositInventory())
                        Delays.delayUntil(() -> Inventory.isEmpty(), 300, 600);
                }
            }

            if (Inventory.getEmptySlots() < 10) {
                getLogger().warn("Not enough space in Inventory to plunder pyramid");
                getLogger().info("Depositing Inventory");
                if (Bank.depositInventory())
                    Delays.delay(300, 600, 450);
            }

            if (bot.vars.isSceptreUsed() && !Equipment.contains(sceptre) && !Inventory.contains(sceptre)) {
                if (Banking.contains(sceptre)) {
                    getLogger().info("Withdrawing " + sceptre);
                    if (Bank.withdraw(sceptre, 1))
                        Delays.delayUntil(() -> Inventory.contains(sceptre), 400, 600);
                } else {
                    ClientUI.showAlert("We do not have a sceptre");
                    bot.stopBot("We do not have a sceptre");
                }
            } else if (!Inventory.contains(bot.vars.getPotion())) {
                if (Banking.contains(bot.vars.getPotion())) {
                    getLogger().info("Withdrawing 1 " + bot.vars.getPotion());
                    if (Bank.withdraw(bot.vars.getPotion(), 1))
                        Delays.delayUntil(() -> Inventory.contains(bot.vars.getPotion()), 300, 600);
                } else {
                    ClientUI.showAlert("We do not have " + bot.vars.getPotion());
                    bot.stopBot("We do not have " + bot.vars.getPotion());
                }
            } else if (!Inventory.contains(bot.vars.getFood())) {
                if (Banking.contains(bot.vars.getFood())) {
                    getLogger().info("Withdrawing 5 " + bot.vars.getFood());
                    if (Bank.withdraw(bot.vars.getFood(), 5))
                        Delays.delayUntil(() -> Inventory.contains(bot.vars.getFood()), 300, 600);
                } else {
                    ClientUI.showAlert("We do not have " + bot.vars.getFood());
                    bot.stopBot("We do not have " + bot.vars.getFood());
                }
            } else {
                getLogger().info("We have all required items, Closing bank");
                if (Bank.close())
                    Delays.delay(300, 600, 450);
            }
        }
    }
}
