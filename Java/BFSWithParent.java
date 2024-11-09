import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BFSWithParent {
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public void readGraphFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int vertex = Integer.parseInt(parts[0]);
                List<Integer> neighbors = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    neighbors.add(Integer.parseInt(parts[i]));
                }
                graph.put(vertex, neighbors);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Integer> bfsWithParent(int startVertex) {
        Map<Integer, Integer> parent = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);
        parent.put(startVertex, null);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            for (int neighbor : graph.getOrDefault(currentVertex, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parent.put(neighbor, currentVertex);
                }
            }
        }
        return parent;
    }

    public static void main(String[] args) {
        BFSWithParent bfs = new BFSWithParent();
        bfs.readGraphFromFile("graph.txt");
        
        int startVertex = 0;
        Map<Integer, Integer> parentMap = bfs.bfsWithParent(startVertex);

        System.out.println("Parent of each node:");
        for (Map.Entry<Integer, Integer> entry : parentMap.entrySet()) {
            System.out.println("Node: " + entry.getKey() + ", Parent: " + entry.getValue());
        }
    }
}
