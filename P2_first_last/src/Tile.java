public class Tile {
    private char character;
    private final int row;
    private final int col;
    private int room;

    public Tile(char character, int row, int col, int room) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.room = room;
    }

    public char getCharacter() {
        return character;
    }
    
    public void setCharacter(char ch) {
    	character = ch;
    }

    public int getRoom() {
        return room;
    }
    
    public void setRoom(int r) {
    	room = r;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
