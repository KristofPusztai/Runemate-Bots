package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsDeposited extends BranchTask {

    private MotherlodeMain bot = (MotherlodeMain)Environment.getBot();

    private IsInLocation isInLocation = new IsInLocation();
    private IsWheelTurning isWheelTurning = new IsWheelTurning();

    @Override
    public boolean validate(){
        return bot.depositstatus;
    }

    @Override
    public TreeTask successTask(){ return isWheelTurning; }

    @Override
    public TreeTask failureTask(){ return isInLocation; }
}
