import java.io.*;
import java.util.*;

public class StackTrav {
    private static boolean firsted;

    public static void quadSearch(Tile[][][] tileMap, Tile curr, Stack<Tile> stack, Set<Tile> visited, int room) {
        int row = curr.getRow();
        int col = curr.getCol();

        // Directions: Down, Up, Right, Left
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the new position is out of bounds for the current room
            if (newRow < 0 || newRow >= tileMap[room].length || newCol < 0 || newCol >= tileMap[room][0].length) {
                continue;
            }

            Tile neighbor = tileMap[room][newRow][newCol];
            if (neighbor != null && !visited.contains(neighbor) && neighbor.getCharacter() != '@') {
                stack.push(neighbor);
                visited.add(neighbor);
            }
        }
    }

    public static void stackSearch(Tile[][][] tileMap, int room) {
        Stack<Tile> searchList = new Stack<>();
        Set<Tile> visited = new HashSet<>(); // Use HashSet for O(1) lookup time

        if (!firsted) {
            firstPosition(tileMap, searchList, visited, room);
            firsted = true;
        }

        while (!searchList.isEmpty()) {
            Tile current = searchList.pop();
            if (current == null) continue;

            char element = current.getCharacter();
            System.out.println("Visiting: " + current); // Debugging print to check current tile

            if (element == '$') {
                System.out.println("Goal found at: " + current);
                return;
            }
            if (element == '|') {
                // Handle transition to next room
                System.out.println("Transitioning to next level.");
                if (room + 1 < tileMap.length) { // Check if next room exists
                    stackSearch(tileMap, room + 1);  // Call search on next room
                }
                continue;
            }

            // Add valid neighbors to the stack
            quadSearch(tileMap, current, searchList, visited, room);
        }

        System.out.println("No valid path found.");
    }

    public static void firstPosition(Tile[][][] tileMap, Stack<Tile> stack, Set<Tile> visited, int room) {
        Tile start = MapReader.startPosition(tileMap, room);
        if (start != null) {
            stack.push(start);
            visited.add(start);
        }
    }

    public static void main(String[] args) {
        // Run the stack-based search functionality
    	Tile[][][] tileMap = null;
		try {
            tileMap = MapReader.readMap("src/map_input/txt_map6");
        } catch (IllegalCommandLineInputsException | IllegalMapCharacterException | IncompleteMapException | IncorrectMapFormatException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        stackSearch(tileMap, 0); // Search the stack, starting from the first traversal
    }
}
