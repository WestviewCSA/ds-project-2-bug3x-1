
public class Tile {
	private char character;

	public Tile(char c) {
		character = c;
	}

	public char getCharacter() {
		return character;
	}
	
	public void setCharacter(char newChar) {
		character = newChar;
	}

	public int getRoom(Tile[][][] tileArray) {
        for (int room = 0; room < tileArray.length; room++) {
            for (int row = 0; row < tileArray[room].length; row++) {
                for (int col = 0; col < tileArray[room][row].length; col++) {
                    if (tileArray[room][row][col].getCharacter() == 'W') {
                        return room;  // Return the current room
                    }
                }
            }
        }
        return -1; // Not found
  }
	
	public int getRow(Tile[][][] tileMap) {
		return 0;
	}
	
	public int getCol(Tile[][][] tileMap) {
		return 0;
	}
}
