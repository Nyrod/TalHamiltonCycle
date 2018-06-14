package edu.tal.hamiltoncycle;

import edu.tal.hamiltoncycle.calculation.BruteForce;
import edu.tal.hamiltoncycle.calculation.geneticalgorithm.GeneticAlgorithm;
import edu.tal.hamiltoncycle.generator.GraphFromFileReader;
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
        ConnectionMatrix connectionMatrix = GraphGenerator.generateGraph(10, 5);
        connectionMatrix.printConnectionMatrix();
//        connectionMatrix.addConnection(0, 1);
//        connectionMatrix.addConnection(0, 2);
//        connectionMatrix.addConnection(3, 1);
//        connectionMatrix.addConnection(3, 2);
        //connectionMatrix.printConnectionMatrix();
        BruteForce bruteForce = new BruteForce(connectionMatrix, new ComputeComplexity(), new MemoryComplexity());
        List<int[]> result = bruteForce.execute();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(connectionMatrix, new ComputeComplexity(), new MemoryComplexity());
        geneticAlgorithm.execute(200, 15);
        System.out.println(geneticAlgorithm.getAverageComputeTime());
    }
}
