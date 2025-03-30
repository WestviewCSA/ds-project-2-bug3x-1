import java.io.*;
import java.util.*;

public class StackTrav {
    private static boolean firsted;

    public static void quadSearch(Tile[][][] tileMap, Tile curr, Stack<Tile> stack, Set<Tile> visited, int room) {
        int row = curr.getRow();
        int col = curr.getCol();

        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow < 0 || newRow >= tileMap[room].length || newCol < 0 || newCol >= tileMap[room][0].length) {
                continue;
            }

            Tile neighbor = tileMap[room][newRow][newCol];
            if (neighbor != null && !visited.contains(neighbor) && neighbor.getCharacter() != '@') {
                if (neighbor.getCharacter() == '.') {
                    neighbor.setCharacter('+'); // Change path marker
                }
                stack.push(neighbor);
                visited.add(neighbor);
            }
        }
    }

    public static void stackSearch(Tile[][][] tileMap, int room) {
        Stack<Tile> searchList = new Stack<>();
        Set<Tile> visited = new HashSet<>();

        if (!firsted) {
            firstPosition(tileMap, searchList, visited, room);
            firsted = true;
        }

        while (!searchList.isEmpty()) {
            Tile current = searchList.pop();
            if (current == null) continue;

            char element = current.getCharacter();
            if (element == '$') {
                System.out.println("Goal found at: " + current);
                System.out.println("At room " + (1 + current.getRoom()) + ", row " + (1 + current.getRow()) + ", column " + (1 + current.getCol()));
                printMap(tileMap);
                return;
            }
            if (element == '|') {
                if (room + 1 < tileMap.length) {
                    stackSearch(tileMap, room + 1);
                }
                continue;
            }

            quadSearch(tileMap, current, searchList, visited, room);
        }

        System.out.println("No valid path found.");
        printMap(tileMap);
    }

    public static void firstPosition(Tile[][][] tileMap, Stack<Tile> stack, Set<Tile> visited, int room) {
        Tile start = MapReader.startPosition(tileMap, room);
        if (start != null) {
            stack.push(start);
            visited.add(start);
        }
    }

    public static void printMap(Tile[][][] tileMap) {
        for (int r = 0; r < tileMap.length; r++) {
            System.out.println("Room " + (r + 1) + ":");
            for (int i = 0; i < tileMap[r].length; i++) {
                for (int j = 0; j < tileMap[r][i].length; j++) {
                    System.out.print(tileMap[r][i][j].getCharacter() + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tile[][][] tileMap = null;
        try {
            tileMap = MapReader.readMap("src/map_input/txt_map6");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        stackSearch(tileMap, 0);
    }
}
