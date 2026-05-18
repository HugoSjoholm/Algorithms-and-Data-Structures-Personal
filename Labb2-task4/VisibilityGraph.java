import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VisibilityGraph {
    private Area area;
    private ArrayList<Point> points;
    private Graph graph;

    public VisibilityGraph(Area area) {
        this.area = area;
        points = area.vertices();
        buildGraph();
    }

    public void addPoint(Point point) {
        if (!area.insideObstacle(point)) {
            points.add(point);
            buildGraph();
        }
    }

    private void buildGraph() {
        HashMap<String, ArrayList<String>> neighbours = new HashMap<String, ArrayList<String>>();

        for (Point point : points) {
            neighbours.put(name(point), new ArrayList<String>());
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point a = points.get(i);
                Point b = points.get(j);

                if (area.visible(a, b)) {
                    neighbours.get(name(a)).add(name(b));
                    neighbours.get(name(b)).add(name(a));
                }
            }
        }

        String input = "";

        for (String point : neighbours.keySet()) {
            input += point;

            for (String other : neighbours.get(point)) {
                input += " " + other;
            }

            input += "\n";
        }

        Scanner scanner = new Scanner(input);
        graph = new Graph(scanner, " ");
        scanner.close();
    }

    private String name(Point point) {
        return point.x + "," + point.y;
    }

    public Graph graph() {
        return graph;
    }

    public int numberOfPoints() {
        return points.size();
    }

    public int numberOfEdges() {
        int count = 0;

        for (String vertex : graph.vertices()) {
            for (String other : graph.adj(vertex)) {
                count++;
            }
        }

        return count / 2;
    }

    public void draw() {
        area.draw();

        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point a = points.get(i);
                Point b = points.get(j);

                if (area.visible(a, b)) {
                    StdDraw.line(a.x, a.y, b.x, b.y);
                }
            }
        }

        StdDraw.setPenColor(StdDraw.RED);
        for (Point point : points) {
            StdDraw.filledCircle(point.x, point.y, 4);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Area area = new Area(args[0]);
        VisibilityGraph visibilityGraph = new VisibilityGraph(area);

        System.out.println("Points before adding start and end: " + visibilityGraph.numberOfPoints());
        System.out.println("Edges before adding start and end: " + visibilityGraph.numberOfEdges());

        if (args.length == 5) {
            Point start = new Point(Double.parseDouble(args[1]), Double.parseDouble(args[2]));
            Point end = new Point(Double.parseDouble(args[3]), Double.parseDouble(args[4]));

            visibilityGraph.addPoint(start);
            visibilityGraph.addPoint(end);

            System.out.println("Points after adding start and end: " + visibilityGraph.numberOfPoints());
            System.out.println("Edges after adding start and end: " + visibilityGraph.numberOfEdges());
        }

        visibilityGraph.draw();
    }
}
