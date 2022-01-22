package tsp.aco.ants;

import graph.AdjacencyMatrix;
import tsp.aco.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ant {
    private final List<Integer> tabuList;
    private final double alpha;
    private final double beta;

    public Ant(double alpha, double beta) {
        tabuList = new ArrayList<>();
        this.alpha = alpha;
        this.beta = beta;
    }

    public void clearTabuList() {
        tabuList.clear();
    }

    public double getPheromone(double distance, boolean DAS) {
        return DAS ? distance : 1 / distance;
    }

    public int nextNode(AdjacencyMatrix graph, Environment environment) {
        Map<Double, Integer> probabilities = new HashMap<>();
        int currentNode = tabuList.get(tabuList.size() - 1);
        int size = environment.getSize();

        for (int i = 0; i < size; i++) {
            if (!tabuList.contains(i)) {
                double pheromoneAmount = environment.getPheromoneAmount(currentNode, i);
                double distance = graph.getCost(currentNode, i);
                probabilities.put(transitionProbability(pheromoneAmount, distance), i);
            }
        }

        int nextNode = probabilities.get(probabilities.keySet().stream().max(Double::compareTo).get());
        tabuList.add(nextNode);

        return nextNode;
    }

    private double transitionProbability(double pheromoneAmount, double distance) {
        return Math.pow(pheromoneAmount, alpha) * Math.pow(distance, -beta);
    }

    public void setInitialNode(int node){
        tabuList.add(node);
    }
}
