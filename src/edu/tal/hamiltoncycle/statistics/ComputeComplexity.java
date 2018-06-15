package edu.tal.hamiltoncycle.statistics;

import java.util.ArrayList;
import java.util.List;

public class ComputeComplexity {

    private List<Long> execTimes;
    private Long currentMeasureStart;

    public ComputeComplexity() {
        execTimes = new ArrayList<>();
    }

    public void startTimer() {
        this.currentMeasureStart = System.currentTimeMillis();
    }

    public void stopTimer() {
        execTimes.add(System.currentTimeMillis() - this.currentMeasureStart);
    }

    public void resetTimeSet() {
        this.execTimes = new ArrayList<>();
    }

    public Double getAverageTime() {
        Double sum = 0.0;
        for (Long time: execTimes) {
            sum += time;
        }
        if(execTimes.size() != 0) return sum/execTimes.size();
        return 0.0;
    }
}
