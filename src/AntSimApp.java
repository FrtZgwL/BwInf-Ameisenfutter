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
        Position[] testFoods = {new Position(100, 100), new Position(230, 230), new Position(200, 200)};
        Position[] foodSpots = createFoodLine(new Position(200, 200), new Position(200, 450), 50);

        area = new Area(500, 500, new Position(500/2, 500/2), foodSpots, 50);
        // create ants
        ants = new Ant[100];
        for (int i = 0; i < 100; i++) {
            Navigator navigator = new Navigator(area,
                    new Position(area.getWidth()/2, area.getHeight()/2));
            ants[i] = new Ant(navigator, area.getField(navigator.getPosition()));
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
                Duration.millis(5),
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

    private Position[] createFoodCircles() {
        Position[] foodSpots = new Position[300];
        Position origin1 = new Position(200, 200);
        Position origin2 = new Position(300, 300);
        int radius = 200;
        //TODO nice food spots

        for (int i = 0, n = 100; i < n; i++) {
            foodSpots[i] = new Position((int) Math.sin(2.0*Math.PI/100.0 * i) * radius + origin1.getX(),
                    (int) Math.cos(2*Math.PI/100 * i) * radius + origin1.getY());

            System.out.println(foodSpots[i]);
        }
        for (int i = 100, n = 300; i < n; i++) {
            foodSpots[i] = new Position((int) Math.sin(2.0 * Math.PI / 200.0 * i) * radius +origin2.getX(),
                    (int) Math.cos(2*Math.PI/200 * i) * radius + origin2.getY());

            System.out.println(foodSpots[i]);
        }

        return foodSpots;
    }

    private Position[] createFoodLine(Position start, Position end, int amount) {
        Position[] foodPositions = new Position[amount];
        int distx = end.getX() - start.getX();
        int disty = end.getY() - start.getY();

        for (int i = 0; i < amount; i++) {
            foodPositions[i] = new Position(start.getX() + distx/amount * i,
                    start.getY() + disty/amount * i);
        }

        return foodPositions;
    }
}

