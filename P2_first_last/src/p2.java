import java.util.*;
import java.io.*;

public class p2 {
	
	public static String quadSearch(String ele) {
		// read all NWSE paths
		if()
		return "";
	}
	
	public static Tile[][][] queueSearch() {
		Queue searchList = new LinkedList();
		firstPosition(searchList);
		return null;
	}
	
	public static Queue firstPosition(Queue queue) {
		Tile[][][] tile = MapReader.readMap("txt_map1.txt");
		Tile[][][] pos = MapReader.startPosition(tile, 0);
		queue.add(pos);
		return queue;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		firstPosition(/*some queue*/);
	}

}
