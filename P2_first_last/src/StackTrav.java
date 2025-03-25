import java.io.*;
import java.util.*;

public class StackTrav {
	// update from queue to stack implementation
	
	private static boolean firsted;
	public static void quadSearch(Tile[][][] tileMap, Tile curr, Stack<Tile> stack, Set<Tile> visited) {
		int row = curr.getRow(tileMap);
		int col = curr.getCol(tileMap);
		int room = curr.getRoom(tileMap);

		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int[] dir : directions) {
			int newRow = row + dir[0];
			int newCol = col + dir[1];

			// Check bounds
			if (newRow < 0 || newRow >= tileMap.length || newCol < 0 || newCol >= tileMap[0].length) {
				continue;
			}

			Tile neighbor = tileMap[newRow][newCol][room];
			if (neighbor != null && !visited.contains(neighbor) && neighbor.getCharacter() != '@') {
				stack.add(neighbor);
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
			if (current == null)
				continue;

			char element = current.getCharacter();
			if (element == '$') {
				System.out.println("Goal found at: " + current);
				return;
			}
			if (element == '|') {
				System.out.println("Transitioning to next level.");
				continue;
			}

			// Add valid neighbors to the queue
			quadSearch(tileMap, current, searchList, visited);
		}

		System.out.println("No valid path found.");
	}

	// queue logic: if it's a wall, don't enqueue. enqueue all locations by nwse
	// that are non-wall values
	// if next enqueued is a '|', next level function and enqueue the '|' position
	// if next enqueued is a '$', complete and return position
	// change the . path to a + path, print it out.
	// do not queue invalid characters (a, b, c, !, ?)

	public static void firstPosition(Tile[][][] tileMap, Stack<Tile> stack, Set<Tile> visited, int room) {
		Tile start = MapReader.startPosition(tileMap, room);
		if (start != null) {
			stack.add(start);
			visited.add(start);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// to run the search functionalities
		Tile[][][] tileMap = MapReader.readMap("src/map_input/txt_map6");
		stackSearch(tileMap, 1);
	}
}
