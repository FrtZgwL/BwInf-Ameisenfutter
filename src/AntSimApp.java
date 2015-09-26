import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        Position[] foodSpots = {
                new Position(100, 100), new Position(200, 100),
                new Position(100, 300), new Position(200, 300)
        };
        area = new Area(500, 500, new Position(500/2, 500/2), foodSpots, 50);
        // create ants
        ants = new Ant[100];
        for (int i = 0; i < 100; i++) {
            Navigator navigator = new Navigator(area, new Position(0, 0));
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

        // set up step loop
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> step()
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Main Method of the Simulation. Gets called every frame.
     */
    private void step() {
        // step Area
        area.step();
        // step Ants
        for (Ant ant : ants) {
            ant.step();
        }
        // render new step
        renderer.renderFrame();
    }
}

