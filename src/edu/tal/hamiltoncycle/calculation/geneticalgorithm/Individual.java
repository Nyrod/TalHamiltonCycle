package edu.tal.hamiltoncycle.calculation.geneticalgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {

    private static int genesNumber = 0;
    private static Random random = new Random();

    private int[] genes;
    private int fitness;

    public static Individual generateIndividual() {
        Individual individual = new Individual();
        List<Integer> genesPule = new ArrayList<>();
        for (int i = 0; i < genesNumber; i++) {
            genesPule.add(i);
        }
        for (int i = 0; i < genesNumber; i++) {
            int generatedNumber = genesPule.remove(random.nextInt(genesPule.size()));
            individual.setGene(i, generatedNumber);
        }
        return individual;
    }

    private Individual() {
        genes = new int[genesNumber];
        this.fitness = 0;
    }

    public int getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, int value) {
        genes[index] = value;
        fitness = 0;
    }

    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    public static void setGenesNumber(int genesNumber) {
        Individual.genesNumber = genesNumber;
    }

    private boolean checkIfIs(int index, int gene) {
        for (int i = 0; i < index; i++) {
            if (genes[i] == gene)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < genes.length; i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
