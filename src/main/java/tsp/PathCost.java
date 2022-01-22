package tsp;

import graph.AdjacencyMatrix;

import java.util.List;

public class PathCost {
    public static Double get(AdjacencyMatrix matrix, List<Integer> list){
        double cost = 0.0;
        int size = list.size();

        for (int i = 0; i < size - 1; i++) {
            cost+=matrix.getCost(list.get(i), list.get(i+1));
        }

        return cost;
    }
}
