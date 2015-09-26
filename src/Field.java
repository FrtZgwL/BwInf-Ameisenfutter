/**
 * Created by Cedric on 20.09.2015.
 */
public class Field {

    int foodCount;
    int pheromoneCount;

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
     * Returns the amount of pheromones on this field
     * @return Pheromones on this field
     */
    public int getPheromoneCount() {
        return pheromoneCount;
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

    public void setPheromoneCount(int pheromoneCount) {
        this.pheromoneCount = pheromoneCount;
    }

    /**
     * Puts one new unit of Pheromones on this field
     */
    public void putPheromones() {
        pheromoneCount++;
    }

    /**
     * Takes away one unit of food from this field
     */
    public void getFood() {
        foodCount--;
    }
}
