package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.PassSpeartrap;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckSpeartrapPassed extends BranchTask {

    private PassSpeartrap pass = new PassSpeartrap();
    private CheckChestLooted chest = new CheckChestLooted();

    @Override
    public TreeTask failureTask() {
        return pass;
    }

    @Override
    public TreeTask successTask() {
        return chest;
    }

    @Override
    public boolean validate() {
        return Varbits.load(2365).getValue() == 0;
    }
}
