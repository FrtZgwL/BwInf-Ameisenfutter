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
        return null;
    }

    /**
     * Ausgehend von momentaner Position aus in Richtung Pheromone
     * @return
     */
    public Field search() {
        // create variables for adjacent fields
        // 0: north, 1: east, 2: south, 3: west
        Field[] adjFields = new Field[4];
        adjFields[0] = area.getField(new Position(pos.getX(), pos.getY()-1));
        adjFields[1] = area.getField(new Position(pos.getX()+1, pos.getY()));
        adjFields[2] = area.getField(new Position(pos.getX(), pos.getY()+1));
        adjFields[3] = area.getField(new Position(pos.getX()-1, pos.getY()));
        // compare variables for highest count
        int highest = 0;
        for (int i = 0; i < adjFields.length; i++) {
            if (adjFields[i].getPheromoneCount()
                    > adjFields[highest].getPheromoneCount()) {
                highest = i;
            }
        }
        // if highest pheromone count is 0, go random
        if (adjFields[highest].getPheromoneCount() == 0) {
            highest = rGen.nextInt(4);
        }
        // if going in forbiddendir, go somewhere else
        if (highest == forbiddenDir) {
            highest = (highest + 1) % 4;
        }
        // update position
        switch (highest) {
            case 0:
                pos = new Position(pos.getX(), pos.getY()-1);
                forbiddenDir = 2;
                break;
            case 1:
                pos = new Position(pos.getX()+1, pos.getY());
                forbiddenDir = 3;
                break;
            case 2:
                pos = new Position(pos.getX(), pos.getY()+1);
                forbiddenDir = 0;
                break;
            case 3:
                pos = new Position(pos.getX()-1, pos.getY());
                forbiddenDir = 1;
                break;
        }
        // return field with highest pheromone count
        return adjFields[highest];
    }

    public Position getPosition() {
        return pos;
    }

}
