package com.darksage.bots.pyramidPlunder.Banking.CastleWars.Branches;

import com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves.EquipRing;
import com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves.RingBankTask;
import com.darksage.bots.pyramidPlunder.Banking.Local.Leaves.BankTask;
import com.darksage.bots.pyramidPlunder.Banking.Local.Leaves.SceptreBankTask;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckRingStatus extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private RingBankTask bankRing = new RingBankTask();
    private EquipRing equip = new EquipRing();
    private BankTask bank = new BankTask();
    private SceptreBankTask sceptre = new SceptreBankTask();

    @Override
    public TreeTask failureTask() {
        if (Inventory.contains(Variables.RING_DUELING))
            return equip;
        return bankRing;
    }

    @Override
    public TreeTask successTask() {
        if (bot.vars.isSceptreUsed() && bot.vars.isBanked() && !bot.vars.isSceptreCharged())
            return sceptre;
        return bank;
    }

    @Override
    public boolean validate() {
        return Equipment.contains(Variables.RING_DUELING);
    }
}
