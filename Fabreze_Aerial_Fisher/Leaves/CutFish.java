package Fabreze.bots.Fabreze_Aerial_Fisher.Leaves;

import Fabreze.bots.Fabreze_Aerial_Fisher.AerialFisherMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CutFish extends LeafTask {
    @Override
    public void execute(){
        SpriteItem selected = Inventory.getSelectedItem();

        if (selected == null) {
            // No selected item, use our knife.
            SpriteItem knife = Inventory.newQuery().names("Knife").results().first();

            if (knife != null && knife.interact("Use"))
                Execution.delay(400, 1200);
                return;
        }

        SpriteItem fish = Inventory.newQuery().names("Bluegill","Common tench","Mottled eel","Greater siren").results().random();

        if (fish != null && fish.interact("Use"))
            Execution.delayWhile(() -> Inventory.getQuantity("Bluegill","Common tench","Mottled eel","Greater siren") >0,() -> ((AerialFisherMain) Environment.getBot()).cutfishtrigger, 15000, 30000);
    }
}