package Fabreze.bots.Fabreze_WebBuilderTool;

import Fabreze.bots.Fabreze_WebBuilderTool.Tasks.GetCoordinates;
import Fabreze.bots.Fabreze_WebBuilderTool.UI.WebToolUIController;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.WebVertex;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.TaskBot;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.*;


public class WebToolMain extends TaskBot implements EmbeddableUI {

    private int n = 1;
    private String filename = "Custom_WebPath" + n;

    private File file;

    public SerializableWeb web = new SerializableWeb();
    public ObservableList<WebVertex> webVertexCollection = FXCollections.observableArrayList();




    @Override
    public void onStart(String... strings) {
        setEmbeddableUI(this);
        Execution.delayUntil(() -> Players.getLocal() != null);
        CreateFile();
        add(new GetCoordinates());
    }

    private final FXMLLoader loader = new FXMLLoader();
    private WebToolUIController controller = new WebToolUIController(this);

    private ObjectProperty<? extends Node> botInterfaceProperty;

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            try {
                loader.setController(controller);
                final InputStream input = Resources.getAsStream("Fabreze/bots/Fabreze_WebBuilderTool/UI/WebToolUI.fxml");
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

    public void onStop(){

        web.addVertices(webVertexCollection);

        try {
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            web.writeExternal(oos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void CreateFile() {
        file = new File(Environment.getStorageDirectory().toString() + File.separator + filename + ".web");
        for (int n = 1; file.exists(); n++){
            String filename = "Custom_WebPath" + n;
            file = new File(Environment.getStorageDirectory().toString() + File.separator + filename + ".web");
        }
        ClientUI.showAlert("Created file in location: " + file);
    }
}
