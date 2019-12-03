package Fabreze.bots.Fabreze_Cannoner.Leaves;

import Fabreze.bots.Fabreze_Cannoner.Branches.IsPotion;
import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DrinkPotion extends LeafTask {

    CannonMain bot = (CannonMain) Environment.getBot();

    private IsPotion isPotion;

    public DrinkPotion(IsPotion isPotion){
        this.isPotion = isPotion;
    }

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

        if (isPotion.prayercheck()){
            SpriteItem prayerpot = Inventory.newQuery().names(Regex.getPatternContainingOneOf("Prayer", "restore")).results().first();
            if (prayerpot != null && prayerpot.interact("Drink")){
                Execution.delayUntil(() -> !prayerpot.isValid(), 2000);
            }
        }
        else if (isPotion.skillcheck()){
            Skill skill = isPotion.getSkill();
            if (skill == Skill.STRENGTH){
                SpriteItem potion = Inventory.newQuery().names(Regex.getPatternForContainsString("rength")).results().first();
                if (potion != null && potion.interact("Drink")){
                    Execution.delayUntil(() -> !potion.isValid(), 2000);
                }
            }
            if (skill == Skill.ATTACK){
                SpriteItem potion = Inventory.newQuery().names(Regex.getPatternForContainsString("ttack")).results().first();
                if (potion != null && potion.interact("Drink")){
                    Execution.delayUntil(() -> !potion.isValid(), 2000);
                }
            }
            if (skill == Skill.DEFENCE){
                SpriteItem potion = Inventory.newQuery().names(Regex.getPatternForContainsString("efence")).results().first();
                if (potion != null && potion.interact("Drink")){
                    Execution.delayUntil(() -> !potion.isValid(), 2000);
                }
            }
            if (skill == Skill.RANGED){
                SpriteItem potion = Inventory.newQuery().names(Regex.getPatternContainingOneOf("Ranging", "astion")).results().first();
                if (potion != null && potion.interact("Drink")){
                    Execution.delayUntil(() -> !potion.isValid(), 2000);
                }
            }
            if (skill == Skill.MAGIC){
                SpriteItem potion = Inventory.newQuery().names(Regex.getPatternForContainsString("Magic")).results().first();
                if (potion != null && potion.interact("Drink")){
                    Execution.delayUntil(() -> !potion.isValid(), 2000);
                }
            }
        }
        SpriteItem vial = Inventory.newQuery().names("Vial").results().first();
        if (bot.dropvials && vial != null && vial.interact("Drop")){
            Execution.delayUntil(() -> !vial.isValid(), 2000);
        }
    }
}
