import java.io.*;
import java.util.*;

public class MapReader {
	public static Tile[][][] readMap(String fileName) {
		Tile[][][] tile = null; // the returnable 3D array
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);

			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			Tile[][][] temp = new Tile[numRows][numCols][numRooms];

			for (int room = 0; room < numRooms; room++) {
				for (int row = 0; row < numRows; row++) {
					if (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						for (int col = 0; col < Math.min(line.length(), numCols); col++) {
							temp[room][row][col] = new Tile(line.charAt(col));
						}
					}
				}
			}
			scanner.close();
			tile = temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return tile;

//		List<char[]> allChars = new ArrayList<>(); // make a list of character arrays
//		
//		try (BufferedReader input = new BufferedReader(new FileReader(fileName))){ /* read the file, line by line*/
//			
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
	}

	public static int getRoom(Tile[][][] tileArray) {
		for (int room = 0; room < tileArray.length; room++) {
			for (int row = 0; row < tileArray[room].length; row++) {
				for (int col = 0; col < tileArray[room][row].length; col++) {
					if (tileArray[room][row][col].getCharacter() == 'W') {
						return room; // Return the current room
					}
				}
			}
		}
		return -1; // Not found
	}

	// looks pretty ugly with all the nested stuff. You can split
	public static Tile startPosition(Tile[][][] tileArray, int selectRoom) {
		if (tileArray != null) {
			for (int room = 0; room < tileArray.length; room++) {
				for (int row = 0; row < tileArray[room].length; row++) {
					for (int col = 0; col < tileArray[room][row].length; col++) {
						if (tileArray[room][row][col].getCharacter() == 'W') {
							if (room == selectRoom) {
								return tileArray[room][row][col]; // Return `W`'s starting position
							}
						}
					}
				}
			}
		}
		return null; // No start position found
	}

	public static char[][] arrayConvert(char[][] cMap) {

		return cMap;
	}

	public static ArrayList coordinateConvert() {
		return null;
	}

	// replace with getData, a method to return a string that you can make a
	// substring out of for all data? or a dictionary for hashin

	public static boolean isWalkway(String filename, char ele) {
		if (ele == '|') {
			Tile[][][] inputTiles = readMap(filename);
			int room = getRoom(inputTiles); // get the current room
//			setRoom(room+1);
			return true;
		}
		return false;
	}

	public static boolean isReward(Tile tile) {
		return tile.getCharacter() == '$';
	}

	public static void move(Queue<Tile> queue) {
		while (!queue.isEmpty()) {
			Tile tile = queue.poll();
			if (tile.getCharacter() == '.') {
				tile.setCharacter('+'); // Replace path with `+`
			}
		}
	}

	public static void printMap(Tile[][][] tileArray) {
		for (int room = 0; room < tileArray.length; room++) {
			System.out.println("Room " + room);
			for (int row = 0; row < tileArray[room].length; row++) {
				for (int col = 0; col < tileArray[room][row].length; col++) {
					System.out.print(tileArray[room][row][col].getCharacter());
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Tile[][][] map = readMap("src/map_input/txt_map7");
		if (map != null) {
			printMap(map);
			Tile start = startPosition(map, getRoom(map)); // get the starting position tile
			System.out.println("Start Position: " + (start != null ? start.getCharacter() : "Not found"));
		}
	}
}
