package Fabreze.bots.Fabreze_Fighter.Branches;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.Telegrab;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.logger.BotLogger;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsTelegrab extends BranchTask { //TODO: Add regex support

    private boolean UIAlert = false;

    private FighterMain bot = (FighterMain) Environment.getBot();


    private IsAlc isAlc = new IsAlc();
    private Telegrab telegrab = new Telegrab(this);

    @Override
    public boolean validate(){

        boolean sufficientrunes = (Inventory.contains( "Law rune") && Inventory.containsAnyOf("Air rune", "Dust rune"))|| (Inventory.contains("Law rune") && (Equipment.contains(Regex.getPatternForContainsString("air")) || Equipment.contains(Regex.getPatternForContainsString("Smoke")) || Equipment.contains(Regex.getPatternForContainsString("Air")) ));

        return !bot.telegrablist.isEmpty() && !Inventory.isFull() && telegrabchecker() && sufficientrunes;
    }
    @Override
    public TreeTask successTask() {
        if (UIAlert) {
            UIAlert = false;
        }
        return telegrab;
    }

    @Override
    public TreeTask failureTask() {
        boolean sufficientrunes = (Inventory.contains("Air rune") && Inventory.contains("Law rune")) || (Inventory.contains("Law rune") && Equipment.contains(Regex.getPatternForContainsString("Air")));

        if (!UIAlert && !sufficientrunes && !bot.telegrablist.isEmpty()){
            ClientUI.showAlert(BotLogger.Level.WARN, "Insufficient Runes to Telegrab ");
            UIAlert = true;
        }
        return isAlc;
    }


    private boolean telegrabchecker(){
        boolean bool = false;

        if (bot.location != null){

            int Xcoord = bot.location.getX();
            int Ycoord = bot.location.getY();
            int Zcoord = bot.location.getPlane();

            Coordinate upperbound = new Coordinate(Xcoord + bot.radius, Ycoord + bot.radius, Zcoord);
            Coordinate lowerbound = new Coordinate(Xcoord - bot.radius, Ycoord - bot.radius, Zcoord);

            Area lootarea = Area.rectangular(upperbound, lowerbound);

            for(int x = 0; x < bot.telegrablist.size(); x++){
                GroundItem loot = GroundItems.newQuery().names(bot.telegrablist.get(x)).results().nearest();
                if (lootarea.contains(loot)){
                    bool = true;
                    break;
                }
            }
        }
        else {
            for(int x = 0; x < bot.telegrablist.size(); x++){
                GroundItem loot = GroundItems.newQuery().names(bot.telegrablist.get(x)).results().nearest();
                if (loot != null){
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }
    public GroundItem telegrabobject(){
        GroundItem object = null;

        if (bot.location != null){

            int Xcoord = bot.location.getX();
            int Ycoord = bot.location.getY();
            int Zcoord = bot.location.getPlane();

            Coordinate upperbound = new Coordinate(Xcoord + bot.radius, Ycoord + bot.radius, Zcoord);
            Coordinate lowerbound = new Coordinate(Xcoord - bot.radius, Ycoord - bot.radius, Zcoord);

            Area lootarea = Area.rectangular(upperbound, lowerbound);

            for(int x = 0; x < bot.telegrablist.size(); x++){
                GroundItem loot = GroundItems.newQuery().names(bot.telegrablist.get(x)).results().nearest();
                if (lootarea.contains(loot)){
                    object = loot;
                    break;
                }
            }
        }
        else {
            for(int x = 0; x < bot.telegrablist.size(); x++){
                GroundItem loot = GroundItems.newQuery().names(bot.telegrablist.get(x)).results().nearest();
                if (loot != null){
                    object = loot;
                    break;
                    }
                }
            }

        return object;
    }

}
