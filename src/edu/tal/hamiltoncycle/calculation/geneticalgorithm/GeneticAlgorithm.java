package edu.tal.hamiltoncycle.calculation.geneticalgorithm;

import edu.tal.hamiltoncycle.graph.ConnectionMatrix;
import edu.tal.hamiltoncycle.statistics.ComputeComplexity;
import edu.tal.hamiltoncycle.statistics.MemoryComplexity;

public class GeneticAlgorithm {

    private ConnectionMatrix connectionMatrix;
    private ComputeComplexity timer;
    private MemoryComplexity memory;

    public GeneticAlgorithm(ConnectionMatrix connectionMatrix, ComputeComplexity computeComplexity, MemoryComplexity memory) {
        this.connectionMatrix = connectionMatrix;
        this.timer = computeComplexity;
        this.memory = memory;
        Individual.setGenesNumber(connectionMatrix.getNodeNumber());
        FitnessCalc.setConnectionMatrix(connectionMatrix);
    }

    public int[] execute(int populationSize, int generationNumber) {
        timer.startTimer();
        memory.addMemory((long) (connectionMatrix.getNodeNumber() * connectionMatrix.getNodeNumber()));
        memory.addMemory((long) (connectionMatrix.getNodeNumber() * populationSize));
        memory.addMemory((long) 1);

        Population population = new Population(populationSize);
        int generationCount = 0;
        while (population.getFittest().getFitness() != FitnessCalc.getSolution() && generationCount < generationNumber) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());
            population = evolvePopulation(population);
        }
        Individual best = population.getFittest();
        if(best.getFitness() == FitnessCalc.getSolution()) {
            System.out.println("Solution found!");
            System.out.println("Generation: " + generationCount);
            System.out.println("Solution:");
            System.out.println(best);
            timer.stopTimer();
            memory.nextExecution();
            return best.getGenes();

        } else {
            System.out.println("Dont find solution");
            timer.stopTimer();
            memory.nextExecution();
            return null;
        }
    }

    public void resetTimerSet() {
        timer.resetTimeSet();
    }

    public Long getAverageComputeTime() {
        return timer.getAverageTime();
    }

    public void resetMemorySet() {
        memory.resetMemorySet();
    }

    public Long getAverageMemoryUsage() {
        return this.memory.getAverageMemory();
    }

    private Population evolvePopulation(Population pop) {
//        Population newPopulation = new Population(pop.size());
//
//        for (int i = 0; i < newPopulation.size(); i++) {
//            mutate(newPopulation.getIndividual(i));
//        }
//
//        return newPopulation;
        for (int i = 0; i < pop.size(); i++) {
            mutate(pop.getIndividual(i));
        }

        return pop;
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
        memory.addMemory((long) 3);

    }
}
