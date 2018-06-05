package edu.tal.hamiltoncycle;

import edu.tal.hamiltoncycle.calculation.BruteForce;
import edu.tal.hamiltoncycle.calculation.geneticalgorithm.GeneticAlgorithm;
import edu.tal.hamiltoncycle.graph.ConnectionMatrix;

public class Main {

    public static void main(String[] args) {
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(4);
        connectionMatrix.addConnection(0, 1);
        connectionMatrix.addConnection(0, 2);
        connectionMatrix.addConnection(3, 1);
        connectionMatrix.addConnection(3, 2);
        //connectionMatrix.printConnectionMatrix();
        BruteForce bruteForce = new BruteForce(connectionMatrix);
        bruteForce.execute();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(connectionMatrix);
        geneticAlgorithm.execute(10, 10);
    }
}
