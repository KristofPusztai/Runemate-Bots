package com.darksage.bots.pyramidPlunder.Banking.CastleWars.Branches;

import com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves.WalkToChest;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckAtChest extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private CheckRingStatus check = new CheckRingStatus();
    private WalkToChest walk = new WalkToChest();

    @Override
    public TreeTask failureTask() {
        return walk;
    }

    @Override
    public TreeTask successTask() {
        return check;
    }

    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return Variables.BANK_CHEST.contains(bot.getPlayer());
    }
}
