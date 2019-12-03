package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.darksage.bots.pyramidPlunder.Data.NamesActions;
import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.CheckForSnakes;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckUrnsSearched extends BranchTask {

    private CheckForSnakes snakes = new CheckForSnakes();
    private CheckChestLooted chest = new CheckChestLooted();

    @Override
    public TreeTask failureTask() {
        return snakes;
    }

    @Override
    public TreeTask successTask() {
        return chest;
    }

    @Override
    public boolean validate() {
        Player me = Players.getLocal();
        return GameObjects.newQuery().names(NamesActions.URN.getName()).actions(NamesActions.URN.getAction()).within(new Area.Circular(me.getPosition(), 6)).results().isEmpty();
    }
}
