package edu.tal.hamiltoncycle.statistics;

import java.util.ArrayList;
import java.util.List;

public class MemoryComplexity {

    private List<Integer> execMemories;
    private Integer currentMemoryCount;

    public MemoryComplexity() {
        this.execMemories = new ArrayList<>();
        this.currentMemoryCount = 0;
    }

    public void addMemory(Integer memory) {
        this.currentMemoryCount += memory;
    }

    public void nextExecution() {
        this.execMemories.add(currentMemoryCount);
        this.currentMemoryCount = 0;
    }

    public void resetMemorySet() {
        this.execMemories = new ArrayList<>();
    }

    public Integer getCurrentMemoryCount() {
        return currentMemoryCount;
    }

    public Integer getAverageMemory() {
        Integer sum = 0;
        for (Integer memory: execMemories) {
            sum += memory;
        }
        if(execMemories.size() != 0) return sum/execMemories.size();
        return 0;
    }
}
