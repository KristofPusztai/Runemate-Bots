package Fabreze.bots.Fabreze_Fighter;

import Fabreze.bots.Fabreze_Fighter.Branches.IsContinue;
import Fabreze.bots.Fabreze_Fighter.GUI.FighterController;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.Execution;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FighterMain extends TreeBot implements EmbeddableUI, InventoryListener, SkillListener {
    //TODO: Add super combat potions
    //TODO: Add chatboxlistener to listen for slayer task ending
    public boolean foodcheck = false;

    public boolean bankinventory = false;
    public boolean bankfood = false;
    public boolean bank = false;
    public boolean eatfoodtoloot = false;
    public boolean bankpot = false;
    public boolean cbloot = false;

    public int healthval = 50;
    public int radius = 25;

    public boolean burybones = false;

    public boolean specattack = false;

    public Coordinate location;

    public Coordinate safespotloc;

    public void setLocation(){
        Player player = Players.getLocal();
        if (player != null){
            location = player.getPosition();
        }
    }
    public void setSafespotloc(){
        Player player = Players.getLocal();
        if (player != null){
            safespotloc = player.getPosition();
        }
    }

    public List<String> monsters = new ArrayList<>();
    public List<String> lootlist = new ArrayList<>();
    public List<String> regexloot = new ArrayList<>();
    public List<String> alclist = new ArrayList<>();
    public List<String> regexalclist = new ArrayList<>();
    public List<String> telegrablist = new ArrayList<>();
    public List<String> regextelegrablist = new ArrayList<>();

    public HashMap<String, Integer> regexbankitems = new HashMap<>();
    public HashMap<String, Integer> bankitems = new HashMap<>();

    public boolean safezone;

    public boolean dropvials = false;
    public boolean prayerpot = false;
    public int prayerval = 50;
    public List <Skill> skilllist = new ArrayList<>();


    @Override
    public void onStart(String... args){
        Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR); //Sets MLP mouse path generator instead of CLOUSE
        setEmbeddableUI(this);
        getEventDispatcher().addListener(this);
    }

    private final FXMLLoader loader = new FXMLLoader();
    private FighterController controller = new FighterController(this);
    private ObjectProperty<? extends Node> botInterfaceProperty;


    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            try {
                loader.setController(controller);
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_Fighter/GUI/FighterGUI.fxml");
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
    public boolean lootadded = false;
    public void onItemAdded(ItemEvent event) {
        ItemDefinition definition = event.getItem().getDefinition();
        if (definition != null && (lootlist.contains(definition.getName()) || telegrablist.contains(definition.getName()))){
            lootadded = true;
        }
    }
    public boolean alchemized = false;
    public void onItemRemoved(ItemEvent event) {
        ItemDefinition definition = event.getItem().getDefinition();
        if (definition != null && alclist.contains(definition.getName())){
            alchemized = true;
        }
    }

    public void onExperienceGained(SkillEvent event){
        if (event.getSkill() == Skill.STRENGTH){
            controller.strengthxp = Skill.STRENGTH.getExperienceAsPercent();
            controller.strengthxpgain = controller.strengthxpgain + event.getChange();

            Platform.runLater(() -> controller.strengthbar.setProgress(controller.strengthxp/100.0));
            Platform.runLater(() -> controller.strengthpercent.setText(controller.strengthxp + "%"));
        }
        if (event.getSkill() == Skill.ATTACK){
            controller.attackxp = Skill.ATTACK.getExperienceAsPercent();
            controller.attackxpgain = controller.attackxpgain + event.getChange();

            Platform.runLater(() -> controller.attackbar.setProgress(controller.attackxp/100.0));
            Platform.runLater(() -> controller.attackpercent.setText(controller.attackxp + "%"));
        }
        if (event.getSkill() == Skill.DEFENCE){
            controller.defencexp = Skill.DEFENCE.getExperienceAsPercent();
            controller.defencexpgain = controller.defencexpgain + event.getChange();

            Platform.runLater(() -> controller.defencebar.setProgress(controller.defencexp/100.0));
            Platform.runLater(() -> controller.defencepercent.setText(controller.defencexp + "%"));
        }
        if (event.getSkill() == Skill.RANGED){
            controller.rangexp = Skill.RANGED.getExperienceAsPercent();
            controller.rangexpgain = controller.rangexpgain + event.getChange();

            Platform.runLater(() -> controller.rangebar.setProgress(controller.rangexp/100.0));
            Platform.runLater(() -> controller.rangepercent.setText(controller.rangexp + "%"));
        }
        if (event.getSkill() == Skill.MAGIC){
            controller.magicxp = Skill.MAGIC.getExperienceAsPercent();
            controller.magicxpgain = controller.magicxpgain + event.getChange();

            Platform.runLater(() -> controller.magicbar.setProgress(controller.magicxp/100.0));
            Platform.runLater(() -> controller.magicpercent.setText(controller.magicxp + "%"));
        }
        if (event.getSkill() == Skill.CONSTITUTION){
            controller.hpxp = Skill.CONSTITUTION.getExperienceAsPercent();
            controller.hpxpgain = controller.hpxpgain + event.getChange();

            Platform.runLater(() -> controller.hpbar.setProgress(controller.hpxp/100.0));
            Platform.runLater(() -> controller.hppercent.setText(controller.hpxp + "%"));
        }
        if (event.getSkill() == Skill.SLAYER){
            controller.slayerxp = Skill.SLAYER.getExperienceAsPercent();
            controller.slayerxpgain = controller.slayerxpgain + event.getChange();

            Platform.runLater(() -> controller.slayerbar.setProgress(controller.slayerxp/100.0));
            Platform.runLater(() -> controller.slayerpercent.setText(controller.slayerxp + "%"));
        }
    }
    public void onLevelUp(SkillEvent event) {
        if (event.getSkill() == Skill.STRENGTH){
            int playerlevel = Skill.STRENGTH.getBaseLevel();
            Platform.runLater(() -> controller.strengthlvl.setText(Integer.toString(playerlevel)));
        }
        if (event.getSkill() == Skill.ATTACK){
            int playerlevel = Skill.ATTACK.getBaseLevel();
            Platform.runLater(() -> controller.attacklvl.setText(Integer.toString(playerlevel)));
        }
        if (event.getSkill() == Skill.DEFENCE){
            int playerlevel = Skill.DEFENCE.getBaseLevel();
            Platform.runLater(() -> controller.defencelvl.setText(Integer.toString(playerlevel)));
        }
        if (event.getSkill() == Skill.RANGED){
            int playerlevel = Skill.RANGED.getBaseLevel();
            Platform.runLater(() -> controller.rangelvl.setText(Integer.toString(playerlevel)));
        }
        if (event.getSkill() == Skill.MAGIC){
            int playerlevel = Skill.MAGIC.getBaseLevel();
            Platform.runLater(() -> controller.magiclvl.setText(Integer.toString(playerlevel)));
        }
        if (event.getSkill() == Skill.CONSTITUTION){
            int playerlevel = Skill.CONSTITUTION.getBaseLevel();
            Platform.runLater(() -> controller.hplvl.setText(Integer.toString(playerlevel)));
        }
        if (event.getSkill() == Skill.SLAYER){
            int playerlevel = Skill.SLAYER.getBaseLevel();
            Platform.runLater(() -> controller.slayerlvl.setText(Integer.toString(playerlevel)));
        }
    }

        @Override
    public TreeTask createRootTask() {
        Execution.delayUntil(() -> !monsters.isEmpty());
        return new IsContinue();
    }
}
