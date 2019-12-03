package com.darksage.bots.pyramidPlunder.InPyramid.Leaves;

import com.RegalAPI.utility.AutoRetaliate;
import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class EnableRetaliate extends LeafTask {

    @Override
    public void execute() {
        if (!AutoRetaliate.isEnabled()) {
            getLogger().info("Enabling Auto-Retaliate");
            AutoRetaliate.enable();
            Delays.delay(300, 600, 450);
        }
    }
}
