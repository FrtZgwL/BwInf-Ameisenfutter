/**
 * Created by Cedric on 20.09.2015.
 */
public class Area {

    private final int width;
    private final int height;
    private final Position nest;
    private final Position[] foods;
    private Field[][] fields;

    public Area(int width, int height, Position nest, Position[] foods) {
        this.width = width;
        this.height = height;
        this.nest = nest;
        this.foods = foods;
    }

    /**
     * Return the position of the nest
     * @return Position des Nestes
     */
    public Position getNestPos() {
        return null;
    }

    public Position[] getFoodPos() {
        return foods;
    }

    /**
     * Get Field at given position
     * @param pos Position of searched field
     * @return searched field
     */
    public Field getField(Position pos) {
        return null;
    }

    /**
     * Request next frame on all fields
     */
    public void step() {
        // step Fields
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
