/**
 * Created by Cedric on 20.09.2015.
 */
public class Ant {

    Navigator nav;
    Field currField;

    boolean hasFood;

    public Ant(Navigator nav, Field initField) {
        this.nav = nav;
        hasFood = false;
        currField = initField;
    }

    /**
     * Request new frame on ant
     */
    public void step() {
        // wenn currField Futter hat, Futter aufnehmen
        if (currField.getFoodCount() > 0){
            currField.takeFood();
            hasFood = true;
        }
        // wenn currField Nest ist, und ich Futter habe, futter ablegen
        if (currField.isNest() && hasFood) {
            hasFood = false;
        }
        // wenn currField normal, und ich Futter habe, pheromone erhöhen;
        // dann einen Schritt in Richtung Nest
        else if (hasFood) {
            currField.putPheromones();
            currField = nav.goHome();
        }
        // wenn currField normal, und kein futter,
        // einen Schritt weg von nest, hin zu stärksten Pheromonen
        else {
            currField = nav.search();
        }
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
        return hasFood;
    }
}
