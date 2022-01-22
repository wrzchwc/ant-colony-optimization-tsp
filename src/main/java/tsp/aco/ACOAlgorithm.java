package tsp.aco;

import graph.AdjacencyMatrix;
import tsp.NearestNeighbour;
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

    public ACOAlgorithm(double alpha, double beta, boolean DAS) {
        this.alpha = alpha;
        this.beta = beta;
        this.DAS = DAS;
        this.size = 0;
    }

    public int solveTSP(AdjacencyMatrix graph){
        setGraph(graph);

        Colony colony = new Colony(size, alpha, beta);
        Environment environment = new Environment(size, 0.5, NearestNeighbour.solveTSP(graph));
        int solution = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            colony.scatterAnts();
            for (Ant ant : colony.getAnts()) {
                List<Integer> tour = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    tour.add(ant.nextNode(graph, environment));
                    //todo: transition in the environment
                }
                tour.add(tour.get(0));
                //todo: transition in the environment
                int tourCost = PathCost.get(graph, tour).intValue();

                if (tourCost < solution){
                    solution = tourCost;
                }
            }
            colony.clearTabuLists();
            //todo: pheromone evaporation
        }

        return solution;
    }

    private void setGraph(AdjacencyMatrix graph){
        this.size = graph.getSize();
    }
}
