package com.darksage.bots.pyramidPlunder.Banking.Local.Leaves;

import com.RegalAPI.utility.Banking;
import com.RegalAPI.utility.RegalCamera;
import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.LocatableEntity;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Banks;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class HealthBankTask extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        LocatableEntity theBank = Banks.newQuery().results().nearest();
        bot.setIgnoreAdditions(true);

        if (theBank.getVisibility() < 55) {
            getLogger().info("Turning camera to bank");
            RegalCamera.makeVisible(theBank);
            Delays.delay(300, 600, 450);
        }

        boolean antidote = Health.isPoisoned();
        boolean eat = Health.getCurrentPercent() <= 80;

        if (antidote && !Inventory.contains(bot.vars.getPotion())) {
            if (!Bank.isOpen()) {
                getLogger().info("opening bank");
                if (Bank.open())
                    Delays.delay(300, 600, 450);
            }
            if (Bank.isOpen()) {
                if (Inventory.isFull()) {
                    getLogger().info("Depositing inventory");
                    Bank.depositInventory();
                    Delays.delayUntil(() -> Inventory.isEmpty(), 400, 600);
                } else if (antidote) {
                    if (Banking.contains(bot.vars.getPotion())) {
                        getLogger().info("Withdrawing " + bot.vars.getPotion() + " to cure poison");
                        Bank.withdraw(bot.vars.getPotion(), 1);
                        Delays.delayUntil(() -> Inventory.contains(bot.vars.getPotion()), 300, 600);
                    } else {
                        getLogger().severe("we are out of " + bot.vars.getPotion());
                        bot.stopBot("We are out of " + bot.vars.getPotion());
                    }
                } else {
                    getLogger().info("Closing bank");
                    Bank.close();
                }
            }
        } else if (antidote && Inventory.contains(bot.vars.getPotion())) {
            bot.setIgnoreAdditions(false);
            if (Bank.isOpen()) {
                getLogger().info("Closing bank");
                Bank.close();
                Delays.delay(200, 400, 300);
            }
            SpriteItem anti = Inventory.newQuery().names(bot.vars.getPotion()).results().first();
            if (anti != null) {
                getLogger().info("Sipping " + bot.vars.getPotion());
                if (anti.click())
                    Delays.delay(300, 600, 450);
            }
        } else if (eat && !Inventory.contains(bot.vars.getFood())) {
            if (!Bank.isOpen()) {
                getLogger().info("opening bank");
                if (Bank.open())
                    Delays.delay(300, 600, 450);
            }
            if (Bank.isOpen()) {
                if (Inventory.isFull()) {
                    getLogger().info("Depositing inventory");
                    Bank.depositInventory();
                    Delays.delayUntil(() -> Inventory.isEmpty(), 400, 600);
                } else if (eat) {
                    if (Banking.contains(bot.vars.getFood())) {
                        getLogger().info("Withdrawing food to heal");
                        Bank.withdraw(bot.vars.getFood(), 8);
                        Delays.delayUntil(() -> Inventory.contains(bot.vars.getFood()), 300, 600);
                    } else {
                        getLogger().severe("We are out of " + bot.vars.getFood());
                        bot.stopBot("We are out of " + bot.vars.getFood());
                    }
                } else {
                    getLogger().info("Closing bank");
                    Bank.close();
                }
            }
        } else if (eat && Inventory.contains(bot.vars.getFood())) {
            bot.setIgnoreAdditions(false);
            if (Bank.isOpen()) {
                getLogger().info("Closing bank");
                Bank.close();
                Delays.delay(200, 400, 300);
            }
            SpriteItem food = Inventory.newQuery().names(bot.vars.getFood()).results().last();
            if (food != null) {
                getLogger().info("Eating " + bot.vars.getFood());
                if (food.interact("Eat"))
                    Delays.delay(300, 600, 450);
            }
        }
    }
}
