package tsp.aco.ants;

import tsp.util.Indexes;

import java.util.ArrayList;
import java.util.List;

public class Colony {
    private final List<Ant> colony;
    private final int size;

    public Colony(int size, double alpha, double beta) {
        this.size = size;
        this.colony = createColony(alpha, beta);
    }

    private List<Ant> createColony(double alpha, double beta) {
        List<Ant> colony = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            colony.add(new Ant(alpha, beta));
        }
        return colony;
    }

    public List<Ant> getAnts() {
        return colony;
    }

    public void clearTabuLists() {
        for (Ant ant : colony) {
            ant.clearTabuList();
        }
    }

    public void scatterAnts() {
        List<Integer> indexes = Indexes.get(size);
        for (Ant ant : colony) {
            ant.setInitialNode(indexes.remove((int) (Math.random() * indexes.size())));
        }
    }
}
