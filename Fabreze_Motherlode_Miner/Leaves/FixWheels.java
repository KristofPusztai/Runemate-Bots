package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.Objects;

public class FixWheels  extends LeafTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    private PredefinedPath tostrut =  PredefinedPath.create(new Coordinate(3741, 5666, 0));
    private Area nearhammercrate = new Area.Rectangular(new Coordinate(3746, 5675, 0), new Coordinate(3755, 5668, 0));
    private Player player = Players.getLocal();
    private PredefinedPath tohammercrate = PredefinedPath.create(new Coordinate(3750, 5667, 0), new Coordinate(3751, 5673, 0));


    @Override
    public void execute(){

        GameObject brokenstrut = GameObjects.newQuery().names("Broken strut").actions("Hammer").results().nearest();
        GameObject hammercrate = GameObjects.newQuery().names("Crate").actions("Search").on(new Coordinate(3752, 5674, 0)).results().first();


        if (!Inventory.contains("Hammer")){
            if (hammercrate != null && hammercrate.isVisible()){
                hammercrate.interact("Search");
                Execution.delayUntil(() -> Inventory.contains("Hammer"),4000 );
            }
            else{
                Camera.concurrentlyTurnTo(hammercrate);
                if (!nearhammercrate.contains(player)){
                    tohammercrate.setStepDeviation(1);
                    tohammercrate.step();
                    Execution.delayUntil(() -> !player.isMoving(), 5000, 7000);
                }
            }
        }
        else if (brokenstrut != null && brokenstrut.isVisible() && brokenstrut.interact("Hammer")){
            Execution.delayUntil(() -> bot.smithexptrig, 7000, 10000);
            bot.smithexptrig = false;
        }

        else{
            if (brokenstrut != null){
                tostrut.setStepDeviation(2);
                tostrut.step();
                Camera.concurrentlyTurnTo(brokenstrut);
                Execution.delayUntil(Objects.requireNonNull(brokenstrut)::isVisible, 10000);
            }
        }
    }
}
