import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    HBox rootLayout;
    Canvas canvas;
        // UI Controls
    Slider antSld;
    Slider nestXSld;
    Slider nestYSld;
    Slider pheroSld;

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
        rootLayout = new HBox();
        mainScene = new Scene(rootLayout);

        canvas = new Canvas(600, 600);
        rootLayout.getChildren().add(canvas);

        setUpUIControls();

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

    private void setUpUIControls() {
        VBox sliderBox = new VBox();

        // --- Ant Slider --- //
        HBox antBox = new HBox();

        antSld = new Slider(0, 1000, 100);
        antSld.setShowTickLabels(true);
        antSld.setShowTickMarks(true);
        antSld.setMajorTickUnit(200);

        antBox.getChildren().addAll(new Text("Anzahl der Ameisen:"), antSld);


        // --- NestX Slider --- //
        HBox nestXBox = new HBox();

        nestXSld = new Slider(0, 500, (500-0)/2);
        nestXSld.setShowTickLabels(true);
        nestXSld.setShowTickMarks(true);
        nestXSld.setMajorTickUnit(100);

        nestXBox.getChildren().addAll(new Text("X Position des Nests:"), nestXSld);


        // --- NestY Slider --- //
        HBox nestYBox = new HBox();

        nestYSld = new Slider(0, 500, (500-0)/2);
        nestYSld.setShowTickLabels(true);
        nestYSld.setShowTickMarks(true);
        nestYSld.setMajorTickUnit(100);

        nestYBox.getChildren().addAll(new Text("Y Position des Nests:"), nestYSld);


        // --- Pheromone Slider --- //
        HBox pheroBox = new HBox();

        pheroSld = new Slider(0, 10000, 1000);
        pheroSld.setShowTickLabels(true);
        pheroSld.setShowTickMarks(true);
        pheroSld.setMajorTickUnit(2000);

        pheroBox.getChildren().addAll(new Text("Pheromone bleiben:"), pheroSld);

        Button anwendBtn = new Button("Einstellungen anwenden");

        sliderBox.getChildren().addAll(antBox, nestXBox, nestYBox, pheroBox, anwendBtn);
        rootLayout.getChildren().addAll(sliderBox);
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

