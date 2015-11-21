import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by Cedric on 20.09.2015.
 */
public class Navigator {

    private final Area area;
    Position pos;
    Random rGen;
    int forbiddenDir; /** the opposite of the last direction is always forbidden, so the ant doesn't walk back&forth */

    public Navigator(Area area, Position position) {
        this.area = area;
        this.pos = position;
        rGen = new Random();
        forbiddenDir = 0;
    }

    /**
     * Ausgehend von momentaner Position aus in Richtung Nest
     * @return
     */
    public Field goHome() {
        // check if moving on x / y axis
        int xDist = area.getNestPos().getX() - pos.getX();
        int yDist = area.getNestPos().getY() - pos.getY();

        // check if home already
        if (xDist == 0 && yDist == 0)
            return area.getField(pos);

        if (Math.abs(xDist) >  Math.abs(yDist)) {
            // move on x axis
            pos = new Position(pos.getX() + xDist / Math.abs(xDist), pos.getY());
        }
        else {
            // move on y axis
            pos = new Position(pos.getX(), pos.getY() + yDist / Math.abs(yDist));
        }

        return area.getField(pos);
    }

    /**
     * Ausgehend von momentaner Position aus in Richtung Pheromone
     * @return
     */
    public Field search() {
        // remember positions of adjacent fields
        Position[] adjFieldPos = new Position[4];
        adjFieldPos[0] = new Position(pos.getX(), pos.getY()-1);
        adjFieldPos[1] = new Position(pos.getX()+1, pos.getY());
        adjFieldPos[2] = new Position(pos.getX(), pos.getY()+1);
        adjFieldPos[3] = new Position(pos.getX()-1, pos.getY());
        // create variables for adjacent fields
        // 0: north, 1: east, 2: south, 3: west
        Field[] adjFields = new Field[4];
        adjFields[0] = area.getField(adjFieldPos[0]);
        adjFields[1] = area.getField(adjFieldPos[1]);
        adjFields[2] = area.getField(adjFieldPos[2]);
        adjFields[3] = area.getField(adjFieldPos[3]);

        // find allowed fields (all fields that would make the ant come closer
        // to the nest are forbidden, since it has to move away when it finds
        // pheromones)
        ArrayList<Field> allowedFields = new ArrayList<>();
        ArrayList<Position> allowedFieldPos = new ArrayList<>();
        // for each adjacent field
        for (int i = 0; i < adjFields.length; i++) {
            // check if close to nest
            Position nest = area.getNestPos();
            if (
                    // distanceX field->nest < distanceX ant->nest
                    nest.getX() - adjFieldPos[i].getX() < nest.getX() - pos.getX()
                    ||
                    // distanceY field->nest < distanceY ant->nest
                    nest.getY() - adjFieldPos[i].getX() < nest.getY() - pos.getY()
            ) {
                // field is closer to nest than ant -> not allowed
                continue;
            }
            // field was not not allowed -> into the list
            allowedFields.add(adjFields[i]);
            allowedFieldPos.add(adjFieldPos[i]);
        }

        // compare variables for highest count
        Field highestPField = null;
        if (allowedFields.size() > 0) {
            highestPField = allowedFields.get(0);
            for (Field field : allowedFields) {
                if (field.getPheromoneCount()
                        > highestPField.getPheromoneCount()) {
                    highestPField = field;
                }
            }
        }

        // if highest pheromone count is 0, go random
        if (highestPField == null || highestPField.getPheromoneCount() == 0) {
            int randomIndex = rGen.nextInt(4);
            highestPField = adjFields[randomIndex];
            pos = adjFieldPos[randomIndex];
            System.out.print("New pos: " + pos + "\n");
        }
        // update position
        else  {
            pos = allowedFieldPos.get(allowedFields.indexOf(highestPField));
        }

        // return field with highest pheromone count
        return highestPField;
    }

    public Position getPosition() {
        return pos;
    }

}
