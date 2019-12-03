package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves.TeleportCastleWars;
import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.EnterNextRoom;
import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.LeaveTomb;
import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.OpenGoldChest;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckChestLooted extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private EnterNextRoom next = new EnterNextRoom();
    private OpenGoldChest loot = new OpenGoldChest();
    private LeaveTomb leave = new LeaveTomb();
    private TeleportCastleWars teleport = new TeleportCastleWars();

    @Override
    public TreeTask failureTask() {
        return loot;
    }

    @Override
    public TreeTask successTask() {
        if (bot.vars.getCurrentRoom() == bot.vars.getFinalRoom()) {
            if (!bot.vars.isLocalBanking() && !Equipment.contains("Pharaoh's sceptre"))
                return teleport;
            return leave;
        }
        return next;
    }

    @Override
    public boolean validate() {
        return Varbits.load(2363).getValue() == 1;
    }
}
