package edu.tal.hamiltoncycle;

import edu.tal.hamiltoncycle.calculation.BruteForce;
import edu.tal.hamiltoncycle.generator.GraphGenerator;
import edu.tal.hamiltoncycle.graph.ConnectionMatrix;
import edu.tal.hamiltoncycle.statistics.ComputeComplexity;
import edu.tal.hamiltoncycle.statistics.MemoryComplexity;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //System.out.println(GraphFromFileReader.checkIfGraphFileIsValid());
        //GraphFromFileReader.getMatrixFromFile().printConnectionMatrix();
//        ConnectionMatrix connectionMatrix = new ConnectionMatrix(4);
        ConnectionMatrix connectionMatrix = GraphGenerator.generateGraph(12, 10);
        connectionMatrix.printConnectionMatrix();
//        connectionMatrix.addConnection(0, 1);
//        connectionMatrix.addConnection(0, 2);
//        connectionMatrix.addConnection(3, 1);
//        connectionMatrix.addConnection(3, 2);
        //connectionMatrix.printConnectionMatrix();
        BruteForce bruteForce = new BruteForce(connectionMatrix, new ComputeComplexity(), new MemoryComplexity());
        //GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(connectionMatrix, new ComputeComplexity(), new MemoryComplexity());
        int tests = 100;
        int good = 0;
        for (int i = 0; i < tests; i++) {
            //int[] result = geneticAlgorithm.execute(5, 3);
            List<int[]> result = bruteForce.execute();
            if(result.size() != 0) {
                good++;
            }
        }

        System.out.println("--------------");
        System.out.println("WYNIKI BRUTE:");
        System.out.println("Próba= " + tests + " ,znalezione rozwiązania= " + good);

        System.out.println(bruteForce.getAverageComputeTime());
        System.out.println(bruteForce.getAverageMemoryUsage());
    }
}
