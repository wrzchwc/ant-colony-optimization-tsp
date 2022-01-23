package tsp.aco.enironment;

public class Environment {
    private final double evaporation;
    private final int size;
    private final double[][] environment;
    private double[][] transitions;

    public Environment(int size, double evaporation, double estimatedPathCost) {
        this.evaporation = evaporation;
        this.size = size;
        this.environment = new double[size][size];
        this.transitions = new double[size][size];
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
        environment[start][finish] += pheromoneAmount;
        transitions[start][finish] += pheromoneAmount;
    }

    public void updatePheromoneAmounts() {
        pheromoneEvaporation();
        transitions = new double[size][size];
    }

    private void pheromoneEvaporation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                environment[i][j] = (environment[i][j] - transitions[i][j]) * evaporation + transitions[i][j];
            }
        }
    }
}
