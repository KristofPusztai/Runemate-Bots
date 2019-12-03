package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.DrinkPotion;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.osrs.local.hud.interfaces.Prayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPotion extends BranchTask {

    CannonMain bot = (CannonMain) Environment.getBot();

    private IsCannonDeployed isCannonDeployed = new IsCannonDeployed();
    private DrinkPotion drinkPotion = new DrinkPotion(this);

    @Override
    public boolean validate(){

        return prayercheck() || skillcheck();
    }
    @Override
    public TreeTask successTask() {
        return drinkPotion;
    }

    @Override
    public TreeTask failureTask() { return isCannonDeployed; }



    public boolean prayercheck(){
        boolean bool = false;


        if (bot.prayerpot && (100*Prayer.getPoints()/Prayer.getMaximumPoints()) <= bot.prayerval && Inventory.contains(Regex.getPatternContainingOneOf("Prayer", "restore"))) {
            bool = true;
        }
        return bool;
    }

    public boolean skillcheck(){
        boolean bool = false;

        boolean inventorycontains = Inventory.contains(Regex.getPatternContainingOneOf("trength", "ttack", "efence", "Ranging", "Magic", "astion"));

        if (!bot.skilllist.isEmpty() && inventorycontains){
            for (Skill i: bot.skilllist){
                if (i.getCurrentLevel() == i.getBaseLevel()){
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }

    public Skill getSkill(){
        Skill bool = null;
        for (Skill i: bot.skilllist){
            if (i.getCurrentLevel() == i.getBaseLevel()){
                bool = i;
                break;
             }
        }
        return bool;
    }
}