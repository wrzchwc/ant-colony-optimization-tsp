package tsp;

import graph.AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbour {
    private static AdjacencyMatrix matrix;

    public static double get(AdjacencyMatrix matrix) {
        NearestNeighbour.matrix = matrix;
        int size = matrix.getSize();
        List<Integer> unvisited = getIndexes(size);
        List<Integer> solution = new ArrayList<>(List.of(0));
        unvisited.remove(0);
        int v = 0;
        while (!unvisited.isEmpty()) {
            v = getIndexOfNearest(matrix, unvisited, v);
            solution.add(v);
            unvisited.remove((Integer) v);
        }
        solution.add(0);
        return getCost(solution);
    }

    private static List<Integer> getIndexes(int size) {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tmp.add(i);
        }
        return tmp;
    }

    private static int getIndexOfNearest(AdjacencyMatrix matrix, List<Integer> unvisited, int row) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (Number u : unvisited) {
            int value = matrix.getData(row, u.intValue()).intValue();
            if (value < min) {
                min = value;
                index = u.intValue();
            }
        }
        return index;
    }

    private static double getCost(List<Integer> list) {
        double cost = 0.0;
        for (int i = 0; i < list.size() - 1; i++) {
            cost += matrix.getData(list.get(i), list.get(i + 1));
        }
        return cost;
    }
}
