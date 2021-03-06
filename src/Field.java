/**
 * Created by Cedric on 20.09.2015.
 */
public class Field {

    private int pheroDecayTime = 1000;

    boolean nest;
    int foodCount;
    int pheromoneCount;

    public Field(int initialFoodCount, int initialPheromoneCount) {
        foodCount = initialFoodCount;
        pheromoneCount = initialPheromoneCount*pheroDecayTime;
    }

    /**
     * Request new frame of this field
     */
    public void step() {
        if (pheromoneCount > 0)
            pheromoneCount--;
    }

    public void setPheroDecayTime(int pheroDecayTime) {
        this.pheroDecayTime = pheroDecayTime;
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
        return pheromoneCount/pheroDecayTime;
    }

    public void setPheromoneCount(int pheromoneCount) {
        this.pheromoneCount = pheromoneCount*pheroDecayTime;
    }

    /**
     * Puts one new unit of Pheromones on this field
     */
    public void putPheromones() {
        pheromoneCount += pheroDecayTime;
    }

    public boolean isNest() {
        return nest;
    }

    public void setNest(boolean nest) {
        this.nest = nest;
    }
}
