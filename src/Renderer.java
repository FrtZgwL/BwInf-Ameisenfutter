import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Cedric on 20.09.2015.
 *
 * Takes Ants, Fields, and a JavaFX Canvas.
 * Renders everythings on the screen
 */
public class Renderer {

    private final Ant[] ants;
    private final Area area;
    private final Canvas canvas;

    public Renderer(Ant[] ants, Area area, Canvas canvas) {
        this.ants = ants;
        this.area = area;
        this.canvas = canvas;
    }

    /**
     * Renders new frame on canvas.
     */
    public void renderFrame() {
        // Clear Screen
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // draw ants
        gc.setStroke(Color.BLACK);
        for (Ant ant : ants) {
            Position pos = ant.getPosition();
            gc.strokeLine(pos.getX(), pos.getY(), pos.getX(), pos.getY() + 1);
        }
        // draw pheromones & food
        int fieldWidth = area.getWidth();
        int fieldHeight = area.getHeight();
        for (int i = 0; i < fieldWidth; i++) {
            for (int j = 0; j < fieldHeight; j++) {
                Field curr = area.getField(new Position(i, j));
                if (curr.getPheromoneCount() > 0) {
                    gc.setStroke(Color.MEDIUMPURPLE);
                    gc.strokeLine(i, j, i, j + 1);
                }
                if (curr.getFoodCount() > 0) {
                    gc.setStroke(Color.BROWN);
                    gc.strokeLine(i, j, i, j + 1);
                }
            }
        }
    }

}
