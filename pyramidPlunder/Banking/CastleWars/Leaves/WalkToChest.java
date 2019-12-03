package com.darksage.bots.pyramidPlunder.Banking.CastleWars.Leaves;

import com.RegalAPI.utility.Pathing;
import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class WalkToChest extends LeafTask {

    @Override
    public void execute() {
        getLogger().info("Walking to Bank chest");
        Pathing.walkTo(Variables.BANK_CHEST);
        Delays.delay(300, 600, 450);
    }
}
