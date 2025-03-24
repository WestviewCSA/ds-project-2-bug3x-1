import java.util.*;
import java.io.*;

public class p2 {
	private static boolean firsted; // only used for the 0th level, not viable for 1 >= n. must update.
	
	public static Queue quadSearch(Tile[][][] currPos, Queue queue, Queue visit) {
		// read NWSE path for the current position, enqueue viable paths
		
		// must be tested ; most likely will not work due to the nature of toString() vs the 3D array variable, which is just 
		// from the curr position, go through NWSE values and check for viable values. enqueue all in NWSE order as n+0,n+1,n+2,n+3 in the queue so that it can referenced again
		int currRow = Integer.parseInt(currPos.toString().substring(0,1)); // get the row position of the currPos object
		int currCol = Integer.parseInt(currPos.toString().substring(1,2)); // get the col position o the currPos object
		int currRoom = Integer.parseInt(currPos.toString().substring(2,3)); // get the room position of the currPos object
		char ele = 0; // get the element of the currPos object
		
		// temporary, make a function to return a boolean value for the parameters of NWSE
		if(currRow + 1 > 0 && visitation == false) {
			queue.add(currPos[currRow+1][currCol][currRoom]);
		}
		if(currCol + 1 > 0 && visitation == false) {
			queue.add(currPos[currRow][currCol+1][currRoom]);
		}
		if(currRow-1 > 0 && visitation == false) {
			queue.add(currPos[currRow-1][currCol][currRoom]);
		}
		if(currCol-1 > 0 && visitation == false)
		queue.add(currPos[currRow][currCol-1][currRoom]);
		
		return queue;
	}
	public static char queueSearch(int room) {
		//redo the logic from scratch. used as an extension of quadSearch to account for W to a walkway or buck
		
		Tile[][][] nwse = null;
		
		
		Queue searchList = new LinkedList();
		Queue visited = new LinkedList();
		
		if(firsted == false && room == 1) {
			// set the first position if it's currently null
			wPosition(searchList, visited, 0);
			firsted = true;
		}
		else if(firsted == false && room != 1) {
			wPosition(searchList, visited, room);
		}
		else {
			// find and queue the next positions, call conditional methods for specific action
			char element = .getElement(); // method to be implemented
			Tile[][][] position; // find the character for the positions
			quadSearch(position, searchList, visited);
			
			queueSearch(room);
		}
		return 0;
	}
	
	// queue logic: if it's a wall, don't enqueue. enqueue all locations by nwse that are non-wall values
	// if next enqueued is a '|', next level function and enqueue the '|' position
	// if next enqueued is a '$', complete and return position
	// change the . path to a + path, print it out.
	// do not queue invalid characters (a, b, c, !, ?)
	
	public static Queue wPosition(Queue queue, Queue visited, int l) {
		Tile[][][] tile = MapReader.readMap("txt_map1.txt");
		Tile[][][] pos = MapReader.startPosition(tile, l);
		queue.add(pos);
		visited.add(pos);
		return queue;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// to run the search functionalities
		queueSearch(1);
	}

}
