package com.darksage.bots.pyramidPlunder.Banking.Local.Branches;

import com.darksage.bots.pyramidPlunder.Banking.Local.Leaves.BankTask;
import com.darksage.bots.pyramidPlunder.Banking.Local.Leaves.HealthBankTask;
import com.darksage.bots.pyramidPlunder.Banking.Local.Leaves.SceptreBankTask;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckHealthBank extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private BankTask bank = new BankTask();
    private SceptreBankTask sceptre = new SceptreBankTask();
    private HealthBankTask health = new HealthBankTask();

    @Override
    public TreeTask failureTask() {
        return health;
    }

    @Override
    public TreeTask successTask() {
        if (bot.vars.isSceptreUsed() && bot.vars.isBanked() && !bot.vars.isSceptreCharged())
            return sceptre;
        return bank;
    }

    @Override
    public boolean validate() {
        return !Health.isPoisoned() && Health.getCurrentPercent() >= 80;
    }
}
