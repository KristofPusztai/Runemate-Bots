package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractZipline;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtZipline extends BranchTask {

    private IsAtTree isAtTree = new IsAtTree();
    private InteractZipline interactZipline= new InteractZipline();

    private Area roof3 = new Area.Rectangular(new Coordinate(3283, 3176, 3), new Coordinate(3302, 3160, 3));

    @Override
    public boolean validate(){
        return roof3.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactZipline; }

    @Override
    public TreeTask failureTask() { return isAtTree; }
}
