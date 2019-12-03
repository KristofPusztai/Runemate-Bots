package Fabreze.bots.Fabreze_Agility.MarksOfGrace;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ToggleRun extends LeafTask {
    @Override
    public void execute(){
        Traversal.toggleRun();
        Execution.delayUntil(Traversal::isRunEnabled, 2000);
    }
}
