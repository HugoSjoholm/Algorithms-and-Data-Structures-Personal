import java.util.ArrayList;

public class Geometry {
    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(10, 10);
        Point d = new Point(0, 10);

        Polygon polygon = new Polygon(new Point[] { a, b, c, d });

        Point inside = new Point(5, 5);
        Point outside = new Point(15, 5);

        Edge edge1 = new Edge(new Point(-1, 5), new Point(11, 5));
        Edge edge2 = new Edge(new Point(12, 5), new Point(15, 5));

        System.out.println("inside polygon: " + polygon.contains(inside));
        System.out.println("outside polygon: " + polygon.contains(outside));
        System.out.println("edge1 intersects polygon: " + polygon.intersects(edge1));
        System.out.println("edge2 intersects polygon: " + polygon.intersects(edge2));

        StdDraw.setCanvasSize(700, 700);
        StdDraw.setXscale(-2, 17);
        StdDraw.setYscale(-2, 12);
        StdDraw.clear();

        polygon.draw();

        StdDraw.setPenColor(StdDraw.RED);
        inside.draw();
        edge1.draw();

        StdDraw.setPenColor(StdDraw.GREEN);
        outside.draw();
        edge2.draw();
    }
}

class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean same(Point other) {
        double e = 0.000001;
        return Math.abs(x - other.x) < e && Math.abs(y - other.y) < e;
    }

    public void draw() {
        StdDraw.filledCircle(x, y, 0.15);
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

class Edge {
    public Point start;
    public Point end;

    public Edge(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Point point) {
        double value = compareValue(point);
        double e = 0.000001;

        if (value > e) {
            return 1;
        }

        if (value < -e) {
            return -1;
        }

        return 0;
    }

    public double compareValue(Point point) {
        double dx1 = end.x - start.x;
        double dy1 = end.y - start.y;
        double dx2 = point.x - start.x;
        double dy2 = point.y - start.y;

        return dx1 * dy2 - dy1 * dx2;
    }

    public double length() {
        return start.distanceTo(end);
    }

    public boolean contains(Point point) {
        if (compareTo(point) != 0) {
            return false;
        }

        double minX = Math.min(start.x, end.x);
        double maxX = Math.max(start.x, end.x);
        double minY = Math.min(start.y, end.y);
        double maxY = Math.max(start.y, end.y);
        double e = 0.000001;

        return point.x >= minX - e && point.x <= maxX + e && point.y >= minY - e && point.y <= maxY + e;
    }

    public boolean intersects(Edge other) {
        int a = compareTo(other.start);
        int b = compareTo(other.end);
        int c = other.compareTo(start);
        int d = other.compareTo(end);

        if (a == 0 && contains(other.start)) {
            return true;
        }

        if (b == 0 && contains(other.end)) {
            return true;
        }

        if (c == 0 && other.contains(start)) {
            return true;
        }

        if (d == 0 && other.contains(end)) {
            return true;
        }

        return a != b && c != d;
    }

    public boolean same(Edge other) {
        return start.same(other.start) && end.same(other.end) || start.same(other.end) && end.same(other.start);
    }

    public boolean sharesEndpoint(Edge other) {
        return start.same(other.start) || start.same(other.end) || end.same(other.start) || end.same(other.end);
    }

    public void draw() {
        StdDraw.line(start.x, start.y, end.x, end.y);
    }

    public String toString() {
        return start + " -> " + end;
    }
}

class Polygon {
    private ArrayList<Edge> edges;

    public Polygon(Point[] points) {
        edges = new ArrayList<Edge>();

        for (int i = 0; i < points.length; i++) {
            Point start = points[i];
            Point end = points[(i + 1) % points.length];
            edges.add(new Edge(start, end));
        }
    }

    public Polygon(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public ArrayList<Edge> edges() {
        return edges;
    }

    public ArrayList<Point> vertices() {
        ArrayList<Point> points = new ArrayList<Point>();

        for (Edge edge : edges) {
            points.add(edge.start);
        }

        return points;
    }

    public boolean contains(Point point) {
        for (Edge edge : edges) {
            if (edge.compareTo(point) < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean strictlyContains(Point point) {
        for (Edge edge : edges) {
            if (edge.compareTo(point) <= 0) {
                return false;
            }
        }

        return true;
    }

    public boolean intersects(Edge edge) {
        Point middle = new Point((edge.start.x + edge.end.x) / 2.0, (edge.start.y + edge.end.y) / 2.0);

        if (strictlyContains(edge.start) || strictlyContains(edge.end) || strictlyContains(middle)) {
            return true;
        }

        for (Edge side : edges) {
            if (edge.same(side)) {
                return false;
            }

            if (edge.intersects(side) && !edge.sharesEndpoint(side)) {
                return true;
            }
        }

        return false;
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);

        for (Edge edge : edges) {
            edge.draw();
        }

        for (Point point : vertices()) {
            point.draw();
        }
    }
}
