package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.Alchemy;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.logger.BotLogger;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAlc  extends BranchTask { //TODO: Add regex support

    private CannonMain bot = (CannonMain) Environment.getBot();

    private boolean UIAlert = false;


    private Alchemy alchemy = new Alchemy(this);
    private IsInCB isInCB = new IsInCB();

    @Override
    public boolean validate(){

        boolean sufficientrunes = ((Inventory.contains("Nature rune") && Inventory.containsAnyOf("Lava rune", "Fire rune") && Inventory.getQuantity("Lava rune", "Fire rune") >= 5)|| Inventory.contains("Nature rune") && (Equipment.contains(Regex.getPatternForContainsString("Fire")) || Equipment.contains(Regex.getPatternForContainsString("Smoke")) || Equipment.contains(Regex.getPatternForContainsString("fire"))));

        return !bot.alclist.isEmpty() && sufficientrunes && alcobject() != null;
    }
    @Override
    public TreeTask successTask() {
        if (UIAlert){
            UIAlert = false;
        }
        return alchemy;
    }

    @Override
    public TreeTask failureTask() {
        boolean sufficientrunes = (Inventory.contains("Nature rune") && Inventory.contains("Fire rune") && Inventory.getQuantity("Fire rune") >= 5);

        if (!UIAlert && !sufficientrunes && !bot.alclist.isEmpty()){
            ClientUI.showAlert(BotLogger.Level.WARN, "Insufficient Runes to Alc");
            UIAlert = true;
        }

        return isInCB;
    }
    public SpriteItem alcobject(){
        SpriteItem object = null;
        for (int x = 0; x < bot.alclist.size(); x++){
            if (Inventory.contains(bot.alclist.get(x))){
                object = Inventory.newQuery().names(bot.alclist.get(x)).results().first();
                break;
            }
        }
        return object;
    }
}
