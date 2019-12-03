package com.darksage.bots.pyramidPlunder.OutPyramid.Branches;

import com.RegalAPI.playersense.CustomPlayerSense;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.Eat;
import com.darksage.bots.pyramidPlunder.OutPyramid.Leaves.SipPotion;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * Created by Team Regal
 */
public class CheckPlayerStatus extends BranchTask {

    private SipPotion potion = new SipPotion();
    private Eat eat = new Eat();
    private CheckMummyRoom mummyRoom = new CheckMummyRoom();

    @Override
    public TreeTask failureTask() {
        if (Health.isPoisoned())
            return potion;
        return eat;
    }

    @Override
    public TreeTask successTask() {
        return mummyRoom;
    }

    @Override
    public boolean validate() {
        int hp = CustomPlayerSense.Key.PYRAMID_MIN_HEALTH.getAsInteger();
        return !Health.isPoisoned() && Health.getCurrentPercent() >= hp;
    }
}
