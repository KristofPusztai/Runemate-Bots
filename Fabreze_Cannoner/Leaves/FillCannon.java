package Fabreze.bots.Fabreze_Cannoner.Leaves;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import javafx.application.Platform;

public class FillCannon extends LeafTask {

    private CannonMain bot = (CannonMain) Environment.getBot();


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
        GameObject cannon = GameObjects.newQuery().names("Dwarf multicannon").on(bot.cannon).results().nearest();

        int cannonballcache = Inventory.getQuantity("Cannonball");


        if (player != null && cannon != null && cannon.isVisible()){
            if (cannon.interact("Fire")){
                Execution.delayUntil(() -> Inventory.getQuantity("Cannonball") < cannonballcache, player::isMoving, 3000);
                if (Inventory.getQuantity("Cannonball") < cannonballcache){
                    bot.firing = bot.cannonammo != 0;
                    bot.cannonammo = 30;
                    bot.getPlatform().invokeLater(() -> Platform.runLater(() -> bot.controller.cannonammo.setText(Integer.toString(bot.cannonammo))));
                    bot.mincannonammo = Random.nextInt(0, 18);
                }
                else{
                    bot.firing = true;
                }
            }
        }
        else {
            Camera.concurrentlyTurnTo(cannon);
        }
    }

}
