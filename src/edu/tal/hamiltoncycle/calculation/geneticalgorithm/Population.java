package edu.tal.hamiltoncycle.calculation.geneticalgorithm;

public class Population {

    private Individual[] individuals;

    public Population(int populationSize) {
        this.individuals = new Individual[populationSize];
        for(int i = 0; i < populationSize; i++) {
            this.individuals[i] = (Individual.generateIndividual());
        }
    }
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];

        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public int size() {
        return individuals.length;
    }
}
