package com.darksage.bots.pyramidPlunder.OutPyramid.Branches;

import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.RechargeSceptre;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.StartPlunder;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.TeleportToPyramid;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckMummyRoom extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private CheckInPyramid check = new CheckInPyramid();
    private StartPlunder start = new StartPlunder();
    private TeleportToPyramid tele = new TeleportToPyramid();
    private RechargeSceptre recharge = new RechargeSceptre();

    @Override
    public TreeTask failureTask() {
        if (bot.vars.isSceptreUsed() && !Equipment.contains("Pharaoh's sceptre"))
            return tele;
        return check;
    }

    @Override
    public TreeTask successTask() {
        if (Equipment.contains("Pharaoh's sceptre") && Inventory.getQuantity(Variables.stoneRecharge) >= Variables.STONE_ITEMS)
            return recharge;
        return start;
    }

    @Override
    public boolean validate() {
        return !Npcs.newQuery().names(Variables.MUMMY).results().isEmpty();
    }
}
