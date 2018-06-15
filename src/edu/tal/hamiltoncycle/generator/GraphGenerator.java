package edu.tal.hamiltoncycle.generator;

import edu.tal.hamiltoncycle.graph.ConnectionMatrix;

import java.util.List;
import java.util.Random;

public class GraphGenerator {

    private static Random random = new Random();

//    public static ConnectionMatrix generateGraph(int nodeNumber, double possibleOfConnection) {
//
//        return new ConnectionMatrix();
//    }
//
    public static ConnectionMatrix generateGraph(int nodeNumber, int nodesDegree) {


        ConnectionMatrix connectionMatrix = new ConnectionMatrix(nodeNumber);
        if(nodeNumber <= nodesDegree + 1) {
            for(int i = 0; i < nodeNumber; i++) {
                for(int x = 0; x < nodeNumber; x++) {
                    if(x == i)
                        continue;
                    connectionMatrix.addConnection(x, i);
                }
            }
        } else {
            for(int i = 0; i < nodeNumber; i++) {
                if(connectionMatrix.getNodeDegree(i) < nodesDegree) {
                    List<Integer> notConnectedNode = connectionMatrix.getNotConnectedToNode(i);
                    while (connectionMatrix.getNodeDegree(i) < nodesDegree && !notConnectedNode.isEmpty()) {
                        int nodeToConnect = notConnectedNode.remove(random.nextInt(notConnectedNode.size()));
                        while(notConnectedNode.size() > 0 && (connectionMatrix.haveConnection(i, nodeToConnect) || connectionMatrix.getNodeDegree(nodeToConnect) >= nodesDegree))
                            nodeToConnect = notConnectedNode.remove(random.nextInt(notConnectedNode.size()));
                        connectionMatrix.addConnection(i, nodeToConnect);
                    }
                }
            }
        }
//        BruteForce bruteForce = new BruteForce(connectionMatrix, new ComputeComplexity(), new MemoryComplexity());
//        List<int[]> result = bruteForce.execute();
//        if(result.size() == 0) {
//
//        }
        return connectionMatrix;
    }
}