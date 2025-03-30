import java.util.*;
import java.util.Map;
import java.util.HashMap;


public class DJK {
    
    private static final int INF = Integer.MAX_VALUE; // Represents "infinity" for unreachable nodes

    public static void dijkstraSearch(Tile[][][] tileMap, int startRoom) {
        PriorityQueue<TileNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Map<Tile, Integer> distance = new HashMap<>();
        Map<Tile, Tile> previous = new HashMap<>();
        Set<Tile> visited = new HashSet<>();

        // Get the start position
        Tile start = MapReader.startPosition(tileMap, startRoom);
        if (start == null) {
            System.err.println("Error: No start position found.");
            return;
        }

        // Initialize starting position
        pq.add(new TileNode(start, 0));
        distance.put(start, 0);

        while (!pq.isEmpty()) {
            TileNode currentNode = pq.poll();
            Tile currentTile = currentNode.tile;

            if (visited.contains(currentTile)) continue; // Skip if already processed
            visited.add(currentTile);

            char element = currentTile.getCharacter();

            // Goal found - reconstruct path
            if (element == '$') {
                System.out.println("Goal found at Room " + (currentTile.getRoom() + 1) +
                                   ", Row " + (currentTile.getRow() + 1) +
                                   ", Column " + (currentTile.getCol() + 1));
                reconstructPath(previous, currentTile);
                return;
            }

            // Handle transition to another room
            if (element == '|') {
                int nextRoom = currentTile.getRoom() + 1;
                if (nextRoom < tileMap.length) {
                    Tile nextTile = tileMap[nextRoom][currentTile.getRow()][currentTile.getCol()];
                    if (nextTile != null && !visited.contains(nextTile)) {
                        pq.add(new TileNode(nextTile, currentNode.cost));
                        distance.put(nextTile, currentNode.cost);
                        previous.put(nextTile, currentTile);
                    }
                }
            }

            // Explore valid neighbors
            quadSearch(tileMap, currentTile, pq, distance, previous, visited);
        }

        System.out.println("No valid path found.");
    }

    private static void quadSearch(Tile[][][] tileMap, Tile curr, PriorityQueue<TileNode> pq, 
                                   Map<Tile, Integer> distance, Map<Tile, Tile> previous, Set<Tile> visited) {
        int row = curr.getRow();
        int col = curr.getCol();
        int room = curr.getRoom();
        
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check bounds
            if (newRow < 0 || newRow >= tileMap[room].length || newCol < 0 || newCol >= tileMap[room][0].length) {
                continue;
            }

            Tile neighbor = tileMap[room][newRow][newCol];
            if (neighbor == null || visited.contains(neighbor) || neighbor.getCharacter() == '@') {
                continue;
            }

            int newCost = distance.getOrDefault(curr, INF) + 1; // Assuming uniform cost of 1 per move

            if (newCost < distance.getOrDefault(neighbor, INF)) {
                distance.put(neighbor, newCost);
                previous.put(neighbor, curr);
                pq.add(new TileNode(neighbor, newCost));
            }
        }
    }

    private static void reconstructPath(Map<Tile, Tile> previous, Tile goal) {
        List<Tile> path = new ArrayList<>();
        for (Tile at = goal; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.println("Shortest Path:");
        for (Tile tile : path) {
            System.out.println("Room " + (tile.getRoom() + 1) + ", Row " + (tile.getRow() + 1) + ", Column " + (tile.getCol() + 1));
        }
    }

    public static void main(String[] args) {
        Tile[][][] tileMap = null;
        try {
            tileMap = MapReader.readMap("src/map_input/txt_map6");
        } catch (IllegalCommandLineInputsException | IllegalMapCharacterException | IncompleteMapException | IncorrectMapFormatException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        dijkstraSearch(tileMap, 0);
    }
    
    // Helper class for priority queue
    private static class TileNode {
        Tile tile;
        int cost;

        TileNode(Tile tile, int cost) {
            this.tile = tile;
            this.cost = cost;
        }
    }
}
