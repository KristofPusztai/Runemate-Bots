package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.Callable;

public class MoveToStart extends LeafTask {


    private Coordinate start = new Coordinate(2474, 3436, 0);
    private Area endoflog = new Area.Absolute(new Coordinate(2474, 3429, 0));

    @Override
    public void execute(){
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(start);
        GameObject log = GameObjects.newQuery().names("Log balance").actions("Walk-across").results().first();
        if (Players.getLocal() != null){
            final Callable<Boolean> playermovingcondition = () -> Players.getLocal().isMoving();
            if (log != null && log.isVisible() && log.interact("Walk-across")) {
                Execution.delayUntil(() -> endoflog.contains(Players.getLocal()), playermovingcondition, 10000);
            }
            else if (path != null && path.step()){
                Execution.delayUntil(() -> !Players.getLocal().isMoving(), playermovingcondition, 2000);
            }
        }
    }
}
