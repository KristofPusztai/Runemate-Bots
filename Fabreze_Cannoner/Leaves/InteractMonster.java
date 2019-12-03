package Fabreze.bots.Fabreze_Cannoner.Leaves;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractMonster extends LeafTask {

    private CannonMain bot = (CannonMain) Environment.getBot();
    private Player player = Players.getLocal();


    @Override
    public void execute() {
        if (player != Players.getLocal() && Players.getLocal()!= null){ //Updates player var after a logout
            player = Players.getLocal();
        }

        Npc monster = getMonster();

        if (player != null){

            if (monster != null && monster.isVisible()){
                Mouse.setSpeedMultiplier(2D);
                if (monster.interact("Attack")){
                    Execution.delayUntil(() -> player.getTarget() != null && monster.getTarget() != null, () -> player.getAnimationId() != -1, 900, 1200);
                    Mouse.setSpeedMultiplier(1D);
                }
            }

            else if (monster != null){
                Camera.concurrentlyTurnTo(monster);
            }

        }
    }


    private  Npc getMonster(){

        Npc monster = null;

        if (bot.location != null){

            int Xcoord = bot.location.getX();
            int Ycoord = bot.location.getY();
            int Zcoord = bot.location.getPlane();

            Coordinate upperbound = new Coordinate(Xcoord + bot.radius, Ycoord + bot.radius, Zcoord);
            Coordinate lowerbound = new Coordinate(Xcoord - bot.radius, Ycoord - bot.radius, Zcoord);

            Area lootarea = Area.rectangular(upperbound, lowerbound);

            for (int x = 0; x < bot.monsters.size(); x++) {
                Npc target = Npcs.newQuery().names(bot.monsters.get(x)).targeting(player).within(lootarea).results().nearest();
                if (target != null && (target.getHealthGauge() == null || target.getHealthGauge().getPercent()>0)){
                    monster = target;
                    break;
                }
                target = Npcs.newQuery().names(bot.monsters.get(x)).targeting(null, null).within(lootarea).results().nearest();
                if (target != null && (target.getHealthGauge() == null || target.getHealthGauge().getPercent()>0)){
                    monster = target;
                    break;
                }
            }
        }
        else {
            for (int x = 0; x < bot.monsters.size(); x++) {
                Npc target = Npcs.newQuery().names(bot.monsters.get(x)).targeting(player).results().nearest();
                if (target != null && (target.getHealthGauge() == null || target.getHealthGauge().getPercent()>0)){
                    monster = target;
                    break;
                }
                target = Npcs.newQuery().names(bot.monsters.get(x)).targeting(null, null).results().nearest();
                if (target != null && (target.getHealthGauge() == null || target.getHealthGauge().getPercent()>0)){
                    monster = target;
                    break;
                }
            }
        }
        return monster;
    }
}
