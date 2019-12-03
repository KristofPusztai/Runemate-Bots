package com.darksage.bots.pyramidPlunder.InPyramid.Branches;

import com.RegalAPI.playersense.CustomPlayerSense;
import com.RegalAPI.utility.AutoRetaliate;
import com.darksage.bots.pyramidPlunder.InPyramid.Leaves.EnableRetaliate;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.Eat;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.SipPotion;
import com.darksage.bots.pyramidPlunder.PyramidPlunder;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckHealth extends BranchTask {

    private Eat eat = new Eat();
    private SipPotion potion = new SipPotion();
    private CheckCurrentRoom room = new CheckCurrentRoom();
    private EnableRetaliate retaliate = new EnableRetaliate();
    private PyramidPlunder bot = (PyramidPlunder) Environment.getBot();
    private boolean auto;

    @Override
    public TreeTask failureTask() {
        if (Health.isPoisoned())
            return potion;
        if (!auto)
            return retaliate;
        return eat;
    }

    @Override
    public TreeTask successTask() {
        return room;
    }

    @Override
    public boolean validate() {
        int hp = CustomPlayerSense.Key.PYRAMID_MIN_HEALTH.getAsInteger();
        auto = AutoRetaliate.isEnabled();
        return !Health.isPoisoned() && Health.getCurrentPercent() >= hp && auto && Inventory.contains(bot.vars.getFood());
    }
}
