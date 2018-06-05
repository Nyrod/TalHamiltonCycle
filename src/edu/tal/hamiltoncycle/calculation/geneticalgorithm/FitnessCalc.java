package edu.tal.hamiltoncycle.calculation.geneticalgorithm;

import edu.tal.hamiltoncycle.graph.ConnectionMatrix;

public class FitnessCalc {

    private static ConnectionMatrix connectionMatrix;

    public static void setConnectionMatrix(ConnectionMatrix connectionMatrix) {
        FitnessCalc.connectionMatrix = connectionMatrix;
    }

    public static int getFitness(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.size() - 1; i++) {
            if (connectionMatrix.haveConnection(individual.getGene(i), individual.getGene(i + 1)))
                fitness++;
        }
        if (connectionMatrix.haveConnection(individual.getGene(individual.size() - 1), individual.getGene(0)))
            fitness++;

        return fitness;
    }

    public static int getSolution() {
        return connectionMatrix.getNodeNumber();
    }
}
