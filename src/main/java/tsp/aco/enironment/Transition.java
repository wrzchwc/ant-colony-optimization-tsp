package tsp.aco.enironment;

public class Transition {
    private final int finish;
    private final double pheromoneAmount;

    public Transition(int finish, double pheromoneAmount) {
        this.finish = finish;
        this.pheromoneAmount = pheromoneAmount;
    }

    public int getFinish() {
        return finish;
    }

    public double getPheromoneAmount() {
        return pheromoneAmount;
    }
}
