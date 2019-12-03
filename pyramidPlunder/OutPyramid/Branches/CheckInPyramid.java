package com.darksage.bots.pyramidPlunder.OutPyramid.Branches;

import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.ExitPyramid;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckInPyramid extends BranchTask {

    private ExitPyramid exit = new ExitPyramid();
    private CheckAtPyramidDoor door = new CheckAtPyramidDoor();

    @Override
    public TreeTask failureTask() {
        return door;
    }

    @Override
    public TreeTask successTask() {
        return exit;
    }

    @Override
    public boolean validate() {
        return Varbits.load(4605).getValue() == 1;
    }
}
