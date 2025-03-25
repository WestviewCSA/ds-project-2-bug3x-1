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
			
			
			for(int room = 0; room < numRooms; room++) {
				for(int row = 0; row < numRows; row++) {
					if(scanner.hasNextLine()) {
						String line = scanner.nextLine();
						for(int col = 0; col < numCols; col++) {
							 temp[room][row][col] = new Tile(line.charAt(col));
						}
					}
				}
			}
			scanner.close();
			tile = temp;
		}
		catch(FileNotFoundException e){
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
	
	public static Tile startPosition(Tile[][][] tileArray) {
	    for (int room = 0; room < tileArray.length; room++) {
	        for (int row = 0; row < tileArray[room].length; row++) {
	            for (int col = 0; col < tileArray[room][row].length; col++) {
	                if (tileArray[room][row][col].getCharacter() == 'W') {
	                    return tileArray[room][row][col];  // Return `W`'s starting position
	                }
	            }
	        }
	    }
	    return null; // No start position found
	}

	
	public static char[][] arrayConvert(char[][] cMap){
		
		return cMap;
	}
	
	public static ArrayList coordinateConvert() {
		return null;
	}
	
	//replace with getData, a method to return a string that you can make a substring out of for all data? or a dictionary for hashing
	public static int getRoom(Tile[][][] tile) {
		int level = Tile[][][] // get the index of the 3rd dimension of the array
		return level;
	}

	public static boolean isWalkway(String filename, char ele) {
		if(ele == '|') {
			Tile[][][] inputTiles = readMap(filename);
			int room = getRoom(inputTiles); // get the current room
			setRoom(room+1);
			return true;
		}
		return false;
	}
	
	public static boolean isReward(char ele) {
		if(ele == '$') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void move(Queue queue) {
		// returns the optimal path, replacing the traversed '.' with '+'
		// for the coordinates, change the '.' in the queue to '+' for that coordinate, so then you can print the map with the original map
		// except for the replaced '.' by a conditional with the queued values
		for(int i = 0; i < queue.size(); i++) {
			
		}
		
	}
	
	public static void printUpdatedMap() { // implement the logic above
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readMap("txt_map1.txt");
		
	}
}
