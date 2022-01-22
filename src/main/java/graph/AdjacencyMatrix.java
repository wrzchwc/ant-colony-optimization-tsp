package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdjacencyMatrix {
    private Number[][] matrix;
    private int size;
    private final boolean integralValues;

    public AdjacencyMatrix(String filepath, boolean integralValues) {
        this.integralValues = integralValues;
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            this.size = Integer.parseInt(scanner.nextLine());
            this.matrix = new Number[this.size][this.size];
            List<String[]> lines = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                lines.add(scanner.nextLine().trim().split(" +"));
            }

            scanner.close();
            initializeMatrix(lines);
        } catch (FileNotFoundException exception) {
            System.out.println("File named " + filepath + " not found!");
            this.size = 0;
            this.matrix = new Number[0][0];
        } catch (ParseException exception){
            System.out.println("Error while parsing data!");
            this.size = 0;
            this.matrix = new Number[0][0];
        }
    }

    private void initializeMatrix(List<String[]> lines) throws ParseException {
        NumberFormat format = NumberFormat.getNumberInstance();
        int row = 0;
        for (String[] line : lines) {
            for (int i = 0; i < line.length; i++) {
                matrix[row][i] = format.parse(line[i]);
            }
            row++;
        }
    }

    public int getSize() {
        return size;
    }

    public Number getData(int row, int column) {
        Number data = matrix[row][column];
        if (integralValues){
            return data.intValue();
        }
        return data.doubleValue();
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
