package Fabreze.bots.Fabreze_Cannoner.Leaves.Bank;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Landmark;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.logger.BotLogger;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.regex.Pattern;

public class GrabItems extends LeafTask {
    private CannonMain bot = (CannonMain) Environment.getBot();

    @Override
    public void execute(){//TODO: Add full inventory check

        Player player = Players.getLocal();
        GameObject bank = GameObjects.newQuery().actions("Bank").results().nearest();

        if (Bank.isOpen()){//Withdraws items from getters
            String item = getItem();
            String pattern = getPattern();
            if (item != null){
                int amount = bot.bankitems.get(item);
                Bank.withdraw(item, amount);
                Execution.delayUntil(() -> Inventory.contains(item), 2000);
            }
            else if (pattern != null){
                int amount = bot.regexbankitems.get(pattern);
                Bank.withdraw(Regex.getPatternForContainsString(pattern), amount);
                Execution.delayUntil(() -> Inventory.contains(Regex.getPatternForContainsString(pattern)), 2000);
            }
            else {//sets bank to false, ending banking cycle
                bot.bank = false;
            }
        }
        else if (player != null && bank != null && bank.isVisible()){
            if (bank.interact("Bank")){
                Execution.delayUntil(Bank::isOpen, player::isMoving, 2000 );
                if (Bank.isOpen()){
                    bot.bank = true;
                }
            }
        }
        else{

            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(Landmark.BANK);

            if (path!= null && player != null){
                path.step(Path.TraversalOption.PREFER_VIEWPORT);
                Execution.delayUntil(() -> !player.isMoving(), player::isMoving, 2000);
            }
            else {
                ClientUI.showAlert(BotLogger.Level.WARN, "WebPath to Bank is Null");
                Execution.delayUntil(() ->path!= null);
            }
        }
    }

    private String getItem(){
        String item = null;

        String[] itemname = bot.bankitems.keySet().toArray(new String[0]);

        for (String i: itemname){
            if (Inventory.contains(i)){
                if (Inventory.getQuantity(i) < bot.bankitems.get(i)){
                    item = i;
                    break;
                }
            }
            else {
                item = i;
                break;
            }
        }
        return item;
    }
    private String getPattern(){
        String pattern = null;

        String [] regexpattern = bot.regexbankitems.keySet().toArray(new String[0]);
        for (String i: regexpattern){
            Pattern regex = Regex.getPatternForContainsString(i);
            if (Inventory.contains(regex)){
                if (Inventory.getQuantity(regex) < bot.regexbankitems.get(i)){
                    pattern = i;
                    break;
                }
            }
            else {
                pattern = i;
                break;
            }
        }
        return pattern;
    }
}
