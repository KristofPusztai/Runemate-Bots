package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckCurrentRoom extends BranchTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private CheckSpeartrapPassed check = new CheckSpeartrapPassed();
    private CheckPassedTrapsFinal trapsFinal = new CheckPassedTrapsFinal();

    @Override
    public TreeTask failureTask() {
        return check;
    }

    @Override
    public TreeTask successTask() {
        return trapsFinal;
    }

    @Override
    public boolean validate() {
        bot.vars.setCurrentRoom(Varbits.load(2376).getValue());
        bot.setIgnoreAdditions(false);
        return bot.vars.getCurrentRoom() == bot.vars.getFinalRoom();
    }
}
