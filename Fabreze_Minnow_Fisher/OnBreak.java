package Fabreze.bots.Fabreze_Minnow_Fisher;

import Fabreze.bots.Fabreze_Minnow_Fisher.Leaves.BreakhandlerLogOut;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class OnBreak extends BranchTask {

    private OnPlatform onPlatform = new OnPlatform();
    private BreakhandlerLogOut breakhandlerLogOut = new BreakhandlerLogOut();

    @Override
    public boolean validate(){
        return ((MinnowFisherMain) Environment.getBot()).onbreak;
    }
    @Override
    public TreeTask successTask() { return breakhandlerLogOut; }

    @Override
    public TreeTask failureTask() { return onPlatform; }
}
