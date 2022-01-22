package tsp.util;

import java.util.ArrayList;
import java.util.List;

public class Indexes {
    public static List<Integer> get(int size){
        List<Integer> indexes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            indexes.add(i);
        }
        return indexes;
    }
}
