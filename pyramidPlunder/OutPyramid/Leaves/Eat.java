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
public class Eat extends LeafTask {

    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();

    @Override
    public void execute() {
        SpriteItem food = Inventory.newQuery().names(bot.vars.getFood()).results().last();
        if (food != null) {
            getLogger().info("Eating " + bot.vars.getFood());
            if (food.interact("Eat"))
                Delays.delay(300, 600, 450);
        }
    }
}
