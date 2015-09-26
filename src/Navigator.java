/**
 * Created by Cedric on 20.09.2015.
 */
public class Navigator {

    private final Area area;
    Position position;

    public Navigator(Area area, Position position) {
        this.area = area;
        this.position = position;
    }

    /**
     * Ausgehend von momentaner Position aus in Richtung Nest
     * @return
     */
    public Field goHome() {
        return null;
    }

    /**
     * Ausgehend von momentaner Position aus in Richtung Pheromone
     * @return
     */
    public Field search() {
        return null;
    }

    public Position getPosition() {
        return position;
    }

}
