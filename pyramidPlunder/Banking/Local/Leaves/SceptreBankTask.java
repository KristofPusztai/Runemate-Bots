package com.darksage.bots.pyramidPlunder.Banking.Local.Leaves;

import com.RegalAPI.utility.RegalCamera;
import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.LocatableEntity;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Banks;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class SceptreBankTask extends LeafTask {

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

        if (!Bank.isOpen()) {
            getLogger().info("opening bank");
            Bank.open();
            Delays.delay(300, 600, 450);
        }

        if (Bank.isOpen()) {
            if (!Bank.getWithdrawMode().equals(Bank.WithdrawMode.NOTE)) {
                getLogger().info("Setting withraw mode to noted");
                Bank.setWithdrawMode(Bank.WithdrawMode.NOTE);
                Delays.delay(300, 600, 450);
            }
            if (Inventory.getQuantity(Variables.stoneRecharge) < Variables.STONE_ITEMS) {
                if (Bank.getQuantity(Variables.stoneRecharge) >= Variables.STONE_ITEMS) {
                    getLogger().info("Withdrawing stone artefacts for sceptre recharge");
                    Bank.withdraw(Variables.stoneRecharge, Variables.STONE_ITEMS);
                    Delays.delayUntil(() -> Inventory.contains(Variables.stoneRecharge), 400, 600);
                } else {
                    getLogger().info("We do not have enough stone artefacts for recharge, stopping sceptre use");
                    ClientUI.showAlert("We do not have enough stone artefacts for recharge, stopping sceptre use");
                    bot.vars.setSceptreUsed(false);
                    if (!bot.vars.isLocalBanking())
                        bot.vars.setLocalBanking(true);
                }
            } else {
                getLogger().info("We have all required items to recharge, closing bank");
                Bank.close();
                bot.setIgnoreAdditions(false);
                Delays.delay(300, 600, 450);
            }
        }
    }
}
