/**
 * Created by Cedric on 20.09.2015.
 */
public class Area {

    private final int width;
    private final int height;
    private final Position nest;
    private final Position[] foods;
    private Field[][] fields;

    public Area(int width, int height, Position nest, Position[] foods, int foodProField) {
        this.width = width;
        this.height = height;
        this.nest = nest;
        this.foods = foods;

        // Creating empty fields
        fields = new Field[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                fields[i][j] = new Field(0, 0);
                fields[i][j].step();
            }
        }

        // Adding food to fields
        for (int i = 0; i < foods.length; i++) {
            fields[foods[i].getX()][foods[i].getY()].setFoodCount(foodProField);
        }

        // creating nest
        getField(nest).setNest(true);
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
        int x = pos.getX();
        int y = pos.getY();

        if (x > width) {
            x = width - 1;
        }
        else if (x < 0) {
            x = 0;
        }
        if (y > height) {
            y = height - 1;
        }
        else if (y < 0) {
            y = 0;
        }


        return fields[x][y];
    }

    /**
     * Request next frame on all fields
     */
    public void step() {
        // step Fields
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                fields[i][j].step();
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
