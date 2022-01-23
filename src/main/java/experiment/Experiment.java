package experiment;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Experiment {
    private final String ROOT_FILEPATH = "src/main/resources/instances/";
    private final String CONFIG = "src/main/resources/setup.ini";
    private Ini ini;
    private final List<Double> das;
    private final List<Double> qas;

    public Experiment() {
        das = new ArrayList<>(3);
        qas = new ArrayList<>(3);
        try{
            ini = new Ini(new File(CONFIG));
            setAlgorithmType(das, "das");
            setAlgorithmType(qas, "qas");

        } catch (IOException exception){
            System.out.println("Configuration file not found!");
        }
    }

    private void setAlgorithmType(List<Double> parametersList, String name){
        for (String parameter: List.of("alpha", "beta", "rho")) {
            parametersList.add(Double.parseDouble(ini.get(name , parameter)));
        }
    }

    public void run(){
        //todo
    }
}
