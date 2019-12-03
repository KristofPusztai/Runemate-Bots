package Fabreze.bots.Fabreze_Aerial_Fisher.Leaves;

import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Displays Client UI Alert for Cormorant gloves
 */
public class DisplayCormorantAlert extends LeafTask {

    @Override
    public void execute() {
        ClientUI.showAlert("Must have Cormorant's glove equipped");
        Execution.delayUntil(() -> Equipment.contains("Cormorant's glove"));
    }
}
