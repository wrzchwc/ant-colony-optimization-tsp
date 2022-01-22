package tsp.aco;

import java.util.ArrayList;
import java.util.List;

public class Environemt {
    private final double[][] environment;
    private final int size;
    private List<int[]> transtions;
    private final double evaporationFactor;

    public Environemt(int size, double evaporationFactor, double estimatedPathCost) {
        this.evaporationFactor = evaporationFactor;
        this.size = size;
        this.environment = new double[size][size];
        setInitialPheromoneAmounts(estimatedPathCost);
        transtions = new ArrayList<>(size);
    }

    private void setInitialPheromoneAmounts(double estimatedPathCost) {
        double pheromoneAmount = (double) size / estimatedPathCost;
        for (double[] row : environment) {
            for (double cell : row) {
                cell = pheromoneAmount;
            }
        }
    }

    public double getPheromoneAmount(int row, int column) {
        return environment[row][column];
    }

//    public void transition(int start, int finish, double pheromoneAmount) {
//        environment[start][finish] += pheromoneAmount;
//        transtions.add(new int[]{start, finish});
//    }

//    public void clearTransitions(){
//        transtions = new ArrayList<>(size);
//    }


}
