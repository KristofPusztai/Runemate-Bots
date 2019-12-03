package Fabreze.bots.Fabreze_Cannoner.GUI;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.JSON;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class CannonerController implements Initializable {

    private CannonMain bot;

    public CannonerController(CannonMain bot){
        this.bot = bot;
    }

    @FXML
    public RadioButton foodcheck;
    @FXML
    public RadioButton cballcheck;

    @FXML
    public Label clock;

    @FXML
    public TextField monster;
    @FXML
    public TextArea monsterlist;
    @FXML
    public Button addmonster;
    @FXML
    public Button removemonster;

    @FXML
    public CheckBox lootcheck;
    @FXML
    public CheckBox alccheck;
    @FXML
    public TextField loot;
    @FXML
    public TextArea lootlist;
    @FXML
    public Button addloot;
    @FXML
    public CheckBox telegrab;
    @FXML
    public Slider lootradius;
    @FXML
    public Button removeloot;
    @FXML
    public RadioButton lootregex;

    @FXML
    public CheckBox safespot;
    @FXML
    public Button setlocation;

    @FXML
    public Button safespotloc;

    @FXML
    public Slider health;

    @FXML
    public Button setcannon;
    @FXML
    public Label cannonammo;

    @FXML
    public CheckBox burybones;

    @FXML
    public CheckBox specattack;

    @FXML
    public ProgressBar strengthbar;
    @FXML
    public ProgressBar attackbar;
    @FXML
    public ProgressBar defencebar;
    @FXML
    public ProgressBar rangebar;
    @FXML
    public ProgressBar magicbar;
    @FXML
    public ProgressBar hpbar;
    @FXML
    public ProgressBar slayerbar;

    @FXML
    public Label strengthpercent;
    @FXML
    public Label attackpercent;
    @FXML
    public Label defencepercent;
    @FXML
    public Label rangepercent;
    @FXML
    public Label magicpercent;
    @FXML
    public Label hppercent;
    @FXML
    public Label slayerpercent;

    @FXML
    public Label strengthhr;
    @FXML
    public Label attackhr;
    @FXML
    public Label defencehr;
    @FXML
    public Label rangehr;
    @FXML
    public Label magichr;
    @FXML
    public Label hphr;
    @FXML
    public Label slayerhr;

    @FXML
    public Label strengthlvl;
    @FXML
    public Label attacklvl;
    @FXML
    public Label defencelvl;
    @FXML
    public Label rangelvl;
    @FXML
    public Label magiclvl;
    @FXML
    public Label hplvl;
    @FXML
    public Label slayerlvl;

    public int strengthxp = Skill.STRENGTH.getExperienceAsPercent();
    public int attackxp = Skill.ATTACK.getExperienceAsPercent();
    public int defencexp = Skill.DEFENCE.getExperienceAsPercent();
    public int rangexp = Skill.RANGED.getExperienceAsPercent();
    public int magicxp = Skill.MAGIC.getExperienceAsPercent();
    public int hpxp = Skill.CONSTITUTION.getExperienceAsPercent();
    public int slayerxp = Skill.SLAYER.getExperienceAsPercent();

    public int strengthxpgain = 0;
    public int attackxpgain = 0;
    public int defencexpgain = 0;
    public int rangexpgain = 0;
    public int magicxpgain = 0;
    public int hpxpgain = 0;
    public int slayerxpgain = 0;

    @FXML
    public CheckBox strengthpot;
    @FXML
    public CheckBox attackpot;
    @FXML
    public CheckBox defencepot;
    @FXML
    public CheckBox rangepot;
    @FXML
    public CheckBox magicpot;
    @FXML
    public CheckBox prayerpot;
    @FXML
    public Slider prayerthreshold;
    @FXML
    public RadioButton dropvials;

    @FXML
    public CheckBox fullinvbank;
    @FXML
    public CheckBox outoffoodbank;
    @FXML
    public CheckBox outofcballbank;
    @FXML
    public TextField withdrawitem;
    @FXML
    public Button addbank;
    @FXML
    public Button clearbank;
    @FXML
    public TextField amount;
    @FXML
    public RadioButton regex;
    @FXML
    public Tab bnktb;
    @FXML
    public CheckBox outofpot;
    @FXML
    public TextArea banklist;

    @FXML
    public CheckBox eatfood;

    @FXML
    public Button deletesave;
    @FXML
    public Button loadsave;
    @FXML
    public Button save;
    @FXML
    public ComboBox saves;
    @FXML
    public TextField savename;


    private StopWatch stopwatch = new StopWatch();

    private Map<String, Object> saveprofile = new HashMap<String, Object>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stopwatch.start();
        new LoopingThread(() -> Platform.runLater(() -> clock.setText(stopwatch.getRuntimeAsString())), 1000).start(); //Starts runtime timer
        //bank checks
        fullinvbank.setOnAction(e -> {
            bot.bankinventory = fullinvbank.isSelected();
            if (!outofcballbank.isSelected() && !outoffoodbank.isSelected() && !outofpot.isSelected()){
                foodcheck.setDisable(fullinvbank.isSelected());
                cballcheck.setDisable(fullinvbank.isSelected());
            }
        });
        outoffoodbank.setOnAction(e -> {
            bot.bankfood = outoffoodbank.isSelected();
            if (!fullinvbank.isSelected() && !outofcballbank.isSelected() && !outofpot.isSelected()){

                foodcheck.setDisable(outoffoodbank.isSelected());
                cballcheck.setDisable(outoffoodbank.isSelected());
            }
        });
        outofcballbank.setOnAction(e -> {
            bot.bankcball = outofcballbank.isSelected();
            if (!fullinvbank.isSelected() && !outoffoodbank.isSelected() && !outofpot.isSelected()){
                foodcheck.setDisable(outofcballbank.isSelected());
                cballcheck.setDisable(outofcballbank.isSelected());
            }
        });
        eatfood.setOnAction(e -> {
            bot.eatfoodtoloot = eatfood.isSelected();
        });
        outofpot.setOnAction(e -> {
            bot.bankpot = outofpot.isSelected();
            if (!fullinvbank.isSelected() && !outoffoodbank.isSelected() && !outofcballbank.isSelected()){
                foodcheck.setDisable(outofpot.isSelected());
                cballcheck.setDisable(outofpot.isSelected());
            }
        });

        withdrawitem.textProperty().addListener((observable, oldValue, newValue) ->{
            if (!amount.getText().isEmpty() && !withdrawitem.getText().isEmpty()){
                addbank.setDisable(false);
            }
            else{
                addbank.setDisable(true);
            }
        });
        amount.textProperty().addListener((observable, oldValue, newValue) -> handleInput(newValue));
        addbank.setOnAction(e -> {
            if (regex.isSelected()){
                banklist.appendText("*regex*" + withdrawitem.getText() + " - " + amount.getText());
                banklist.appendText("\n");
                bot.regexbankitems.put(withdrawitem.getText(), Integer.parseInt(amount.getText()));
            }
            else{
                banklist.appendText(withdrawitem.getText() + " - " + amount.getText());
                bot.bankitems.put(withdrawitem.getText(), Integer.parseInt(amount.getText()));
                banklist.appendText("\n");
            }
        });
        clearbank.setOnAction(e -> {
            banklist.clear();
            bot.regexbankitems.clear();
            bot.bankitems.clear();
        });
        //log out checks
        foodcheck.setOnAction(e -> {
            bot.foodcheck = foodcheck.isSelected();
            if (!cballcheck.isSelected()){
                bnktb.setDisable(foodcheck.isSelected());
            }
        });
        cballcheck.setOnAction(e -> {
            bot.cannonballcheck = cballcheck.isSelected();
            if (!foodcheck.isSelected()){
                bnktb.setDisable(cballcheck.isSelected());
            }
        });
        addmonster.setOnAction(e -> {
            monsterlist.appendText(monster.getText());
            monsterlist.appendText("\n");
            bot.monsters.add(monster.getText());
        });
        lootcheck.setOnAction(e ->{
            boolean bool = !lootcheck.isSelected();
            System.out.println(bool);
            addloot.setDisable(bool);
            telegrab.setDisable(lootcheck.isSelected());
            alccheck.setDisable(lootcheck.isSelected());
        });
        alccheck.setOnAction(e ->{
            lootcheck.setDisable(alccheck.isSelected());
            telegrab.setDisable(alccheck.isSelected());
            addloot.setDisable(!alccheck.isSelected());
        });
        addloot.setOnAction(e -> {
            if (alccheck.isSelected()){
                if (lootregex.isSelected()){
                    bot.regexalclist.add(loot.getText());
                    lootlist.appendText("*regex*" + loot.getText() + " - Alchemy");
                }
                else {
                    bot.alclist.add(loot.getText());
                    lootlist.appendText(loot.getText() + " - Alchemy");
                }
            }
            else if (telegrab.isSelected()){
                if (lootregex.isSelected()){
                    bot.regextelegrablist.add(loot.getText());
                    lootlist.appendText("*regex*" + loot.getText() + " - Telegrab");
                }
                else {
                    lootlist.appendText(loot.getText() + " - Telegrab");
                    bot.telegrablist.add(loot.getText());
                }
            }
            else {
                if (lootregex.isSelected()){
                    bot.regexloot.add(loot.getText());
                    lootlist.appendText("*regex*" + loot.getText() + " - Loot");
                }
                else {
                    lootlist.appendText(loot.getText() + " - Loot");
                    bot.lootlist.add(loot.getText());
                }
            }
            lootlist.appendText("\n");
        });
        health.setOnMouseReleased(e -> bot.healthval = (int) Math.round(health.getValue()));
        health.setValue(50);
        lootradius.setOnMouseReleased(e -> bot.radius = (int) Math.round(lootradius.getValue()));
        lootradius.setValue(25);
        telegrab.setOnAction(e -> {
            lootcheck.setDisable(telegrab.isSelected());
            alccheck.setDisable(telegrab.isSelected());
            addloot.setDisable(!telegrab.isSelected());
        });
        safespot.setOnAction(e -> {
            bot.safezone = safespot.isSelected();
            safespotloc.setDisable(!safespot.isSelected());
        });
        setlocation.setOnAction(e -> {
            bot.getPlatform().invokeLater(() -> bot.setLocation());
            lootradius.setDisable(false);
        });
        safespotloc.setOnAction(e ->{
            bot.getPlatform().invokeLater(() -> bot.setSafespotloc());
        });
        removeloot.setOnAction(e -> {
            lootlist.clear();
            bot.lootlist.clear();
        });
        removemonster.setOnAction(e -> {
            monsterlist.clear();
            bot.monsters.clear();
        });
        setcannon.setOnAction(e -> {
            bot.getPlatform().invokeLater(() -> bot.setCannon());
        });
        burybones.setOnAction(e -> bot.burybones = burybones.isSelected());
        specattack.setOnAction(e -> bot.specattack = specattack.isSelected());

        strengthpot.setOnAction(e -> {
                if (bot.skilllist.contains(Skill.STRENGTH)){
                    bot.skilllist.remove(Skill.STRENGTH);
                }
                else {
                    bot.skilllist.add(Skill.STRENGTH);
                }
        });
        attackpot.setOnAction(e -> {
            if (bot.skilllist.contains(Skill.ATTACK)){
                bot.skilllist.remove(Skill.ATTACK);
            }
            else {
                bot.skilllist.add(Skill.ATTACK);
            }
        });
        defencepot.setOnAction(e -> {
            if (bot.skilllist.contains(Skill.DEFENCE)){
                bot.skilllist.remove(Skill.DEFENCE);
            }
            else {
                bot.skilllist.add(Skill.DEFENCE);
            }
        });
        rangepot.setOnAction(e -> {
            if (bot.skilllist.contains(Skill.RANGED)){
                bot.skilllist.remove(Skill.RANGED);
            }
            else {
                bot.skilllist.add(Skill.RANGED);
            }
        });
        magicpot.setOnAction(e -> {
            if (bot.skilllist.contains(Skill.MAGIC)){
                bot.skilllist.remove(Skill.MAGIC);
            }
            else {
                bot.skilllist.add(Skill.MAGIC);
            }
        });
        prayerpot.setOnAction(e -> {
                bot.prayerpot = prayerpot.isSelected();
        });
        prayerthreshold.setValue(50);
        prayerthreshold.setOnMouseReleased(e -> bot.prayerval = (int) Math.round(prayerthreshold.getValue()));
        dropvials.setOnAction(e -> bot.dropvials = dropvials.isSelected());

        Execution.delayUntil(() -> Skill.STRENGTH.getCurrentLevel() != -1);
        //sets xp bar progress
        strengthbar.setProgress(strengthxp/100.0);
        strengthbar.setStyle("-fx-accent: #7f2d1c");
        attackbar.setProgress(attackxp/100.0);
        attackbar.setStyle("-fx-accent: #7C271B");
        defencebar.setProgress(defencexp/100.0);
        defencebar.setStyle("-fx-accent: #365164");
        rangebar.setProgress(rangexp/100.0);
        rangebar.setStyle("-fx-accent: #1a6720");
        magicbar.setProgress(magicxp/100.0);
        magicbar.setStyle("-fx-accent: #001140");
        hpbar.setProgress(hpxp/100.0);
        hpbar.setStyle("-fx-accent: #bd1a1a");
        slayerbar.setProgress(slayerxp/100.0);
        slayerbar.setStyle("-fx-accent: #390000");
        //sets xp bar lvl label
        strengthlvl.setText(Integer.toString(Skill.STRENGTH.getBaseLevel()));
        attacklvl.setText(Integer.toString(Skill.ATTACK.getBaseLevel()));
        defencelvl.setText(Integer.toString(Skill.DEFENCE.getBaseLevel()));
        rangelvl.setText(Integer.toString(Skill.RANGED.getBaseLevel()));
        magiclvl.setText(Integer.toString(Skill.MAGIC.getBaseLevel()));
        hplvl.setText(Integer.toString(Skill.CONSTITUTION.getBaseLevel()));
        slayerlvl.setText(Integer.toString(Skill.SLAYER.getBaseLevel()));
        //sets xpbar percentage
        strengthpercent.setText(strengthxp + "%");
        attackpercent.setText(attackxp + "%");
        defencepercent.setText(defencexp + "%");
        rangepercent.setText(rangexp + "%");
        magicpercent.setText(magicxp + "%");
        hppercent.setText(hpxp + "%");
        slayerpercent.setText(slayerxp + "%");
        //starts xp/hr tracker
        new LoopingThread(() -> Platform.runLater(() -> strengthhr.setText(Math.round(strengthxpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> attackhr.setText(Math.round(attackxpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> defencehr.setText(Math.round(defencexpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> rangehr.setText(Math.round(rangexpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> magichr.setText(Math.round(magicxpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> hphr.setText(Math.round(hpxpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> slayerhr.setText(Math.round(slayerxpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        //save and load
        savename.textProperty().addListener((observable, oldValue, newValue) ->{
            if (!savename.getText().isEmpty()){
                save.setDisable(false);
            }
            else{
                save.setDisable(true);
            }
        });
        File folder = Environment.getStorageDirectory();
        List<String> listOfSaves = removeSuffix(Arrays.asList(folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.endsWith(".json");
            }
        })));
        save.setOnAction(e -> {
            saveprofile.put("foodcheck", bot.foodcheck);
            saveprofile.put("cballcheck", bot.cannonballcheck);
            saveprofile.put("cannonlocation", bot.cannon);
            saveprofile.put("safespot", bot.safezone);
            saveprofile.put("safespotcoord", bot.safespotloc);
            saveprofile.put("monsters", bot.monsters);
            saveprofile.put("loot", bot.lootlist);
            saveprofile.put("regexloot", bot.regexloot);
            saveprofile.put("telegrab", bot.telegrablist);
            saveprofile.put("regextelegrab", bot.regextelegrablist);
            saveprofile.put("alchemy", bot.alclist);
            saveprofile.put("regexalchemy", bot.regexalclist);
            saveprofile.put("burybones", bot.burybones);
            saveprofile.put("specattack", bot.specattack);
            saveprofile.put("eatfoodtoloot", bot.eatfoodtoloot);
            saveprofile.put("fightradius", bot.radius);
            saveprofile.put("fightlocation", bot.location);
            saveprofile.put("healththreshold", bot.healthval);
            saveprofile.put("meleepotions", bot.skilllist);
            saveprofile.put("prayerpot", bot.prayerpot);
            saveprofile.put("prayerpotthreshhold", bot.prayerval);
            saveprofile.put("foodbank", bot.bankfood);
            saveprofile.put("cballbank", bot.bankcball);
            saveprofile.put("inventoryfullbank", bot.bankinventory);
            saveprofile.put("outofpotions", bot.bankpot);
            saveprofile.put("withdrawbank", bot.bankitems);
            saveprofile.put("regexwithdrawbank", bot.regexbankitems);
            saveprofile.put("dropvial", bot.dropvials);

            String json = JSON.mapToJson(saveprofile);

            File file = new File( folder.toString() + File.separator + savename.getText() + ".json");
            try {
                FileOutputStream fis = new FileOutputStream(file);
                PrintWriter out = new PrintWriter(fis);
                out.write(json);
                out.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                ClientUI.showAlert("Failed to Create Save");
            }
        });
        saves.setItems(FXCollections.observableList(listOfSaves));
        saves.setOnAction(e -> {
            loadsave.setDisable(false);
            deletesave.setDisable(false);
        });
        loadsave.setOnAction(e -> {
            File file = new File(folder.toString() + File.separator + saves.getValue() + ".json");
            try {
                String json = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                saveprofile.clear();
                saveprofile = JSON.jsonToMap(json);
                //load in all object data to references
                if (!saveprofile.get("cannonlocation").equals("null")){
                    bot.cannon = deserializeCoordinate((String)saveprofile.get("cannonlocation"));
                }
                bot.safezone = (boolean) saveprofile.get("safespot");
                if (!saveprofile.get("safespotcoord").equals("null")){
                    bot.safespotloc = deserializeCoordinate((String)saveprofile.get("safespotcoord"));
                }
                if (!saveprofile.get("fightlocation").equals("null")){
                    bot.location = deserializeCoordinate((String)saveprofile.get("fightlocation"));
                    lootradius.setDisable(false);
                }
                bot.cannonballcheck = (boolean)saveprofile.get("cballcheck");
                bot.foodcheck = (boolean) saveprofile.get("foodcheck");
                bot.monsters = (List<String>) toStringArray((Object[])saveprofile.get("monsters"));
                bot.lootlist = (List<String>) toStringArray((Object[]) saveprofile.get("loot"));
                bot.regexloot = (List<String>) toStringArray((Object[]) saveprofile.get("regexloot"));
                bot.telegrablist = (List<String>) toStringArray((Object[]) saveprofile.get("telegrab"));
                bot.regextelegrablist = (List<String>) toStringArray((Object[]) saveprofile.get("regextelegrablist"));
                bot.alclist = (List<String>) toStringArray((Object[]) saveprofile.get("alchemy"));
                bot.regexalclist = (List<String>) toStringArray((Object[]) saveprofile.get("regexalchemy"));
                bot.burybones = (boolean) saveprofile.get("burybones");
                bot.specattack = (boolean) saveprofile.get("specattack");
                bot.eatfoodtoloot = (boolean) saveprofile.get("eatfoodtoloot");
                bot.radius = (int) saveprofile.get("fightradius");
                bot.healthval = (int) saveprofile.get("healththreshold");
                bot.skilllist = (List<Skill>) toSkillArray(toStringArray((Object[]) saveprofile.get("meleepotions")));
                bot.prayerpot = (boolean) saveprofile.get("prayerpot");
                bot.prayerval = (int) saveprofile.get("prayerpotthreshhold");
                bot.bankfood = (boolean) saveprofile.get("foodbank");
                bot.bankcball = (boolean) saveprofile.get("cballbank");
                bot.bankinventory = (boolean) saveprofile.get("inventoryfullbank");
                bot.bankpot = (boolean) saveprofile.get("outofpotions");
                bot.bankitems = (HashMap<String, Integer>) saveprofile.get("withdrawbank");
                bot.regexbankitems = (HashMap<String, Integer>) saveprofile.get("regexwithdrawbank");
                bot.dropvials = (boolean) saveprofile.get("dropvial");
                //Removes artifacts from JSON deserialization process
                bot.regexbankitems.remove("");
                bot.bankitems.remove("");

                //load in object data visually
                for (String i: bot.monsters){
                    monsterlist.appendText(i);
                    monsterlist.appendText("\n");
                }
                for (String i: bot.lootlist){
                    lootlist.appendText(i + " - Loot");
                    lootlist.appendText("\n");
                }
                for (String i: bot.regexloot){
                    lootlist.appendText("*regex*" + i + " - Loot");
                    lootlist.appendText("\n");
                }
                for (String i: bot.telegrablist){
                    lootlist.appendText(i + " - Telegrab");
                    lootlist.appendText("\n");
                }
                for (String i: bot.regextelegrablist){
                    lootlist.appendText("*regex*" + i + " - Telegrab");
                    lootlist.appendText("\n");
                }
                for (String i: bot.alclist){
                    lootlist.appendText(i + " - Alchemy");
                    lootlist.appendText("\n");
                }
                for (String i: bot.regexalclist){
                    lootlist.appendText("*regex*" + i + " - Alchemy");
                    lootlist.appendText("\n");
                }
                for (String i: bot.bankitems.keySet()){
                    if (!i.equals("")){
                        banklist.appendText(i + " - " + bot.bankitems.get(i) + "\n");
                    }
                }
                for (String i : bot.regexbankitems.keySet()){
                    if (!i.equals("")){
                        banklist.appendText("*regex*" + i + " - " + bot.regexbankitems.get(i) + "\n");
                    }
                }
                if (bot.cannonballcheck){
                    cballcheck.setSelected(true);
                    cballcheck.fire();
                }
                if (bot.foodcheck){
                    foodcheck.setSelected(true);
                    foodcheck.fire();
                }
                if (bot.safezone){
                safespot.setSelected(bot.safezone);
                safespotloc.setDisable(false);
                }
                burybones.setSelected(bot.burybones);
                eatfood.setSelected(bot.eatfoodtoloot);
                prayerpot.setSelected(bot.prayerpot);
                outoffoodbank.setSelected(bot.bankfood);
                outofpot.setSelected(bot.bankpot);
                outofcballbank.setSelected(bot.bankcball);
                fullinvbank.setSelected(bot.bankinventory);
                lootradius.setValue(bot.radius);
                health.setValue(bot.healthval);
                prayerthreshold.setValue(bot.prayerval);
                specattack.setSelected(bot.specattack);
                dropvials.setSelected(bot.dropvials);
            } catch (IOException ex) {
                ex.printStackTrace();
                ClientUI.showAlert("Failed to Load Save");
            }
        });
        deletesave.setOnAction(e -> {
            File save = new File(folder.toString() + File.separator + saves.getValue() + ".json");
            if (save.delete()){
                ClientUI.showAlert("Save Profile Successfully Deleted");
            }
            else {
                ClientUI.showAlert("Failed to Delete Save Profile");
            }
        });
    }

    private void handleInput(String s) { //Textfield Handler

        Matcher matcher = Pattern.compile("[^\\d]").matcher(s);

        if (matcher.find()) {

            // Only allows numeral inputs

            Platform.runLater(() -> amount.setText(s.replaceAll("[^\\d]", "")));
        }
        if (!amount.getText().isEmpty() && !withdrawitem.getText().isEmpty()){
            addbank.setDisable(false);
        }
        else{
            addbank.setDisable(true);
        }
    }
    private List<Skill> toSkillArray (List<String> lst){
        List <Skill> list = new ArrayList<>();
        for (String i : lst){
            if (i.equals("Strength")){
                list.add(Skill.STRENGTH);
                strengthpot.setSelected(true);
            }
            else if (i.contains("Attack")){
                list.add(Skill.ATTACK);
                attackpot.setSelected(true);
            }
            else if (i.contains("Defence")){
                list.add(Skill.DEFENCE);
                defencepot.setSelected(true);
            }
            else if (i.contains("Ranged")){
                list.add(Skill.RANGED);
                rangepot.setSelected(true);
            }
            else if (i.contains("Magic")){
                list.add(Skill.MAGIC);
                magicpot.setSelected(true);
            }
        }
        return list;
    }
    private List<String> toStringArray(Object[] o){
        List <String> list = new ArrayList<>();
        for (Object x : o){
            if (!x.toString().equals("")){
                String string = x.toString();
                if (string.indexOf(' ') == 0){ //Gets rid of leading spaces
                    string = string.replaceFirst(" ", "");
                }
                list.add(string);
            }
        }
        return list;
    }
    private List<String> removeSuffix(List<String> list){
        List<String> newlist = new ArrayList<>();
        for (String i: list){
            int dotIndex=i.lastIndexOf('.');
            if(dotIndex>=0) { // to prevent exception if there is no dot
                i=i.substring(0,dotIndex);
                newlist.add(i);
            }
        }
        return newlist;
    }
    private Coordinate deserializeCoordinate(String string){
        String intcache = "";
        boolean xtokenizer = true;
        boolean ytokenizer = false;
        boolean ztokenizer = false;
        int x = 0;
        int y = 0;
        int z = 0;
        string = string.replace("Coordinate", "");
        for (int i = 0; i < string.length(); i ++ ){
            char c = string.charAt(i);
            if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) < 10){
                intcache += c;
            }
            else if (c == ','){
                if (xtokenizer){
                    xtokenizer = false;
                    ytokenizer = true;
                    x = Integer.parseInt(intcache);
                }
                else if (ytokenizer){
                    ytokenizer = false;
                    ztokenizer = true;
                    y = Integer.parseInt(intcache);
                }
                intcache = "";
            }
            else if (c == ')'){
                z = Integer.parseInt(intcache);
                break;
            }
        }
        return new Coordinate(x,y,z);
    }
}
