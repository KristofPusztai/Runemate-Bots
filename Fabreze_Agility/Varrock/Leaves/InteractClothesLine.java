package Fabreze.bots.Fabreze_Agility.Varrock.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractClothesLine extends LeafTask {
    private Area roof2 = new Area.Rectangular(new Coordinate(3208, 3413, 3), new Coordinate(3201, 3417, 3));
    @Override
    public void execute(){
        GameObject clothesline = GameObjects.newQuery().names("Clothes line").actions("Cross").results().nearest();
        if (clothesline != null && clothesline.isVisible() && Players.getLocal() != null){
            if (clothesline.interact("Cross")){
                Execution.delayUntil(() -> roof2.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
                Execution.delay(900);
            }
        }
        else {
            Camera.concurrentlyTurnTo(clothesline, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
