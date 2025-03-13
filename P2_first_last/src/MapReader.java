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
			
			while(scanner.hasNextLine() /* only going across the row, but where's the column being accounted  for?*/){
				String row = scanner.nextLine();
				int rowCount = 0;
				int roomCount = 0;
				for(int j = 0; j < numRows; j++) {
					if(row.length() > 0) {
						for(int i = 0; i < row.length(); i++) {
							char element = row.charAt(i);
							temp[rowCount][i][roomCount] = new Tile(element);
							// add char to 3D array
							
							//changing to the index of the next level
						}
					}
				}
				roomCount++;
			}
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
	
	public static Tile[][][] startPosition(Tile[][][] tileArray, int l) {
		char curr = 0;
		Tile[][][] position = null;
		for(int i = 0; i < tileArray[i].length; i++) {
			for(int j = 0; j < tileArray[i][j].length; j++) {
				// find the char curr value of each tile in the 3d array storing tiles
				// set the startPosition to the tile coordinates where there is a tile
				if(curr == 'W') {
					position = new Tile[i][j][l];
				}
			}
		}
		return position;
	}
	
	public static char[][] arrayConvert(char[][] cMap){
		
		return cMap;
	}
	
	public static ArrayList coordinateConvert() {
		return null;
	}
	
	public static boolean isWalkway() {
		return false;
	}
	
	public static boolean isReward() {
		return false;
	}
	
	public static void move() {
		// returns the optimal path, replacing the traversed '.' with '+'
		return;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readMap("txt_map1.txt");
		
	}
}
