import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cedric on 23.09.2015.
 *
 * Top Level Class for this Program
 */
public class AntSimApp extends Application implements ActionListener {

    // JavaFX Stuff
    Scene mainScene;
    Canvas canvas;
    Timer newFrame;

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
        area = new Area(500, 500, new Position(500/2, 500/2), foodSpots);
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

        newFrame = new Timer(1000, this);
        newFrame.start();
        // create renderer
        renderer = new Renderer(ants, area, canvas);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFrame) {
            step();
        }
    }
}
