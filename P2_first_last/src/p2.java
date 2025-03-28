import java.util.*;
import java.io.*;

public class p2 {
	private static boolean firsted; // only used for the 0th level, not viable for 1 >= n. must update.

	public static void quadSearch(Tile[][][] tileMap, Tile curr, Queue<Tile> queue, Set<Tile> visited) {
		int row = curr.getRow();
		int col = curr.getCol();
		int room = curr.getRoom();

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
				queue.add(neighbor);
				visited.add(neighbor);
			}
		}
	}

	public static void queueSearch(Tile[][][] tileMap, int room) {
		Queue<Tile> searchList = new LinkedList<>();
		Set<Tile> visited = new HashSet<>(); // Use HashSet for O(1) lookup time

		if (!firsted) {
			wPosition(tileMap, searchList, visited, room);
			firsted = true;
		}

		while (!searchList.isEmpty()) {
			Tile current = searchList.poll();
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

	public static void wPosition(Tile[][][] tileMap, Queue<Tile> queue, Set<Tile> visited, int room) {
		Tile start = MapReader.startPosition(tileMap, room);
		if (start != null) {
			queue.add(start);
			visited.add(start);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// to run the search functionalities
		Tile[][][] tileMap = null;
		try {
            tileMap = MapReader.readMap("src/map_input/txt_map6");
        } catch (IllegalCommandLineInputsException | IllegalMapCharacterException | IncompleteMapException | IncorrectMapFormatException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
		queueSearch(tileMap, 0);
	}

}
