package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class MoveToDispenser extends LeafTask {

    private PredefinedPath path = PredefinedPath.create(new Coordinate(1940, 4962, 0));

    private Varbit adamantitedispenser = Varbits.load(945);
    private Varbit mithrildispenser = Varbits.load(944);
    private Varbit golddispenser = Varbits.load(947);
    private Varbit irondispenser = Varbits.load(942);
    private Varbit steeldispenser = Varbits.load(943);


    @Override
    public void execute(){
        path.setStepDeviation(1);
        path.step();
        Execution.delayUntil(() -> irondispenser.getValue() > 0 || steeldispenser.getValue() > 0 || mithrildispenser.getValue() > 0 || golddispenser.getValue() > 0 || adamantitedispenser.getValue() > 0);
    }
}
