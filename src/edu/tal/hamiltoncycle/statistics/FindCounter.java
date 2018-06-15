package edu.tal.hamiltoncycle.statistics;


public class FindCounter {
    private static FindCounter ourInstance = new FindCounter();

    private int solutions = 0;

    public static FindCounter getInstance() {
        return ourInstance;
    }

    private FindCounter() {
    }

    public void addCount() {
        solutions++;
    }

    public int getCount() {
        return solutions;
    }
}
