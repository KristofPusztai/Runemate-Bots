package com.darksage.bots.pyramidPlunder.Banking.Local.Branches;

import com.darksage.bots.pyramidPlunder.Banking.Local.Leaves.WalkToBank;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckAtBank extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private CheckHealthBank check = new CheckHealthBank();
    private WalkToBank walk = new WalkToBank();

    @Override
    public TreeTask failureTask() {
        return walk;
    }

    @Override
    public TreeTask successTask() {
        bot.checkTimeLimit();
        return check;
    }

    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return Variables.BANK_AREA.contains(bot.getPlayer());
    }
}
