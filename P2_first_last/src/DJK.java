import java.util.*;

public class DJK {

    // Node class to store the tile with its distance
    static class Node {
        Tile tile;      // The tile (node) in the maze
        int distance;   // The shortest distance from the start

        public Node(Tile tile, int distance) {
            this.tile = tile;
            this.distance = distance;
        }
    }

    public static void dijkstra(Tile[][][] tileMap, int startRoom) {
        
    }

    // Get neighbors (up, down, left, right)
    public static List<Tile> getNeighbors(Tile[][][] tileMap, Tile current) {
		return null;
       
    }

    public static void main(String[] args) {
        // Run the search functionality
    	Tile[][][] tileMap = null;
		try {
            tileMap = MapReader.readMap("src/map_input/txt_map6");
        } catch (IllegalCommandLineInputsException | IllegalMapCharacterException | IncompleteMapException | IncorrectMapFormatException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        dijkstra(tileMap, 0); // Assuming starting from room 0
    }
}
