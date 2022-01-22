package tsp.aco;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private final double[][] environment;
    private final int size;
    private List<int[]> transtions;
    private final double evaporationFactor;

    public Environment(int size, double evaporationFactor, double estimatedPathCost) {
        this.evaporationFactor = evaporationFactor;
        this.size = size;
        this.environment = new double[size][size];
        this.transtions = new ArrayList<>();
        setInitialPheromoneAmounts(estimatedPathCost);
    }

    private void setInitialPheromoneAmounts(double estimatedPathCost) {
        double pheromoneAmount = (double) size / estimatedPathCost;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                environment[i][j] = pheromoneAmount;
            }
        }
    }

    public double getPheromoneAmount(int row, int column) {
        return environment[row][column];
    }

    public int getSize() {
        return size;
    }



}
