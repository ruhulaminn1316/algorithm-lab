import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BFSLevelParent {
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

    public Map<Integer, Integer> bfsWithLevelAndParent(int startVertex) {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> level = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);
        parent.put(startVertex, null);
        level.put(startVertex, 0);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            int currentLevel = level.get(currentVertex);

            for (int neighbor : graph.getOrDefault(currentVertex, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parent.put(neighbor, currentVertex);
                    level.put(neighbor, currentLevel + 1);
                }
            }
        }
        return level;
    }

    public static void main(String[] args) {
        BFSLevelParent bfs = new BFSLevelParent();
        bfs.readGraphFromFile("graph.txt");
        
        int startVertex = 0;
        Map<Integer, Integer> parentMap = bfs.bfsWithLevelAndParent(startVertex);
        
        System.out.println("Node | Level | Parent");
        System.out.println("----------------------");
        for (Map.Entry<Integer, Integer> entry : parentMap.entrySet()) {
            System.out.printf("Node: %d, Level: %d, Parent: %s%n",
                    entry.getKey(),
                    entry.getValue(),
                    entry.getValue() != null ? entry.getValue() : "null");
        }
    }
}
