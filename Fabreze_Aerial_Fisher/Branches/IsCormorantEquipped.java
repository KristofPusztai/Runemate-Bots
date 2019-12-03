package Fabreze.bots.Fabreze_Aerial_Fisher.Branches;

import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.DisplayCormorantAlert;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

//import path.to.your.Fish
//import path.to.your.DisplayCormorantAlert

/**
 * NOTES:
 * Checks for Cormorant glove
 */
public class IsCormorantEquipped extends BranchTask {

    private HaveWorms haveWorms = new HaveWorms();
    private DisplayCormorantAlert displaycormorantalert = new DisplayCormorantAlert();

    @Override
    public boolean validate() {

        return Equipment.contains("Cormorant's glove");
    }

    @Override
    public TreeTask failureTask() {
        return displaycormorantalert;
    }

    @Override
    public TreeTask successTask() {
        return haveWorms;
    }
}
