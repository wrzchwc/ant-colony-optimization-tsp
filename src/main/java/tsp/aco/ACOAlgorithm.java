package tsp.aco;

import graph.AdjacencyMatrix;
import tsp.NearestNeighbour;
import tsp.aco.enironment.Environment;
import tsp.util.PathCost;
import tsp.aco.ants.Ant;
import tsp.aco.ants.Colony;

import java.util.ArrayList;
import java.util.List;

public class ACOAlgorithm {
    private final double alpha;
    private final double beta;
    private int size;
    private final boolean DAS;
    private AdjacencyMatrix graph;

    public ACOAlgorithm(double alpha, double beta, boolean DAS) {
        this.alpha = alpha;
        this.beta = beta;
        this.DAS = DAS;
    }

    public int solveTSP(AdjacencyMatrix graph) {
        setGraph(graph);

        Colony colony = new Colony(size, alpha, beta);
        Environment environment = new Environment(size, 0.5, NearestNeighbour.solveTSP(graph));
        int solution = Integer.MAX_VALUE;

        for (int i = 0; i < 100; i++) {
            colony.scatterAnts();
            for (Ant ant : colony.getAnts()) {
                List<Integer> tour = new ArrayList<>(List.of(ant.getInitialNode()));
                for (int j = 0; j < size - 1; j++) {
                    int recentNode = tour.get(tour.size() - 1);
                    int nextNode = ant.nextNode(graph, environment);
                    performTransition(ant, recentNode, nextNode, tour, environment);
                }
                performTransition(ant, tour.get(tour.size() - 1), tour.get(0), tour, environment);
                int tourCost = PathCost.get(graph, tour).intValue();

                if (tourCost < solution) {
                    solution = tourCost;
                }
            }
            colony.clearTabuLists();
            environment.updatePheromoneAmounts();
        }

        return solution;
    }

    private void setGraph(AdjacencyMatrix graph) {
        this.graph = graph;
        this.size = graph.getSize();
    }

    private void performTransition(Ant ant, int start, int finish, List<Integer> tour, Environment environment) {
        double pheromoneAmount = ant.getPheromone(graph.getCost(start, finish), DAS);
        tour.add(finish);
        environment.transition(start, finish, pheromoneAmount);
    }
}
