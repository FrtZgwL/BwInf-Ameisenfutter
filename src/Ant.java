/**
 * Created by Cedric on 20.09.2015.
 */
public class Ant {

    Navigator nav;

    Field currField;

    public Ant(Navigator nav) {

    }

    /**
     * Request new frame on ant
     */
    public void step() {
        // wenn currField Futter hat, Futter aufnehmen
        // wenn currField Nest ist, und futter hat, futter ablegen
        // wenn currField normal, und futter hat, pheromone erhöhen; dann einen Schritt in Richtung Nest
        // wenn currField normal, und kein futter, einen Schritt weg von nest, hin zu stärksten Pheromonen
    }

    /**
     * Get current Position of this Ant
     * @return Position of this ant
     */
    public Position getPosition() {
        return nav.getPosition();
    }

    /**
     * Is this ant carrying food?
     * @return true if this ant is carrying food
     */
    public boolean isCarryingFood() {
        return false;
    }
}
