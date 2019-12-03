package Fabreze.bots.Fabreze_Fighter.Branches;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.GrabLoot;
import Fabreze.bots.Fabreze_Fighter.Leaves.InteractMonster;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.regex.Pattern;

public class IsLootPostCB extends BranchTask {

    private FighterMain bot = (FighterMain) Environment.getBot();

    private InteractMonster interactMonster = new InteractMonster();

    private GroundItem loot;

    @Override
    public boolean validate(){
        if (!bot.cbloot){//If 'Only Loot When Not in Combat' isn't selected, puts loot check before combat check
            return false;
        }
        else {
            loot = lootobject();
            return (!bot.lootlist.isEmpty() && (loot = lootobject()) != null) || (!bot.regexloot.isEmpty() && (loot = regexlootobject()) != null);
        }
    }
    @Override
    public TreeTask successTask() { return new GrabLoot(loot); }

    @Override
    public TreeTask failureTask() { return interactMonster; }


    private GroundItem lootobject(){

        GroundItem object = null;
        SpriteItem food = Inventory.newQuery().actions("Eat").results().first();

        if (!Inventory.isFull() || (bot.eatfoodtoloot && food != null)){
            if (bot.location != null){

                int Xcoord = bot.location.getX();
                int Ycoord = bot.location.getY();
                int Zcoord = bot.location.getPlane();

                Coordinate upperbound = new Coordinate(Xcoord + bot.radius, Ycoord + bot.radius, Zcoord);
                Coordinate lowerbound = new Coordinate(Xcoord - bot.radius, Ycoord - bot.radius, Zcoord);

                Area lootarea = Area.rectangular(upperbound, lowerbound);

                for(int x = 0; x < bot.lootlist.size(); x++){
                    GroundItem loot = GroundItems.newQuery().names(bot.lootlist.get(x)).reachable().results().nearest();
                    if (lootarea.contains(loot)){
                        object = loot;
                        break;
                    }
                }

            }
            else{
                for(int x = 0; x < bot.lootlist.size(); x++){
                    GroundItem loot = GroundItems.newQuery().names(bot.lootlist.get(x)).reachable().results().nearest();
                    if (loot != null){
                        object = loot;
                        break;
                    }
                }
            }
        }
        else { //Handles stackable items like brimstone keys and runes
            for (String s : bot.lootlist){
                GroundItem loot = GroundItems.newQuery().names(s).reachable().results().nearest();
                if (loot != null && loot.getDefinition() != null){
                    boolean RuneBoolean = loot.getDefinition().getName().contains("rune") && Inventory.contains(loot.getDefinition().getName());
                    boolean BrimstoneBoolean = loot.getDefinition().getName().equals("Brimstone key") && Inventory.contains(loot.getDefinition().getName());
                    if (RuneBoolean || BrimstoneBoolean){
                        object = loot;
                    }
                }
            }
        }
        return object;
    }
    private GroundItem regexlootobject(){

        GroundItem object = null;
        SpriteItem food = Inventory.newQuery().actions("Eat").results().first();

        if (!Inventory.isFull() || (bot.eatfoodtoloot && food != null)){
            if (bot.location != null){

                int Xcoord = bot.location.getX();
                int Ycoord = bot.location.getY();
                int Zcoord = bot.location.getPlane();

                Coordinate upperbound = new Coordinate(Xcoord + bot.radius, Ycoord + bot.radius, Zcoord);
                Coordinate lowerbound = new Coordinate(Xcoord - bot.radius, Ycoord - bot.radius, Zcoord);

                Area lootarea = Area.rectangular(upperbound, lowerbound);

                for(int x = 0; x < bot.regexloot.size(); x++){
                    Pattern pattern = Regex.getPatternForContainsString(bot.regexloot.get(x));
                    GroundItem loot = GroundItems.newQuery().names(pattern).reachable().results().nearest();
                    if (lootarea.contains(loot)){
                        object = loot;
                        break;
                    }
                }

            }
            else{
                for(int x = 0; x < bot.regexloot.size(); x++){
                    Pattern pattern = Regex.getPatternForContainsString(bot.regexloot.get(x));
                    GroundItem loot = GroundItems.newQuery().names(pattern).reachable().results().nearest();
                    if (loot != null){
                        object = loot;
                        break;
                    }
                }
            }
        }
        else { //Handles stackable items like brimstone keys and runes
            for (String x : bot.regexloot){
                Pattern pattern = Regex.getPatternForContainsString(x);
                GroundItem loot = GroundItems.newQuery().names(pattern).reachable().results().nearest();
                if (loot != null && loot.getDefinition() != null){
                    boolean RuneBoolean = loot.getDefinition().getName().contains("rune") && Inventory.contains(loot.getDefinition().getName());
                    boolean BrimstoneBoolean = loot.getDefinition().getName().equals("Brimstone key") && Inventory.contains(loot.getDefinition().getName());
                    if (RuneBoolean || BrimstoneBoolean){
                        object = loot;
                    }
                }
            }
        }
        return object;
    }
}
