import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DFSSearch {

    private Map<Integer, List<Integer>> graph;
    private boolean[] visited;
    private List<Integer> path;

    public DFSSearch(int vertices) {
        graph = new HashMap<>();
        visited = new boolean[vertices];
        path = new ArrayList<>();
    }

    public void addEdge(int source, int destination) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        graph.computeIfAbsent(destination, k -> new ArrayList<>()).add(source); // If it's undirected
    }

    public boolean dfs(int current, int destination) {
        visited[current] = true;
        path.add(current);

        if (current == destination) {
            return true;
        }

        for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, destination)) {
                    return true;
                }
            }
        }

        // Backtrack if destination not found
        path.remove(path.size() - 1);
        return false;
    }

    public List<Integer> findPath(int source, int destination) {
        if (dfs(source, destination)) {
            return path;
        }
        return null; // Return null if no path found
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int vertices = scanner.nextInt();
            DFSSearch dfsSearch = new DFSSearch(vertices);

            while (scanner.hasNextInt()) {
                int source = scanner.nextInt();
                int destination = scanner.nextInt();
                dfsSearch.addEdge(source, destination);
            }
            scanner.close();

            // Specify the source and destination here
            int source = 0;
            int destination = 4;
            List<Integer> path = dfsSearch.findPath(source, destination);

            if (path != null) {
                System.out.println("Path from " + source + " to " + destination + ": " + path);
            } else {
                System.out.println("No path found from " + source + " to " + destination);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }
    }
}
