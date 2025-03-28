import java.io.*;
import java.util.*;

public class MapReader {
	public static Tile[][][] readMap(String fileName) throws IllegalCommandLineInputsException, IllegalMapCharacterException, IncompleteMapException, IncorrectMapFormatException {
        Tile[][][] tile = null; // the returnable 3D array
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            // Check if the file starts with a valid map specification
            if (!scanner.hasNextInt()) {
                throw new IncorrectMapFormatException("The map is incorrectly formatted. The first line should contain two positive integers followed by a room count.");
            }

            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();
            
            // Validate if the map is correctly formatted
            if (numRows <= 0 || numCols <= 0 || numRooms <= 0) {
                throw new IncorrectMapFormatException("The map has incorrect dimensions. All numbers in the first line should be positive integers.");
            }

            // Initialize the 3D array
            Tile[][][] temp = new Tile[numRooms][numRows][numCols];
            
            // Validate each room
            for (int room = 0; room < numRooms; room++) {
                for (int row = 0; row < numRows; row++) {
                    if (!scanner.hasNextLine()) {
                        throw new IncompleteMapException("The map is incomplete. The number of rows or characters per row is not sufficient.");
                    }
                    String line = scanner.nextLine();

                    // Check if the line is the right length
                    if (line.length() != numCols) {
                        throw new IncompleteMapException("Row " + row + " does not have the correct number of columns. Expected " + numCols + " columns.");
                    }

                    // Validate that each character in the line is allowed
                    for (int col = 0; col < line.length(); col++) {
                        char ch = line.charAt(col);
                        if (!isValidMapCharacter(ch)) {
                            throw new IllegalMapCharacterException("Illegal character found at (" + row + ", " + col + "): " + ch);
                        }
                        temp[room][row][col] = new Tile(ch,row,col,room); // assuming Tile constructor is available
                    }
                }
            }
            
            scanner.close();
            tile = temp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tile;
    }

//	public static int getRoomList(Tile[][][] tileArray) {
//		for (int room = 0; room < tileArray.length; room++) {
//			for (int row = 0; row < tileArray[room].length; row++) {
//				for (int col = 0; col < tileArray[room][row].length; col++) {
//					if (tileArray[room][row][col].getCharacter() == 'W') {
//						return room; // Return the current room
//					}
//				}
//			}
//		}
//		return -1; // Not found
//	}

	private static boolean isValidMapCharacter(char ch) {
        // Check if the character is one of the valid map characters
        return ch == '@' || ch == '.' || ch == '$' || ch == 'W' || ch == '|';
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

	public static boolean isWalkway(Tile currTile, String filename, char ele) {
		if (ele == '|') {
			int room = currTile.getRoom(); // get the current room
			room +=1;
			// create an arrayList of 3D tile arrays
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
		Tile[][][] tileMap = null;
		try {
            tileMap = readMap("src/map_input/txt_map6");
        } catch (IllegalCommandLineInputsException | IllegalMapCharacterException | IncompleteMapException | IncorrectMapFormatException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
		if (tileMap != null) {
			printMap(tileMap);
			Tile start = startPosition(tileMap, 1); // get the starting position tile
			System.out.println("Start Position: " + (start != null ? start.getCharacter() : "Not found"));
		}
	}
}
