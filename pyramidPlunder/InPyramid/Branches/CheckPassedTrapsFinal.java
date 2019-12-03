package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.PassSpeartrap;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckPassedTrapsFinal extends BranchTask {

    private CheckUrnsSearched urns = new CheckUrnsSearched();
    private PassSpeartrap pass = new PassSpeartrap();

    @Override
    public TreeTask failureTask() {
        return pass;
    }

    @Override
    public TreeTask successTask() {
        return urns;
    }

    @Override
    public boolean validate() {
        return Varbits.load(2365).getValue() == 0;
    }
}
