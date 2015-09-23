/**
 * Created by Cedric on 20.09.2015.
 */
public class Ant {

    Navigator nav;

    Field currField;

    public Ant(Navigator nav) {

    }

    public void step() {
        // wenn currField Futter hat, Futter aufnehmen
        // wenn currField Nest ist, und futter hat, futter ablegen
        // wenn currField normal, und futter hat, pheromone erhöhen; dann einen Schritt in Richtung Nest
        // wenn currField normal, und kein futter, einen Schritt weg von nest, hin zu stärksten Pheromonen
    }

    public Position getPosition() {
        return null;
    }

    public boolean isCarryingFood() {
        return false;
    }
}
