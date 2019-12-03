package com.darksage.bots.pyramidPlunder.OutPyramid.Leaves;

import com.RegalAPI.utility.common_playersensed_methods.Delays;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by Team Regal
 */
public class SipPotion extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        SpriteItem pot = Inventory.newQuery().names(bot.vars.getPotion()).results().first();
        if (pot != null) {
            getLogger().info("Sipping " + bot.vars.getPotion());
            if (pot.click())
                Delays.delay(300, 600, 450);
        }
    }
}
