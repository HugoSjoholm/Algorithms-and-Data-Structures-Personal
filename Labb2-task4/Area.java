import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Area {
    private ArrayList<Polygon> polygons;
    private Point lowerLeft;
    private Point upperRight;

    public Area(String fileName) throws FileNotFoundException {
        polygons = new ArrayList<Polygon>();
        readFile(fileName);
    }

    private void readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        ArrayList<Edge> edges = new ArrayList<Edge>();
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith("Boundary:")) {
                ArrayList<Double> numbers = numbersFromLine(line, pattern);
                lowerLeft = new Point(numbers.get(0), numbers.get(1));
                upperRight = new Point(numbers.get(2), numbers.get(3));
            }

            if (line.startsWith("Polygon:")) {
                if (edges.size() > 0) {
                    polygons.add(new Polygon(edges));
                    edges = new ArrayList<Edge>();
                }
            }

            if (line.startsWith("(")) {
                ArrayList<Double> numbers = numbersFromLine(line, pattern);

                Point a = new Point(numbers.get(0), numbers.get(1));
                Point b = new Point(numbers.get(2), numbers.get(3));

                edges.add(new Edge(a, b));
            }
        }

        if (edges.size() > 0) {
            polygons.add(new Polygon(edges));
        }

        scanner.close();
    }

    private ArrayList<Double> numbersFromLine(String line, Pattern pattern) {
        ArrayList<Double> numbers = new ArrayList<Double>();
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            numbers.add(Double.parseDouble(matcher.group()));
        }

        return numbers;
    }

    public ArrayList<Polygon> polygons() {
        return polygons;
    }

    public Point lowerLeft() {
        return lowerLeft;
    }

    public Point upperRight() {
        return upperRight;
    }

    public boolean insideObstacle(Point point) {
        for (Polygon polygon : polygons) {
            if (polygon.strictlyContains(point)) {
                return true;
            }
        }

        return false;
    }

    public boolean visible(Point a, Point b) {
        Edge edge = new Edge(a, b);

        for (Polygon polygon : polygons) {
            if (polygon.intersects(edge)) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Point> vertices() {
        ArrayList<Point> points = new ArrayList<Point>();

        for (Polygon polygon : polygons) {
            for (Point point : polygon.vertices()) {
                if (!insideOtherPolygon(point, polygon)) {
                    points.add(point);
                }
            }
        }

        return points;
    }

    private boolean insideOtherPolygon(Point point, Polygon ownPolygon) {
        for (Polygon polygon : polygons) {
            if (polygon != ownPolygon && polygon.strictlyContains(point)) {
                return true;
            }
        }

        return false;
    }

    public void draw() {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(lowerLeft.x, upperRight.x);
        StdDraw.setYscale(lowerLeft.y, upperRight.y);
        StdDraw.clear();

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle((lowerLeft.x + upperRight.x) / 2.0, (lowerLeft.y + upperRight.y) / 2.0, (upperRight.x - lowerLeft.x) / 2.0, (upperRight.y - lowerLeft.y) / 2.0);

        for (Polygon polygon : polygons) {
            polygon.draw();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Area area = new Area(args[0]);

        System.out.println("Polygons: " + area.polygons().size());
        System.out.println("Vertices: " + area.vertices().size());

        area.draw();
    }
}
