/**
 * Created by Cedric on 20.09.2015.
 */
public class Field {

    enum FieldType {
        NEST, FOOD, NORMAL
    }

    FieldType type;

    public void step() {

    }

    public int getPheromoneCount() {
        return 0;
    }

    public int getFoodCount() {
        return 0;
    }

    public FieldType getType() {
        return FieldType.NORMAL;
    }

    public void putPheromones() {

    }

    public void getFood() {

        // wenn leer, FieldType zu normal
    }
}
