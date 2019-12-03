package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class BreakhandlerLogOut extends LeafTask {
    @Override
    public void execute(){
        if (RuneScape.isLoggedIn()){
            if(Bank.isOpen()){
                Bank.close();
                Execution.delayUntil(() -> !Bank.isOpen(), 2000);
            }
            else{
                RuneScape.logout();
                GameEvents.Universal.LOGIN_HANDLER.disable();
            }
        }
    }

}
