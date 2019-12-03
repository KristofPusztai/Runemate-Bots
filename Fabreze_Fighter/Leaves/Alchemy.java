package Fabreze.bots.Fabreze_Fighter.Leaves;

import Fabreze.bots.Fabreze_Fighter.Branches.IsAlc;
import Fabreze.bots.Fabreze_Fighter.FighterMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Alchemy extends LeafTask {//TODO: Implement object from parent branch init
    private FighterMain bot = (FighterMain) Environment.getBot();

    private IsAlc isAlc;

    public Alchemy(IsAlc isAlc){
        this.isAlc = isAlc;
    }

    @Override
    public void execute(){
        SpriteItem object = isAlc.alcobject();

        if (object != null){

            ItemDefinition definition = object.getDefinition();

            if (Magic.HIGH_LEVEL_ALCHEMY.isSelected() && definition !=  null && object.interact("Cast",  Magic.getSelected().getComponent().getName() + " -> " + definition.getName())){
                Execution.delayUntil(() -> bot.alchemized, 6000);
                bot.alchemized = false;
            }
            else {
                Magic.HIGH_LEVEL_ALCHEMY.activate();
                Execution.delayUntil(Magic.HIGH_LEVEL_ALCHEMY::isSelected, 3000);
            }
        }
    }
}
