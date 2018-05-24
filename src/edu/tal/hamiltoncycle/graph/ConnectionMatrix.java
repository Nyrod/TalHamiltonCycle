package edu.tal.hamiltoncycle.graph;

public class ConnectionMatrix {

    private boolean[][] connectionMatrix;
    private int nodeNumber;

    public ConnectionMatrix(int nodeNumber) {
        this.nodeNumber = nodeNumber;
        this.connectionMatrix = new boolean[nodeNumber][nodeNumber];
        for(int i = 0; i < nodeNumber; i++) {
            for(int j = 0; j < nodeNumber; j++) {
                this.connectionMatrix[i][j] = false;
            }
        }
    }

    public static ConnectionMatrix getMatrixFromFile() {
        return new ConnectionMatrix(5);
    }

    public boolean haveConnection(int node1, int node2) {
        return connectionMatrix[node1][node2];
    }

    public void addConnection(int node1, int node2) {
        if(node1 < connectionMatrix.length && node2 < connectionMatrix.length) {
            connectionMatrix[node1][node2] = true;
            connectionMatrix[node2][node1] = true;
        }
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void printConnectionMatrix() {
        for(int i = 0; i < nodeNumber; i++) {
            for(int j = 0; j < nodeNumber; j++) {
                if(connectionMatrix[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }
}
