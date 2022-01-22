package tsp.aco;

import graph.AdjacencyMatrix;
import tsp.NearestNeighbour;
import tsp.PathCost;

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

        List<Ant> colony = getColony();
        Environment environment = new Environment(size, 0.5, NearestNeighbour.get(graph));
        int solution = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            //todo: ants scattering
            for (Ant ant:colony) {
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
            //todo: pheromone evaporation
        }

        return solution;
    }

    private void setGraph(AdjacencyMatrix graph){
        this.size = graph.getSize();
    }

    private List<Ant> getColony(){
        List<Ant> colony = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            colony.add(new Ant(alpha, beta));
        }
        return colony;
    }
}
