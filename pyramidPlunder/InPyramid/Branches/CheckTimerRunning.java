package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.darksage.bots.pyramidPlunder.Data.RoomLevels;
import com.darksage.bots.pyramidPlunder.Data.Sceptre;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.OutPyramid.Branches.CheckInventory;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckTimerRunning extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private CheckHealth status = new CheckHealth();
    private CheckInventory check = new CheckInventory();

    @Override
    public TreeTask failureTask() {
        return check;
    }

    @Override
    public TreeTask successTask() {
        return status;
    }

    @Override
    public boolean validate() {
        if (!bot.vars.isUiSet()) {
            Execution.delayUntil(() -> bot.vars.isUiSet(), 120000);
            if (!bot.vars.isUiSet()) {
                getLogger().severe("UI not set, bot stopping");
                bot.stopBot("UI not set, bot stopping");
            }
        }
        bot.setPlayer(Players.getLocal());
        if (bot.vars.getCurrentLevel() != Skill.THIEVING.getBaseLevel()) {
            getLogger().info("Setting Highest level room that can be entered");
            bot.vars.setCurrentLevel(Skill.THIEVING.getBaseLevel());
            bot.vars.setFinalRoom(RoomLevels.getRoomLevel());
        }
        if (bot.vars.isSceptreUsed() && Equipment.contains(Variables.SCEPTRE)) {
            String sceptre = Equipment.getItemIn(Equipment.Slot.WEAPON).toString();
            boolean charged = Sceptre.isRechargeRequired(sceptre);
            bot.vars.setSceptreCharged(!charged);
        }
        return Varbits.load(2375).getValue() >= 1;
    }
}
