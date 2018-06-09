package edu.tal.hamiltoncycle.generator;

import edu.tal.hamiltoncycle.graph.ConnectionMatrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphFromFileReader {

    public static String fileName = "graph.txt";

    public static ConnectionMatrix getMatrixFromFile() {
        ConnectionMatrix connectionMatrix = null;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> lines = stream.collect(Collectors.toList());
            connectionMatrix = new ConnectionMatrix((int)lines.stream().filter(a -> !a.startsWith("//")).count());
            for(int i = 0; i < lines.size(); i++) {
                if(lines.get(i).startsWith("//"))
                    continue;
                changeLinesToConnection(connectionMatrix, lines.get(i), i);
            }
        } catch (IOException e) {

        }
        return connectionMatrix;
    }

    private static void changeLinesToConnection(ConnectionMatrix connectionMatrix, String line, int i) {
        for (int x = 0; x < line.length(); x++) {
            if (line.charAt(x) == '1')
                connectionMatrix.addConnection(i, x);
        }
    }

    public static boolean checkIfGraphFileIsValid() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> lines = stream.collect(Collectors.toList());
            int nodes = (int)lines.stream().filter(a -> !a.startsWith("//")).count();
            for(int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if(line.startsWith("//"))
                    continue;
                if(lines.get(i).length() != nodes) {
                    return false;
                }
            }
        } catch (IOException e) {

        }
        return true;
    }
}
