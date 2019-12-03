package com.darksage.bots.pyramidPlunder.Banking.CastleWars.Branches;

import com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves.TeleportCastleWars;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckAtBankCWars extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private CheckAtChest check = new CheckAtChest();
    private TeleportCastleWars tele = new TeleportCastleWars();

    @Override
    public TreeTask failureTask() {
        return tele;
    }

    @Override
    public TreeTask successTask() {
        bot.checkTimeLimit();
        return check;
    }

    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return Variables.CASTLE_WARS.contains(bot.getPlayer());
    }
}
