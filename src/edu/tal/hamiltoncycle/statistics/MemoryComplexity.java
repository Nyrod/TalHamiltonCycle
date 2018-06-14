package edu.tal.hamiltoncycle.statistics;

import java.util.ArrayList;
import java.util.List;

public class MemoryComplexity {

    private List<Long> execMemories;
    private Long currentMemoryCount;

    public MemoryComplexity() {
        this.execMemories = new ArrayList<>();
        this.currentMemoryCount = 0L;
    }

    public void addMemory(Long memory) {
        this.currentMemoryCount += memory;
    }

    public void nextExecution() {
        this.execMemories.add(currentMemoryCount);
        this.currentMemoryCount = 0L;
    }

    public void resetMemorySet() {
        this.execMemories = new ArrayList<>();
    }

    public Long getAverageMemory() {
        Long sum = 0L;
        for (Long memory: execMemories) {
            sum += memory;
        }
        if(execMemories.size() != 0) return sum/execMemories.size();
        return 0L;
    }
}
