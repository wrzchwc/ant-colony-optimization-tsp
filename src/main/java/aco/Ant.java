package aco;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    private List<Integer> tabuList;
    private final int listSize;
    private final int alpha;
    private final int beta;

    public Ant(int tabuListSize, int alpha, int beta) {
        listSize = tabuListSize;
        tabuList = new ArrayList<>(listSize);
        this.alpha = alpha;
        this.beta = beta;
    }

    public void clearTabuList() {
        tabuList = new ArrayList<>(listSize);
    }

    public Number getPheromone(Number distance, boolean DAS) {
        if (DAS) {
            return distance;
        }
        return 1 / distance.doubleValue();
    }

    public int selectNext (){
        return 0;
    }


}
