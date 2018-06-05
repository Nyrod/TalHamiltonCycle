package edu.tal.hamiltoncycle.calculation.geneticalgorithm;

import edu.tal.hamiltoncycle.graph.ConnectionMatrix;

public class GeneticAlgorithm {

    private ConnectionMatrix connectionMatrix;

    public GeneticAlgorithm(ConnectionMatrix connectionMatrix) {
        this.connectionMatrix = connectionMatrix;
        Individual.setGenesNumber(connectionMatrix.getNodeNumber());
        FitnessCalc.setConnectionMatrix(connectionMatrix);
    }

    public void execute(int populationSize, int generationNumber) {
        Population population = new Population(populationSize);
        int generationCount = 0;
        while (population.getFittest().getFitness() != FitnessCalc.getSolution() && generationCount <= generationNumber) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());
            population = evolvePopulation(population);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(population.getFittest());

    }

    private Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size());

        for (int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private void mutate(Individual individual) {
        int idx1 = (int) Math.random() * individual.size();
        int idx2 = (int) Math.random() * individual.size();
        if(idx1 == idx2) {
            if(idx2 == individual.size() - 1)
                idx2--;
            else
                idx2++;
        }
        int gene = individual.getGene(idx1);
        individual.setGene(idx1, individual.getGene(idx2));
        individual.setGene(idx2, gene);
    }
}
