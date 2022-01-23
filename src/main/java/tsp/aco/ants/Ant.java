package tsp.aco.ants;

import graph.AdjacencyMatrix;
import tsp.aco.enironment.Environment;

import java.util.*;
import java.util.stream.Collectors;

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
        int currentNode = tabuList.get(tabuList.size() - 1);
        int size = environment.getSize();
        double sumOfProbabilities = 0.0;
        Map<Double, Integer> probabilities = new TreeMap<>();

        for (int i = 0; i < size; i++) {
            if (!tabuList.contains(i)) {
                double pheromoneAmount = environment.getPheromoneAmount(currentNode, i);
                double distance = graph.getCost(currentNode, i);
                double probability = transitionProbability(pheromoneAmount, distance);
                probabilities.put(probability, i);
                sumOfProbabilities += probability;
            }
        }
        int nextNode = probabilities.get(probabilities.keySet()
                .stream()
                .collect(Collectors.toUnmodifiableList())
                .get(getIndex(sumOfProbabilities, probabilities.keySet()))
        );
        tabuList.add(nextNode);

        return nextNode;
    }

    private int getIndex(double sumOfProbabilities, Set<Double> probabilities) {
        double value = Math.random() * sumOfProbabilities;
        double sum = 0.0;
        int next = -1;

        Iterator<Double> iterator = probabilities.iterator();
        while (sum <= value && iterator.hasNext()) {
            sum += iterator.next();
            next++;
        }

        return next;
    }

    private double transitionProbability(double pheromoneAmount, double distance) {
        return Math.pow(pheromoneAmount, alpha) * Math.pow(distance, -beta);
    }

    public void setInitialNode(int node) {
        tabuList.add(node);
    }

    public int getInitialNode() {
        return tabuList.get(0);
    }
}
