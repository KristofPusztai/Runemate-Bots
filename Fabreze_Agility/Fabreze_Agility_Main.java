package Fabreze.bots.Fabreze_Agility;

import Fabreze.bots.Fabreze_Agility.GUI.Fabreze_AgilityController;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
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

public class Fabreze_Agility_Main extends TreeBot implements EmbeddableUI, SkillListener, InventoryListener {

    public boolean eatfood = false;
    public boolean markofgrace = false;
    public boolean markofgracetrig = false;

    public void onStart(String... args){
        setEmbeddableUI(this);
        getEventDispatcher().addListener(this);
        Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR); //Sets MLP mouse path generator instead of CLOUSE
        new LoopingThread(this::botstopper, 1000).start();
        Execution.delayUntil(() -> fabreze_agilityController.clock != null);
        new LoopingThread(() -> Platform.runLater(() -> fabreze_agilityController.xptracker.setText(Math.round(fabreze_agilityController.xpgain* 3600.00/fabreze_agilityController.stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
    }


    private final FXMLLoader loader = new FXMLLoader();
    private ObjectProperty<? extends Node> botInterfaceProperty;
    private Fabreze_AgilityController fabreze_agilityController = new Fabreze_AgilityController(this);

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            try {
                loader.setController(fabreze_agilityController);
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_Agility/GUI/Fabreze_AgilityGUI.fxml");
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

    public TreeTask createRootTask(){
        Execution.delayUntil(() -> fabreze_agilityController.startclass != null);
        return fabreze_agilityController.startclass;
    }

    private void botstopper(){
            if (fabreze_agilityController.clock != null){
                if (fabreze_agilityController.stopatvalue.equals(fabreze_agilityController.clock.getText())){
                    this.stop("timer reached");
                }
        }
    }

    public void onExperienceGained(SkillEvent event){
        fabreze_agilityController.xpgain += event.getChange();
        fabreze_agilityController.xp = Skill.AGILITY.getExperienceAsPercent();
        Platform.runLater(() -> fabreze_agilityController.experiencepercent.setText(fabreze_agilityController.xp + "%")); //Modifies proressbar label
        Platform.runLater(() -> fabreze_agilityController.experiencebar.setProgress(fabreze_agilityController.xp/100.0)); //Modifies Progressbar progress
    }

    public void onLevelUp(SkillEvent event) {
        int playerlevel = Skill.AGILITY.getCurrentLevel();
        Platform.runLater(() -> fabreze_agilityController.level.setText(playerlevel +""));
    }

    public void onItemAdded(ItemEvent event){
        SpriteItem markofgrace = Inventory.newQuery().names("Mark of grace").results().first();
        if (event.getItem().equals(markofgrace)){
            markofgracetrig = true;
        }
    }
}
