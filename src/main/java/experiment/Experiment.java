package experiment;

import graph.AdjacencyMatrix;
import org.ini4j.Ini;
import tsp.aco.ACOAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Experiment {
    private Ini ini;
    private final List<Double> das;
    private final List<Double> qas;

    public Experiment() {
        das = new ArrayList<>(3);
        qas = new ArrayList<>(3);
        try {
            ini = new Ini(new File("src/main/resources/setup.ini"));
            setAlgorithmType(das, "das");
            setAlgorithmType(qas, "qas");
        } catch (IOException exception) {
            System.out.println("Configuration file not found!");
        }
    }

    private void setAlgorithmType(List<Double> parametersList, String name) {
        for (String parameter : List.of("alpha", "beta", "rho")) {
            parametersList.add(Double.parseDouble(ini.get(name, parameter)));
        }
    }

    public void run() {
        for (String key : ini.keySet()) {
            if (!List.of("das", "qas").contains(key)) {
                System.out.println(key);
                AdjacencyMatrix matrix = new AdjacencyMatrix("src/main/resources/instances/" + key);
                runAlgorithmType(key, matrix, true, das);
                runAlgorithmType(key, matrix, false, qas);
            }
        }
    }

    private void runAlgorithmType(String instance, AdjacencyMatrix matrix, boolean DAS, List<Double> parameters) {
        System.out.println(DAS ? "DAS" : "QAS");
        ACOAlgorithm algorithm = new ACOAlgorithm(parameters.get(0), parameters.get(1), DAS, parameters.get(2), matrix);
        int solution = Integer.parseInt(ini.get(instance, "solution"));
        long timeSum = 0;
        long absoluteErrorSum = 0;

        for (int i = 0; i < 5; i++) {
            long start = System.currentTimeMillis();
            int result = algorithm.solveTSP(matrix);
            long stop = System.currentTimeMillis();
            timeSum += stop - start;
            absoluteErrorSum += result - solution;
        }

        saveResult(instance, timeSum / 5, absoluteErrorSum / 5);
    }

    private void saveResult(String name, long time, long error) {
        try {
            FileWriter writer = new FileWriter("results.txt", true);
            writer.write(name + " " + time + " " + error + "\n");
            writer.close();
        } catch (IOException exception) {
            System.out.println("Data save error has occurred!");
        }
    }
}
