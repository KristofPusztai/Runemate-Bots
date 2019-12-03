package Fabreze.bots.Fabreze_Cannoner.Leaves;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SafeLogOut extends LeafTask {

    private CannonMain bot = (CannonMain) Environment.getBot();
    private MoveToSafe moveToSafe = new MoveToSafe();

    @Override
    public void execute(){

        Magic spell = Magic.getSelected();
        if ( spell != null){//Magic selection handler
            spell.deactivate();
            Execution.delayUntil(() -> !spell.isSelected(), 2000);
            return;
        }
        SpriteItem selected = Inventory.getSelectedItem();
        if (Inventory.isItemSelected() && selected != null){//Item selection handler
            if (selected.click()){
                Execution.delayUntil(() -> !Inventory.isItemSelected(), 1500, 3000);
            }
            return;
        }

        Player player = Players.getLocal();

        if (player != null && Inventory.contains("Cannon base")){
            if (!bot.safezone || bot.safespotloc == null ||  bot.safespotloc.equals(player.getPosition())){
                if (RuneScape.isLoggedIn() && player.getTarget() == null){
                    RuneScape.logout();
                    Execution.delayUntil(() -> !RuneScape.isLoggedIn(), 3000);
                }
                if (!RuneScape.isLoggedIn()){
                    ClientUI.showAlert("Bot stopped due to lack of food/cannon balls");
                    bot.stop("Bot stopped due to lack of food/cannon balls");
                }
            }
            else {
                moveToSafe.execute();
            }
        }
        else {
            GameObject cannon = GameObjects.newQuery().names("Dwarf multicannon").on(bot.cannon).results().first();
            if (cannon != null){
                cannon.interact("Pick-up");
                Execution.delayUntil(() -> Inventory.contains("Cannon base"), 3000);
            }
            else {
                bot.stop("No cannon detected");
            }
        }
    }

}
