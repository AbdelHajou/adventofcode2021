package nl.abdel.aoc;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class VentScanner {

    public int[][] scanLineSegments(String lineSegments, boolean includeDiagonalLines) {
        String[] segments = lineSegments.split("\n");
        List<Line> lines = parseLines(segments);

        int diagramWidth = getDiagramWidth(lines);
        int diagramHeight = getDiagramHeight(lines);

        int[][] diagram = new int[diagramHeight][diagramWidth];
        for (Line line : lines) {
            if (includeDiagonalLines || (line.x1 == line.x2 || line.y1 == line.y2)) {
                fillLine(diagram, line);
            }
        }

        return diagram;
    }

    private void fillLine(int[][] diagram, Line line) {
        int x = line.x1;
        int y = line.y1;
        while (x != line.x2 || y != line.y2) {
            diagram[y][x]++;
            x += Comparator.comparingInt(Integer::intValue).compare(line.x2, x);
            y += Comparator.comparingInt(Integer::intValue).compare(line.y2, y);
        }
        diagram[y][x]++;
    }

    private List<Line> parseLines(String[] segments) {
        List<Line> lines = new LinkedList<>();
        for (String segment : segments) {
            String[] points = segment.split(" -> ");
            String[] point1 = points[0].split(",");
            String[] point2 = points[1].split(",");
            lines.add(new Line(Integer.parseInt(point1[0]), Integer.parseInt(point1[1]), Integer.parseInt(point2[0]), Integer.parseInt(point2[1])));
        }
        return lines;
    }

    private int getDiagramWidth(List<Line> lines) {
        return lines.stream().max(Comparator.comparingInt(line -> line.maxX())).get().maxX() + 1;
    }

    private int getDiagramHeight(List<Line> lines) {
        return lines.stream().max(Comparator.comparingInt(line -> line.maxY())).get().maxY() + 1;
    }

    private record Line(int x1, int y1, int x2, int y2) {
        int maxX() {
            return Math.max(x1, x2);
        }

        int maxY() {
            return Math.max(y1, y2);
        }
    }
}
