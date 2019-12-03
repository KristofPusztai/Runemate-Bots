package Fabreze.bots.Fabreze_Minnow_Fisher.Leaves;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class BreakhandlerLogOut extends LeafTask {
    @Override
    public void execute(){
        if (RuneScape.isLoggedIn()){
            RuneScape.logout();
            GameEvents.Universal.LOGIN_HANDLER.disable();
        }
    }

}
