package Fabreze.bots.Fabreze_Fighter.Leaves;

import Fabreze.bots.Fabreze_Fighter.Branches.IsTelegrab;
import Fabreze.bots.Fabreze_Fighter.FighterMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class Telegrab extends LeafTask {//TODO: Implement object from parent branch init
    private FighterMain bot = (FighterMain) Environment.getBot();


    private IsTelegrab istelegrab;

    public Telegrab(IsTelegrab isTelegrab){
        this.istelegrab = isTelegrab;
    }

    @Override
    public void execute(){

        Player player = Players.getLocal();

        GroundItem object = istelegrab.telegrabobject();

        if (player != null && object!= null){

            ItemDefinition definition = object.getDefinition();

            if (Magic.TELEKINETIC_GRAB.isSelected() && definition != null){
                if (object.interact("Cast", Magic.getSelected().getComponent().getName() + " -> " + definition.getName())){
                    Execution.delayUntil(() -> bot.lootadded, player::isMoving,6000);
                    bot.lootadded = false;
                }
            }
            else {
                Magic.TELEKINETIC_GRAB.activate();
                Execution.delayUntil(Magic.TELEKINETIC_GRAB::isSelected, 3000);
            }
        }
    }
}
