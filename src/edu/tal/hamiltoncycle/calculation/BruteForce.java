package edu.tal.hamiltoncycle.calculation;

import edu.tal.hamiltoncycle.graph.ConnectionMatrix;

public class BruteForce {

    private ConnectionMatrix connectionMatrix;
    private int[] currentPermutation;

    public BruteForce(ConnectionMatrix connectionMatrix) {
        this.connectionMatrix = connectionMatrix;
        this.currentPermutation = new int[connectionMatrix.getNodeNumber()];
        setFirstPermutation();
    }

    public void execute() {
        while(getNextPermutation()) {
            if(checkIfPermutationIsHamiltonCycle())
                printCurrentPermutation();
        }
    }

    private boolean checkIfPermutationIsHamiltonCycle() {
        int lastNode = currentPermutation.length - 1;
        for(int i = 0; i < lastNode; i++) {
            if(!connectionMatrix.haveConnection(currentPermutation[i], currentPermutation[i + 1]))
                return false;
        }
        return connectionMatrix.haveConnection(currentPermutation[lastNode], currentPermutation[0]);
    }

    private boolean getNextPermutation() {
        int j, i = j = currentPermutation.length - 1;
        while (i > 0 && currentPermutation[i - 1] >= currentPermutation[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }

        while (currentPermutation[j] <= currentPermutation[i - 1]) {
            j--;
        }
        swapElementsInPermutationArray(i - 1, j);

        j = currentPermutation.length - 1;
        while (i < j) {
            swapElementsInPermutationArray(i, j);
            i++;
            j--;
        }

        return true;
    }

    private void printCurrentPermutation() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< currentPermutation.length; i++) {
            builder.append(currentPermutation[i]);
            if(i < currentPermutation.length -1)
                builder.append(" -> ");
        }
        System.out.println(builder.toString());
    }

    private void swapElementsInPermutationArray(int i, int j) {
        int tmp = currentPermutation[i];
        currentPermutation[i] = currentPermutation[j];
        currentPermutation[j] = tmp;
    }

    private void setFirstPermutation() {
        for (int i = 0; i < currentPermutation.length; i++) {
            currentPermutation[i] = i;
        }
    }
}
