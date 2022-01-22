package tsp;

import graph.AdjacencyMatrix;
import tsp.util.Indexes;
import tsp.util.PathCost;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbour {

    public static double solveTSP(AdjacencyMatrix matrix) {
        List<Integer> unvisited = Indexes.get(matrix.getSize());
        List<Integer> solution = new ArrayList<>(List.of(0));
        unvisited.remove(0);
        int v = 0;
        while (!unvisited.isEmpty()) {
            v = getIndexOfNearest(matrix, unvisited, v);
            solution.add(v);
            unvisited.remove((Integer) v);
        }
        solution.add(0);
        return PathCost.get(matrix, solution);
    }

    private static int getIndexOfNearest(AdjacencyMatrix matrix, List<Integer> unvisited, int row) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (Number u : unvisited) {
            int value = matrix.getCost(row, u.intValue()).intValue();
            if (value < min) {
                min = value;
                index = u.intValue();
            }
        }
        return index;
    }
}
