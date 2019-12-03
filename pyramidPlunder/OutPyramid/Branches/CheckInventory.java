package com.darksage.bots.pyramidPlunder.OutPyramid.Branches;

import com.darksage.bots.pyramidPlunder.Banking.CastleWars.Branches.CheckAtBankCWars;
import com.darksage.bots.pyramidPlunder.Banking.Local.Branches.CheckAtBank;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckInventory extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private CheckPlayerStatus status = new CheckPlayerStatus();
    private CheckAtBank bank = new CheckAtBank();
    private CheckAtBankCWars bankCWars = new CheckAtBankCWars();

    @Override
    public TreeTask failureTask() {
        if (bot.vars.isLocalBanking() || !bot.vars.isLocalBanking() && Equipment.contains("Pharaoh's sceptre") || !bot.vars.isLocalBanking() && Inventory.contains("Pharaoh's sceptre"))
            return bank;
        return bankCWars;
    }

    @Override
    public TreeTask successTask() {
        return status;
    }

    @Override
    public boolean validate() {
        bot.vars.setBanked(Inventory.contains(bot.vars.getPotion()) && Inventory.contains(bot.vars.getFood()) && Inventory.getEmptySlots() >= 10);
        if (!bot.vars.isSceptreUsed()) {
            bot.vars.setSceptreCharged(true);
        }
        boolean ring;
        if (!bot.vars.isLocalBanking()) {
            ring = Equipment.contains(Variables.RING_DUELING);
        } else {
            ring = true;
        }
        boolean recharge;
        if (bot.vars.isSceptreUsed() && !bot.vars.isSceptreCharged()) {
            if (Varbits.load(4605).getValue() == 1) {
                recharge = true;
            } else {
                recharge = Inventory.getQuantity(Variables.stoneRecharge) >= Variables.STONE_ITEMS;
            }
        } else {
            recharge = true;
        }
        return bot.vars.isBanked() && ring && recharge;
    }
}
