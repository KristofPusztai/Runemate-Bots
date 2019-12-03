package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.OpenSarcophagus;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckSarcophagus extends BranchTask {

    private CheckChestLooted chest = new CheckChestLooted();
    private OpenSarcophagus open = new OpenSarcophagus();

    @Override
    public TreeTask failureTask() {
        return open;
    }

    @Override
    public TreeTask successTask() {
        return chest;
    }

    @Override
    public boolean validate() {
        return GameObjects.newQuery().names(NamesActions.SARCOPHAGUS.getName()).actions(NamesActions.SARCOPHAGUS.getAction()).results().isEmpty();
    }
}
