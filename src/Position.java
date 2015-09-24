/**
 * Created by Cedric on 20.09.2015.
 *
 * Position eines Objekts im Area. 2 Koordinaten x & y.
 */
public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
