package aco;

import graph.AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;

public class ACOAlgorithm {
    private final int alpha;
    private final int beta;
    private int size;
    private AdjacencyMatrix graph;
    private final boolean DAS;

    public ACOAlgorithm(int alpha, int beta, boolean DAS) {
        this.alpha = alpha;
        this.beta = beta;
        this.DAS = DAS;
        this.graph = null;
        this.size = 0;
    }

    public int solveTSP(AdjacencyMatrix graph){
        setGraph(graph);

        List<Ant> colony = getColony();
        int solution = Integer.MAX_VALUE;

        return 0;
    }

    private void setGraph(AdjacencyMatrix graph){
        this.graph = graph;
        this.size = graph.getSize();
    }

    private List<Ant> getColony(){
        List<Ant> colony = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            colony.add(new Ant(alpha, beta));
        }
        return colony;
    }
}
