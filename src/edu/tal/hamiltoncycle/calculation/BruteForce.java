package edu.tal.hamiltoncycle.calculation;

import edu.tal.hamiltoncycle.graph.ConnectionMatrix;
import edu.tal.hamiltoncycle.statistics.ComputeComplexity;
import edu.tal.hamiltoncycle.statistics.MemoryComplexity;

import java.util.ArrayList;
import java.util.List;

public class BruteForce {

    private ConnectionMatrix connectionMatrix;
    private int[] currentPermutation;
    private ComputeComplexity timer;
    private MemoryComplexity memory;

    public BruteForce(ConnectionMatrix connectionMatrix, ComputeComplexity computeComplexity, MemoryComplexity memory) {
        this.connectionMatrix = connectionMatrix;
        this.currentPermutation = new int[connectionMatrix.getNodeNumber()];
        this.timer = computeComplexity;
        this.memory = memory;
        setFirstPermutation();
    }

    public List<int[]> execute() {
        timer.startTimer();
        memory.addMemory((connectionMatrix.getNodeNumber() * connectionMatrix.getNodeNumber()));
        setFirstPermutation();
        List<int[]> result = new ArrayList<>();
        while(getNextPermutation()) {
            if(checkIfPermutationIsHamiltonCycle()) {
                result.add(currentPermutation);
                //printCurrentPermutation();
            }
        }

        timer.stopTimer();
        memory.nextExecution();

        return result;
    }

    public void resetTimerSet() {
        timer.resetTimeSet();
    }

    public Double getAverageComputeTime() {
        return timer.getAverageTime();
    }

    public void resetMemorySet() {
        memory.resetMemorySet();
    }

    public Integer getAverageMemoryUsage() {
        return this.memory.getAverageMemory();
    }

    private boolean checkIfPermutationIsHamiltonCycle() {
        memory.addMemory(1);
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

        memory.addMemory(2);
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
        memory.addMemory(1);
    }

    private void setFirstPermutation() {
        for (int i = 0; i < currentPermutation.length; i++) {
            currentPermutation[i] = i;
        }
    }
}
