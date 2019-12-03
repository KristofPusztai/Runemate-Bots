package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.DisplayBFError;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtBlastFurnace extends BranchTask {

    private DisplayBFError displayBFError = new DisplayBFError();
    private IsCoinCofferFull isCoinCofferFull = new IsCoinCofferFull();

    private Area blastfurnace = new Area.Rectangular(new Coordinate(1956, 4956, 0), new Coordinate(1935, 4969, 0));

    @Override
    public boolean validate(){
        return blastfurnace.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return isCoinCofferFull; }

    @Override
    public TreeTask failureTask() { return displayBFError; }

}
