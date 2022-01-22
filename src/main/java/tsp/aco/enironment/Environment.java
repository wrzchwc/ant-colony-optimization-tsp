package tsp.aco.enironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {
    private final double[][] environment;
    private final int size;
    private Map<Integer, List<Transition>> transitions;
    private final double evaporationFactor;

    public Environment(int size, double evaporationFactor, double estimatedPathCost) {
        this.evaporationFactor = evaporationFactor;
        this.size = size;
        this.environment = new double[size][size];
        this.transitions = new HashMap<>(size);
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

    public void transition(int start, int finish, double pheromoneAmount) {
        if (transitions.containsKey(start)) {
            transitions.get(start).add(new Transition(finish, pheromoneAmount));
        } else {
            transitions.put(start, new ArrayList<Transition>(List.of(new Transition(finish, pheromoneAmount))));
        }
    }

    public void updatePheromoneAmounts() {
        pheromoneEvaporation();
        for (Integer index : transitions.keySet()) {
            for (Transition transition : transitions.get(index)) {
                environment[index][transition.getFinish()] += transition.getPheromoneAmount();
            }
            transitions.get(index).clear();
        }
    }

    private void pheromoneEvaporation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                environment[i][j] *= evaporationFactor;
            }
        }
    }
}
