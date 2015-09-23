import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Cedric on 23.09.2015.
 *
 * Top Level Class for this Program
 */
public class AntSimApp extends Application {

    // JavaFX Stuff
    Scene mainScene;
    Canvas canvas;

    // AntSim Stuff
    Renderer renderer;
    Area area;
    Ant[] ants;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create area
        area = new Area(500, 500, null, null);
        // create ants
        ants = new Ant[100];
        for (int i = 0; i < 100; i++) {
            Navigator navigator = new Navigator(area, null);
            ants[i] = new Ant(navigator);
        }
        // Create JavaFX Scene
        StackPane root = new StackPane();
        mainScene = new Scene(root);

        canvas = new Canvas(600, 600);
        root.getChildren().add(canvas);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Ameisenfutter");
        primaryStage.show();
        // create renderer
        renderer = new Renderer(ants, area, canvas);
    }

    private void step() {
        // step Area
        // step Ants
        // render new step
            // give canvas to renderer and call him
    }
}
