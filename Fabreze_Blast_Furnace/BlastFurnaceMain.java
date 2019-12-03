package Fabreze.bots.Fabreze_Blast_Furnace;

import Fabreze.bots.Fabreze_Blast_Furnace.Branches.IsContinue;
import Fabreze.bots.Fabreze_Blast_Furnace.GUI.BlastFurnaceGUIController;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class BlastFurnaceMain extends TreeBot implements EmbeddableUI, SkillListener, InventoryListener {
    public String bar = null;
    public String Ore = null;
    public String stopatvalue = "0";
    public int depositedcoalamt = 0;
    public boolean isoredeposited = false;
    public BranchTask coalclass;
    public int coalbagamt = 0;
    public boolean iscoalneeded = false;
    public boolean coalbag = false;
    public boolean bucketofwater = false;
    public boolean icegloves = false;
    public boolean goldsmithgauntlets = false;
    public int cofferamt = 0;
    public boolean start = false;
    public int coalcost;
    public boolean staminapot = false;
    public boolean dropvial = false;

    private BlastFurnaceGUIController controller = new BlastFurnaceGUIController(this);

    private final FXMLLoader loader = new FXMLLoader();
    private ObjectProperty<? extends Node> botInterfaceProperty;

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            try {
                loader.setController(controller);
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_Blast_Furnace/GUI/Blast_FurnaceGUI.fxml");
                final Parent document = loader.load(input);

                botInterfaceProperty = new SimpleObjectProperty<>(document);
            } catch (final IOException e) {
                Environment.getLogger().debug("Failed to load ");
                ClientUI.showAlert("Failed to load a resource ");
                e.printStackTrace();
            }
        }
        return botInterfaceProperty;
    }
    //Sets GUI

    private void botstopper(){
        if (controller.clock!=null){
            if (stopatvalue.equals(controller.clock.getText())){
                this.stop("timer reached");
            }
        }
    }
    @Override
    public void onStart(String... args){
        setEmbeddableUI(this);
        getEventDispatcher().addListener(this);
        new LoopingThread(this::botstopper, 1000).start();
        Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR); //Sets MLP mouse path generator instead of CLOUSE
        Execution.delayUntil(() -> controller.clock != null);
        new LoopingThread(() -> Platform.runLater(() -> controller.xptracker.setText(Math.round(controller.xpgain* 3600.00/controller.stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
    }
    public void onExperienceGained(SkillEvent event) {
        controller.xp = Skill.SMITHING.getExperienceAsPercent();
        controller.xpgain += event.getChange();

        Platform.runLater(() -> controller.xppercent.setText(controller.xp + "%")); //Modifies proressbar label
        Platform.runLater(() -> controller.progressbar.setProgress(controller.xp/100.0)); //Modifies Progressbar progress
    }
    public boolean leveluptrigger = false;
    public void onLevelUp(SkillEvent event) {
        leveluptrigger = true;
        int playerlevel = Skill.SMITHING.getCurrentLevel();
        Platform.runLater(() -> controller.level.setText(playerlevel + "")); //modifies level text
    }
        @Override
    public TreeTask createRootTask() {
        Execution.delayUntil(() -> start);
        return new IsContinue();}
}
