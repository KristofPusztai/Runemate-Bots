package Fabreze.bots.Fabreze_Cannoner.Branches.Banking;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.Bank.GrabItems;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPotOut extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();

    private IsCBallOut isCBallOut = new IsCBallOut();
    private GrabItems grabItems = new GrabItems();

    @Override
    public boolean validate(){
        return bot.bankpot && isPotionMissing();
    }
    @Override
    public TreeTask successTask() { return grabItems; }

    @Override
    public TreeTask failureTask() { return isCBallOut; }


    private boolean isPotionMissing(){
        boolean missingpot = false;
        if (bot.prayerpot){
            SpriteItem prayerpot = Inventory.newQuery().names(Regex.getPatternContainingOneOf("Prayer", "restore")).results().first();
            if (prayerpot == null){
                missingpot = true;
            }
        }
        if (!bot.skilllist.isEmpty()){
            SpriteItem potion;
            if (bot.skilllist.contains(Skill.STRENGTH)){
                potion = Inventory.newQuery().names(Regex.getPatternForContainsString("rength")).results().first();
                if (potion == null){
                    missingpot = true;
                }
            }
            if (bot.skilllist.contains(Skill.ATTACK)){
                potion = Inventory.newQuery().names(Regex.getPatternForContainsString("ttack")).results().first();
                if (potion == null){
                    missingpot = true;
                }
            }
            if (bot.skilllist.contains(Skill.DEFENCE)){
                potion = Inventory.newQuery().names(Regex.getPatternForContainsString("efence")).results().first();
                if (potion == null){
                    missingpot = true;
                }
            }
            if (bot.skilllist.contains(Skill.RANGED)){
                potion = Inventory.newQuery().names(Regex.getPatternContainingOneOf("Ranging", "astion")).results().first();
                if (potion == null){
                    missingpot = true;
                }
            }
            if (bot.skilllist.contains(Skill.MAGIC)){
                potion = Inventory.newQuery().names(Regex.getPatternForContainsString("Magic")).results().first();
                if (potion == null){
                    missingpot = true;
                }
            }
        }
        return missingpot;
    }
}
