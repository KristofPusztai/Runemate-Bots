package Fabreze.bots.Fabreze_Agility.Varrock.Leaves;

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
    private Coordinate start = new Coordinate(3221, 3414, 0);
    private Area roof1 = new Area.Rectangular(new Coordinate(3219, 3419, 3), new Coordinate(3214, 3410, 3));

    @Override
    public void execute(){
        GameObject roughwall = GameObjects.newQuery().names("Rough wall").actions("Climb").results().nearest();
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(start);
        if (Players.getLocal() != null){
            final Callable<Boolean> playermovingcondition = () -> Players.getLocal().isMoving();
            if (roughwall != null && roughwall.isVisible()){
                if (roughwall.interact("Climb")){
                    Execution.delayUntil(() -> roof1.contains(Players.getLocal()), playermovingcondition, 2000);
                    Execution.delay(900);
                }
            }
            else if (path != null){
                    path.step();
                    Execution.delayUntil(() -> !Players.getLocal().isMoving(), playermovingcondition, 3000);
            }
        }
    }

}
