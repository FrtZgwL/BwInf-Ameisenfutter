/**
 * Created by Cedric on 20.09.2015.
 */
public class Field {

    int foodCount;
    int pheromoneCount;
    private boolean nest;

    public Field(int initialFoodCount, int initialPheromoneCount) {
        foodCount = initialFoodCount;
        pheromoneCount = initialPheromoneCount;
    }

    /**
     * Request new frame of this field
     */
    public void step() {
    }

    /**
     * Returns the amount of food on this field
     * @return Food on this field
     */
    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    /**
     * Takes away one unit of food from this field
     */
    public void takeFood() {
        foodCount--;
    }

    public void putFood() {
        foodCount++;
    }

    /**
     * Returns the amount of pheromones on this field
     * @return Pheromones on this field
     */
    public int getPheromoneCount() {
        return pheromoneCount;
    }

    public void setPheromoneCount(int pheromoneCount) {
        this.pheromoneCount = pheromoneCount;
    }

    /**
     * Puts one new unit of Pheromones on this field
     */
    public void putPheromones() {
        pheromoneCount++;
    }

    public boolean isNest() {
        return nest;
    }
}
