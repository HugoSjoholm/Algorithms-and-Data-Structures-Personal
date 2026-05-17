import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AntennaGraph {
    private Graph graph;
    private HashMap<String, Antenna> antennas;

    private static class Antenna {
        String name;
        double x;
        double y;
        double r;

        Antenna(String name, double x, double y, double r) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    public AntennaGraph(String fileName) throws FileNotFoundException {
        antennas = new HashMap<String, Antenna>();
        readAntennas(fileName);
        buildGraph();
    }

    private void readAntennas(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNext()) {
            String word = scanner.next();

            if (word.equals("Node:")) {
                String name = scanner.next();

                scanner.next();
                double x = scanner.nextDouble();

                scanner.next();
                double y = scanner.nextDouble();

                scanner.next();
                double r = scanner.nextDouble();

                antennas.put(name, new Antenna(name, x, y, r));
            }
        }

        scanner.close();
    }

    private void buildGraph() {
        ArrayList<Antenna> list = new ArrayList<Antenna>(antennas.values());
        HashMap<String, ArrayList<String>> neighbours = new HashMap<String, ArrayList<String>>();

        for (Antenna a : list) {
            neighbours.put(a.name, new ArrayList<String>());
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Antenna a = list.get(i);
                Antenna b = list.get(j);

                if (overlap(a, b)) {
                    neighbours.get(a.name).add(b.name);
                    neighbours.get(b.name).add(a.name);
                }
            }
        }

        String input = "";

        for (String name : neighbours.keySet()) {
            input += name;

            for (String other : neighbours.get(name)) {
                input += " " + other;
            }

            input += "\n";
        }

        Scanner scanner = new Scanner(input);
        graph = new Graph(scanner, " ");
        scanner.close();
    }

    private boolean overlap(Antenna a, Antenna b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance <= a.r + b.r;
    }

    public boolean connected(String a, String b) {
        if (!antennas.containsKey(a) || !antennas.containsKey(b)) {
            return false;
        }

        HashSet<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();

        visited.add(a);
        queue.add(a);

        while (!queue.isEmpty()) {
            String current = queue.remove();

            if (current.equals(b)) {
                return true;
            }

            for (String next : graph.adj(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return false;
    }

    public int nrOfNwks() {
        HashSet<String> visited = new HashSet<String>();
        int count = 0;

        for (String node : graph.vertices()) {
            if (!visited.contains(node)) {
                count++;
                visitNetwork(node, visited);
            }
        }

        return count;
    }

    private void visitNetwork(String start, HashSet<String> visited) {
        Queue<String> queue = new LinkedList<String>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.remove();

            for (String next : graph.adj(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
    }

    public double distanceToBorder(String a) {
        if (!antennas.containsKey(a)) {
            return -1;
        }

        HashSet<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        Antenna start = antennas.get(a);
        double longest = 0;

        visited.add(a);
        queue.add(a);

        while (!queue.isEmpty()) {
            String current = queue.remove();
            Antenna other = antennas.get(current);

            double dx = start.x - other.x;
            double dy = start.y - other.y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > longest) {
                longest = distance;
            }

            for (String next : graph.adj(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return longest;
    }

    public int shortestDistance(String a, String b) {
        if (!antennas.containsKey(a) || !antennas.containsKey(b)) {
            return -1;
        }

        HashSet<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        HashMap<String, Integer> distance = new HashMap<String, Integer>();

        visited.add(a);
        queue.add(a);
        distance.put(a, 0);

        while (!queue.isEmpty()) {
            String current = queue.remove();

            if (current.equals(b)) {
                return distance.get(current);
            }

            for (String next : graph.adj(current)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                    distance.put(next, distance.get(current) + 1);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        AntennaGraph antennaGraph = new AntennaGraph(args[0]);

        System.out.println("Number of networks: " + antennaGraph.nrOfNwks());

        if (args.length >= 3) {
            String a = args[1];
            String b = args[2];

            System.out.println("Connected " + a + " " + b + ": " + antennaGraph.connected(a, b));
            System.out.println("Shortest distance " + a + " " + b + ": " + antennaGraph.shortestDistance(a, b));
            System.out.println("Distance to border from " + a + ": " + antennaGraph.distanceToBorder(a));
        }
    }
}
