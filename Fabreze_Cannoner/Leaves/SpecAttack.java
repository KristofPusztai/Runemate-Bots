package Fabreze.bots.Fabreze_Cannoner.Leaves;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.cache.configs.EnumDefinition;
import com.runemate.game.api.hybrid.cache.configs.EnumDefinitions;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varps;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainers;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SpecAttack extends LeafTask {
    private CannonMain bot = (CannonMain) Environment.getBot();

    private Player player = Players.getLocal();
    private EnumDefinition enumDefinition = EnumDefinitions.load(906);


    @SuppressWarnings("ConstantConditions")
    private
    InterfaceComponent specattack = InterfaceContainers.getAt(160).getComponent(30);

    @Override
    public void execute(){

        int specpercnt = Varps.getAt(300).getValue();
        int specselected = Varps.getAt(301).getValue();

        SpriteItem weapon = Equipment.getItemIn(Equipment.Slot.WEAPON);
        if (player.getTarget() != null && bot.specattack && weapon != null && enumDefinition != null){
            int weaonid = weapon.getId();
            int usagecost = enumDefinition.getInt(weaonid);

            if (usagecost != 0 && specpercnt >= usagecost){
                if (specselected == 0){
                    specattack.interact("Use");
                    //noinspection ConstantConditions
                    Execution.delayUntil(() ->specselected == 1, 2000);
                }
            }
        }
    }

}
