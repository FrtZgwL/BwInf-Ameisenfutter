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

        fields = new Field[width][height];
        for (int i = 0; i < 0; i++) {
            for (int j = 0; j < 0; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    /**
     * Return the position of the nest
     * @return Position des Nestes
     */
    public Position getNestPos() {
        return nest;
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
        return fields[pos.getX()][pos.getY()];
    }

    /**
     * Request next frame on all fields
     */
    public void step() {
        // step Fields
        for (Field[] fieldarr : fields) {
            for (Field field : fieldarr) {
                field.step();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
