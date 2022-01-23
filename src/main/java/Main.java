import experiment.Experiment;

import java.util.Scanner;

public class Main {
    private static void printHeader(){
        System.out.println("Effective Algorithms Design");
        System.out.println("Project 7 - TSP using Ant colony optimization algorithm");
        System.out.println("Jakub Wierzchowiec, January 2022");
    }

    private static int getMode(){
        Scanner input = new Scanner(System.in);
        System.out.println("0 Quit");
        System.out.println("1 Launch experiment");
        System.out.print("> ");
        return input.nextInt();
    }

    public static void main(String[] args) {
        printHeader();

        while (true){
            int mode = getMode();
            if (mode == 0) {
                break;
            } else if (mode == 1) {
                Experiment experiment = new Experiment();
                experiment.run();
            }
        }
    }
}
