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
			if (newRow < 0 || newRow >= tileMap[0].length || newCol < 0 || newCol >= tileMap[0][0].length) {
				continue;
			}

			Tile neighbor = tileMap[room][newRow][newCol];
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
                System.out.println("Goal found at: " + current + " ");
                System.out.println("At room " + (1+current.getRoom()) + ", " + "row " + (1+current.getRow()) + ", at column " + (1+current.getCol()));
                return;
            }
			if (element == '|') {
			    if (room + 1 < tileMap.length) { // Ensure next room exists
			        Tile nextRoomTile = tileMap[room + 1][current.getRow()][current.getCol()];
			        if (!visited.contains(nextRoomTile)) {
			            searchList.add(nextRoomTile);
			            visited.add(nextRoomTile);
			        }
			    }
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
	    if (start == null) {
	        System.err.println("Error: No start position found in Room " + room);
	        return;
	    }
	    queue.add(start);
	    visited.add(start);
	}

	
	public static void optimalSearch(Tile[][][] tileMap) {
		DJK.dijkstraSearch(tileMap, 0);
	}
	
	public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java MapTraversal [options] <map_filename>");
            return;
        }

        String mapFilename = null;
        boolean outCoordinateDisabled = false;

        // Process command-line arguments
        List<String> validArgs = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "--Opt":
                case "--Queue":
                case "--Stack":
                    // Ignore these switches
                    break;
                case "--OutCoordinate":
                    outCoordinateDisabled = true;  // Mark it as disabled
                    break;
                default:
                    validArgs.add(arg);
                    break;
            }
        }

        // The last argument should be the map filename
        if (!validArgs.isEmpty()) {
            mapFilename = validArgs.get(validArgs.size() - 1);
        } else {
            System.err.println("Error: No map file specified.");
            return;
        }

        // Debugging output
        System.out.println("Map file: " + mapFilename);
        System.out.println("--OutCoordinate disabled: " + outCoordinateDisabled);

        // Load the map dynamically without hardcoding file names
        Tile[][][] tileMap = null;
        try {
            tileMap = MapReader.readMap(mapFilename);
        } catch (Exception e) {
            System.err.println("Error loading map: " + e.getMessage());
            return;
        }

        // Start the traversal
        StackTrav.stackSearch(tileMap, 0);
    }

}
