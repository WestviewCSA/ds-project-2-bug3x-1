import java.util.*;
import java.io.*;

public class p2 {
	private static boolean firsted; // only used for the 0th level, not viable for 1 >= n. must update.
	
	public static Tile[][][] quadSearch(Tile[][][] currPos) {
		// read NWSE path for the current position, enqueue viable paths
		
		// from the curr position, go through NWSE values and check for viable values. enqueue all in NWSE order as n+0,n+1,n+2,n+3 in the queue so that it can referenced again
		if()
		return null;
	}
	
	public static char queueSearch() {
		//redo the logic from scratch. used as an extension of quadSearch to account for W to a walkway or buck
		
		Tile[][][] nwse = null;
		Queue searchList = new LinkedList();
		if(firsted == false) {
			// set the first position if it's currently null
			wPosition(searchList, 0);
		}
		else {
			// find the
			char element = nwse[/*3 dimension array*/];
			Tile[][][] position = new Tile(element);
			quadSearch(position);
		}
		return 0;
	}
	
	// queue logic: if it's a wall, don't enqueue. enqueue all locations by nwse that are non-wall values
	// if next enqueued is a '|', next level function and enqueue the '|' position
	// if next enqueued is a '$', complete and return position
	// change the . path to a + path, print it out.
	// do not queue invalid characters (a, b, c, !, ?)
	
	public static Queue wPosition(Queue queue, int l) {
		Tile[][][] tile = MapReader.readMap("txt_map1.txt");
		Tile[][][] pos = MapReader.startPosition(tile, 0);
		queue.add(pos);
		return queue;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// to run the search functionalities
		queueSearch();
	}

}
