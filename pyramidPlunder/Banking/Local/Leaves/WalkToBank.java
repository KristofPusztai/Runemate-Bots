package com.darksage.bots.pyramidPlunder.Banking.Local.Leaves;

import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class WalkToBank extends LeafTask {

    @Override
    public void execute() {
        Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(Variables.BANK_AREA);
        if (path != null) {
            getLogger().info("Walking to bank");
            path.step(true);
        }
    }
}
